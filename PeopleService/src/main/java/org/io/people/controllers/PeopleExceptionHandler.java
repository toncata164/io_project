package org.io.people.controllers;

import org.io.people.messages.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PeopleExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Message showMessage(Exception e){
        return new Message(e.getMessage());
    }
}
