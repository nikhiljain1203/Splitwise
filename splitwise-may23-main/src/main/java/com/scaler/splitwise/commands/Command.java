package com.scaler.splitwise.commands;

public interface Command {

    void execute(String input);

    // validate
    boolean matches(String input);
}
