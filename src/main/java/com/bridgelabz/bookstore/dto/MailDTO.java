package com.bridgelabz.bookstore.dto;

import java.util.Date;

import lombok.Data;

 @Data
public class MailDTO {
 
    private String mailFrom;
 
    private String mailTo;
 
    private String mailSubject;
 
    private String mailContent;
 
    private String contentType;
 
 
    public MailDTO() {
        contentType = "text/plain";
    }
 
}