package com.scaler.splitwise.commands;

import org.springframework.stereotype.Component;

@Component
public class UpdateProfileCommand implements Command {
    @Override
    public void execute(String input) {

    }

    @Override
    public boolean matches(String input) {
        return false;
    }
}
