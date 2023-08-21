package com.bridgelabz.bookstore.utility;

import com.bridgelabz.bookstore.exception.UserNotFoundException;
import com.bridgelabz.bookstore.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Authentication 
{
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    public String authenticate(String token)
    {
       String userMail= jwtUtil.extractUsername(token);

       userRepository.findUserByEmail(userMail).orElseThrow(()-> new UserNotFoundException(userMail));

       return userMail;


    }
    
}
