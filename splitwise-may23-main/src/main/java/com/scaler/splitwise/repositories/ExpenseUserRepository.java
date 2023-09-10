package com.scaler.splitwise.repositories;

import com.scaler.splitwise.models.ExpenseUser;
import com.scaler.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
}
