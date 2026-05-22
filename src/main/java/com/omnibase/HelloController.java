package com.omnibase;

import com.omnibase.app.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Home Page!";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Helo World!";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
        return new User(1, "John Doe", "john@example.com");
    }
}
