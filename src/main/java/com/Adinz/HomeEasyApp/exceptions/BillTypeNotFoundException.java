package com.Adinz.HomeEasyApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BillTypeNotFoundException extends RuntimeException{
    public BillTypeNotFoundException(String message){
        super(message);
    }
}
