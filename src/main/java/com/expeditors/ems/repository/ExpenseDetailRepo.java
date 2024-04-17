package com.expeditors.ems.repository;

import com.expeditors.ems.dto.GetAccountStatusResponse;
import com.expeditors.ems.entity.ExpenseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseDetailRepo extends JpaRepository <ExpenseDetail,Integer> {
    List<ExpenseDetail> findByUserIdId(Integer UserId);

}
