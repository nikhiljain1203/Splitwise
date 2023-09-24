package com.scaler.splitwise.commands;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterUserCommand implements Command {
    /*
    Expected Input :-

        registerUser <userName> <phoneNumber> <password>
        registerUser nikhil 1234 password
        register nikhil 1234 password
     */


    @Override
    public void execute(String input) {

    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));

        return words.size() == 4 && words.get(0).equals(CommandKeywords.RegisterUser);
    }
}
