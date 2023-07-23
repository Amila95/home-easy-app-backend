package com.Adinz.HomeEasyApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectException(ProjectIDException ex, WebRequest request){
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleBillTypeFoundException(BillTypeNotFoundException ex, WebRequest request){
        BillTypeNotFoundExceptionResponse billTypeNotFoundExceptionResponse = new BillTypeNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(billTypeNotFoundExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex, WebRequest request){
        UsernameAlreadyExistsResponse usernameAlreadyExistsResponse = new UsernameAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity(usernameAlreadyExistsResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameNotFoundException(InvalidLoginResponseException ex, WebRequest request){
        InvalidLoginResponseException invalidLoginResponseException = new InvalidLoginResponseException();
        return new ResponseEntity(invalidLoginResponseException, HttpStatus.BAD_REQUEST);
    }
}
