package com.expeditors.ems.repository;

import com.expeditors.ems.entity.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseTypeRepo extends JpaRepository<ExpenseType,Integer> {
}
