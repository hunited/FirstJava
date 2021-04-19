package hu.durasoft.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGeneral {

    @ExceptionHandler
    public String exceptionHandler(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "exceptionHandler";
    }

}
