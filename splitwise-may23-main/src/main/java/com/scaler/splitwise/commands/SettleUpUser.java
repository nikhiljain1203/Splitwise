package com.scaler.splitwise.commands;

import com.scaler.splitwise.controllers.SettleUpController;
import com.scaler.splitwise.dtos.SettleUpUserRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUser implements Command {
    /*
    Expected Input : SettleUp <user_id>
     */
    //SettleUpUser is a command from the user input.
    private SettleUpController settleUpController;

    @Autowired
    public SettleUpUser(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {;
        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(0).equals(CommandKeywords.SettleUpUser);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));

        SettleUpUserRequestDto settleUpUserRequestDto = new SettleUpUserRequestDto();
        settleUpUserRequestDto.setUserId(Long.valueOf(words.get(1)));

        SettleUpUserResponseDto responseDto = settleUpController.settleUpUser(settleUpUserRequestDto);
    }
}
