package com.bridgelabz.bookstore.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.MailDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.dto.UserNameAndPhoneDTO;
import com.bridgelabz.bookstore.dto.UserCombinedDTO;
import com.bridgelabz.bookstore.dto.UserPasswordUpdateDTO;
import com.bridgelabz.bookstore.exception.AddressNotFoundException;
import com.bridgelabz.bookstore.exception.BookExistsException;
import com.bridgelabz.bookstore.exception.UserNotFoundException;
import com.bridgelabz.bookstore.exception.BookNotFoundException;
import com.bridgelabz.bookstore.exception.InvalidCredentialsException;
import com.bridgelabz.bookstore.exception.InvalidPasswordException;
import com.bridgelabz.bookstore.exception.UserAlreadyPresentException;
import com.bridgelabz.bookstore.exception.UserWishlistException;
import com.bridgelabz.bookstore.exception.UserCartException;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.model.WishList;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.repository.WishListRepository;
import com.bridgelabz.bookstore.service.MailService;
import com.bridgelabz.bookstore.service.UserService;
import com.bridgelabz.bookstore.utility.Authentication;
import com.bridgelabz.bookstore.utility.JwtUtil;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	Authentication authentication;
	
	@Autowired
	MailService mailService;


	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public User createUser(UserDTO userDTO) 
	{
		User user = new User(userDTO);
		userRepository.findUserByEmail(user.getEmail().toLowerCase());
		if(userRepository.findUserByEmail(user.getEmail().toLowerCase()).orElse(null) ==null){
			return userRepository.save(user);
		}
		
		throw new UserAlreadyPresentException();
	}

	@Override
	public UserCombinedDTO getUserByEmail(String token) 
	{

		token=token.split(" ")[1];
		String email=authentication.authenticate(token);

		Optional<User> user = Optional.of(userRepository.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(email)));

		
		Optional<List<Address>> addresses = Optional.of(addressRepository.findAddressByUser(user)
				.orElseThrow(()-> new AddressNotFoundException()));
		UserCombinedDTO userGetByEmailResponseDTO = new UserCombinedDTO(user,addresses);
		

		return userGetByEmailResponseDTO;
	}

	@Override
	public User updateUserPassword(String email, UserPasswordUpdateDTO userPasswordUpdateDTO) 
	{
		
		Optional<User> user = userRepository.findUserByEmail(email);
		user.get().setPassword(userPasswordUpdateDTO.getPassword());
		return userRepository.save(user.get());
	}
	
	@Override
	public User updateUserNameAndPassword(String token, @Valid UserNameAndPhoneDTO userNameAndPhoneDTO) {
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);
		
		Optional<User> user = userRepository.findUserByEmail(email);
		user.get().setFullName(userNameAndPhoneDTO.getFullName());
		user.get().setMobileNumber(userNameAndPhoneDTO.getMobileNumber());
		
		return userRepository.save(user.get());
	}
	
	@Override
	public User updateUser(String token, @Valid UserDTO userDTO) {
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);
		
		Optional<User> user = userRepository.findUserByEmail(email);
		user.get().setFullName(userDTO.getFullName());
		user.get().setMobileNumber(userDTO.getMobileNumber());
		user.get().setPassword(userDTO.getPassword());
		user.get().setEmail(userDTO.getEmail());
		return userRepository.save(user.get());
	}

	@Override
	public User updateUserWishList(String token, UUID bookId) 
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);
		User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
		Book book =bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException());
		WishList wishList =wishListRepository.findById(user.getWishList().getWishListId())
											 .orElseThrow(()-> new UserWishlistException());

		List<Book> books=  wishList.getBooks();

		if(books.contains(book))
		{
			throw new BookExistsException("This Book exists in users wishlist Already");

		}
		books.add(book);

		wishList.setBooks(books);
		wishList.setQuantity(books.size());
		user.setWishList(wishList);
		book.getWishLists().add(wishList);

		wishListRepository.save(wishList);
		
		return userRepository.save(user);
	}

	@Override
	public User updateUserCart(String token, UUID bookId)
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);

		User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
		Book book =bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException());
		Cart cart =cartRepository.findById(user.getCart().getCartId())
											 .orElseThrow(()-> new UserCartException());

		List<Book> books=  cart.getBooks();
		
		books.add(book);

		cart.setBooks(books);
		cart.setQuantity(books.size());
		user.setCart(cart);
		book.getCarts().add(cart);

		cartRepository.save(cart);
		
		 return userRepository.save(user);

	}

	@Override
	public Optional<List<Address>> updateUserAddress(String token,AddressDTO addressDTO) 
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);

		Optional<User> user = Optional.of(userRepository.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(email)));
		
		Optional<List<Address>> addresses = Optional.of(addressRepository.findAddressByUser(user)
				.orElseThrow(()-> new AddressNotFoundException()));
		addresses.get().get(0).setUser(user.get());
		addresses.get().get(0).setCity(addressDTO.getCity());
		addresses.get().get(0).setState(addressDTO.getState());
		addresses.get().get(0).setPin(addressDTO.getPin());
		addresses.get().get(0).setType(addressDTO.getType());
		for(Address address:addresses.get()) {
			addressRepository.save(address);
		}
		return addresses;
	}

	@Override
	public UserCombinedDTO getUserById(UUID id) {
		Optional<User> user = Optional.of(userRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException(id)));
		Optional<List<Address>> addresses = Optional.of(addressRepository.findAddressByUser(user)
				.orElseThrow(()-> new AddressNotFoundException()));
		UserCombinedDTO userGetByEmailResponseDTO = new UserCombinedDTO(user,addresses);
		return userGetByEmailResponseDTO;
	}

	@Override
	public Optional<List<Address>> updateUserAddressByEmailType(String token, String type,AddressDTO addressDTO) 
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);

		Optional<User> user = Optional.of(userRepository.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(email)));
		
		Optional<List<Address>> addresses = Optional.of(addressRepository.findAddressByUser(user)
				.orElseThrow(()-> new AddressNotFoundException()));
		for(Address address:addresses.get()) {
			if(address.getType().equalsIgnoreCase(type)) {
				address.setUser(user.get());
				address.setCity(addressDTO.getCity());
				address.setState(addressDTO.getState());
				address.setPin(addressDTO.getPin());
				addressRepository.save(address);
			}
		}
		return addresses;
	}

	@Override
	public Boolean deleteWishlistByEmail(String token, UUID bookId) 
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);

		Optional<User> user = Optional.of(userRepository.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(email)));
		for(Book book : user.get().getWishList().getBooks()) {
			if(book.getId().equals(bookId)) {
				book.getWishLists().forEach(wishlist -> wishlist.getBooks().remove(book));
				Optional<WishList> wishList = wishListRepository.findById(user.get().getWishList().getWishListId());
				wishList.get().setQuantity(wishList.get().getQuantity()-1);
				wishListRepository.saveAll(book.getWishLists());
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean deleteCartByEmail(String token, UUID bookId) 
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);
		Optional<User> user = Optional.of(userRepository.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(email)));
		for(Book book: user.get().getCart().getBooks()) {
			if(book.getId().equals(bookId)) {
				book.getCarts().forEach(cart -> cart.getBooks().remove(book));
				Optional<Cart> cart = cartRepository.findById(user.get().getCart().getCartId());
				cart.get().setQuantity(cart.get().getQuantity()-1);
				cartRepository.saveAll(book.getCarts());
				return true;
			}
		}
		 return false;
	}

	@Override
	public UUID updateUserOrder(String token, UUID cartId)
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);

		User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
		Cart cart =user.getCart();

		Order order=cart.UpdateCartToOrder();
		order.setUser(user);
		orderRepository.save(order);

		return order.getOrderId();
	}


	@Override
	public List<Order> getUserOrders(String token)
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);

		User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException(email));

		List<Order> orders=orderRepository.findByUserId(user.getUserId());

		return orders;


	}
	@Override
	public Boolean getUserOrderedBook(String token,UUID bookId)
	{
		
		List<Order> orders=getUserOrders(token);
		for(Order order:orders) {
			for(Book book:order.getBooks()) {
				if(book.getId().equals(bookId))
					return true;
			}
		}
		return false;


	}

	@Override
	public String login(LoginDTO loginDTO)
	{
		User user = userRepository.findUserByEmail(loginDTO.getEmail()).orElseThrow(()-> new UserNotFoundException(loginDTO.getEmail()));

		if(user.getEmail().equalsIgnoreCase(loginDTO.getEmail()) && user.getPassword().equals(loginDTO.getPassword()))
		{
			String token=jwtUtil.generateToken(user);
			return token;

		}
		else if(user.getEmail().equalsIgnoreCase(loginDTO.getEmail())) {
			throw new InvalidPasswordException();
		}
		else
		{
			throw new InvalidCredentialsException();
		}



	}

	@Override
	public Optional<Address> updateUserAddressById(String token, @Valid AddressDTO addressDTO,
			UUID id) {
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);
		
		Optional<User> user = Optional.of(userRepository.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(email)));
		
		Optional<Address> addresses = Optional.of(addressRepository.findById(id).orElseThrow(
				()-> new AddressNotFoundException()));
		addresses.get().setUser(user.get());
		addresses.get().setCity(addressDTO.getCity());
		addresses.get().setState(addressDTO.getState());
		addresses.get().setPin(addressDTO.getPin());
		addresses.get().setType(addressDTO.getType());
		addresses.get().setAddress(addressDTO.getAddress());
		
			addressRepository.save(addresses.get());
		return addresses;
	}

	@Override
	public String sendOtpMail(String email) {
		Optional<User> user = Optional.of(userRepository.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(email)));
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    String otp=String.format("%06d", number);
		user.get().setOtp(otp);
		userRepository.save(user.get());
		MailDTO mail = new MailDTO();
        mail.setMailFrom("bookstoreapp0@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Password Reset For Book-Store");
        mail.setMailContent("Hi "+user.get().getFullName().toUpperCase()+"\n Here is your OTP for password reset : "+otp+"\n click on the following link to reset your password \n http://localhost:3000/resetpassword");
        mailService.sendMail(mail);
        return otp;
	}

	@Override
	public Boolean verifyOTP(String email,String otp)
	{
		User user = userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
		if(user.getOtp().equals(otp))
		{
			return true;
		}
		else

		return false;


	}


}