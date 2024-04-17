package com.expeditors.ems.repository;

import com.expeditors.ems.entity.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseStatusRepo extends JpaRepository<ExpenseStatus,Integer> {
}
