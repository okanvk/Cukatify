package com.okanciftci.cukatify.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseArtistExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleArtistNotFoundException(ArtistNotFoundException ex, WebRequest request) {
        ArtistNotFoundResponse exceptionResponse = new ArtistNotFoundResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleFileStorageException(FileStorageException ex, WebRequest request) {
        FileStorageResponse exceptionResponse = new FileStorageResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public  final ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex,WebRequest request){
        UsernameAlreadyExistsResponse response = new UsernameAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }
}
