package com.drunkleen.crudmvc.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorHandlerController implements ErrorController {

    @GetMapping
    public String handleErrors(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        switch (statusCode) {
            case 400:
                model.addAttribute("error_title", "Bad Request");
                model.addAttribute("error_message", "The server cannot process the request due to a client error. ");
                break;
            case 401:
                model.addAttribute("error_title", "Unauthorized");
                model.addAttribute("error_message", "You are not authorized to view the requested resource.");
                break;
            case 403:
                model.addAttribute("error_title", "Forbidden");
                model.addAttribute("error_message", "Access to the requested resource is forbidden.");
                break;
            case 404:
                model.addAttribute("error_title", "Page not found.");
                model.addAttribute("error_message", "The page you’re looking for doesn’t exist.");
                break;
            case 405:
                model.addAttribute("error_title", "Method Not Allowed");
                model.addAttribute("error_message", "The method specified in the request is not allowed.");
                break;
            case 500:
                model.addAttribute("error_title", "Internal Server Error");
                model.addAttribute("error_message", "Sorry, something went wrong on our end.");
                break;
            default:
                model.addAttribute("error_title", "Error");
                model.addAttribute("error_message", "An unexpected error occurred.");
        }

        model.addAttribute("error", statusCode);

        return "error/error_handler";
    }

}
