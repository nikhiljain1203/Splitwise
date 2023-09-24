package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwise.dtos.SettleUpGroupResponseDto;
import com.scaler.splitwise.dtos.SettleUpUserRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserResponseDto;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.services.SettleUpService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    private SettleUpService settleUpService;
    public SettleUpGroupResponseDto settleUpGroup(
            SettleUpGroupRequestDto settleUpGroupRequestDto
    ) {
        // settleup service -> settleup strategy
        settleUpService.settleUpGroup(settleUpGroupRequestDto.getGroupId());

        return null;
    }

    public SettleUpUserResponseDto settleUpUser(
            SettleUpUserRequestDto settleUpUserRequestDto
    ) {
        return null;
    }
}

// Deepak -> Hitesh : 500
