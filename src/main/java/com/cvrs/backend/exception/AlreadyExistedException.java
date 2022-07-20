package com.cvrs.backend.exception;


public class AlreadyExistedException extends RuntimeException{
    public AlreadyExistedException(String msg){
        super(msg);
    }

    public AlreadyExistedException(String msg, Exception ex){
        super(msg, ex);
    }
}
