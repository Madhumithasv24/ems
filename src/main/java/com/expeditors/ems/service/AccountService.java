package com.expeditors.ems.service;

import com.expeditors.ems.dto.*;
import com.expeditors.ems.entity.*;
import com.expeditors.ems.repository.ExpenseDetailRepo;
import com.expeditors.ems.repository.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    ExpenseDetailRepo accountRepo;
    @Autowired
    UserDetailRepo userDetailRepo;


    public void setAccount(AccountRequest accountRequest) {
        ExpenseDetail expenseDetail = new ExpenseDetail();
        expenseDetail.setId(accountRequest.getExpenseId());

        expenseDetail.setAmount(accountRequest.getAmount());
        expenseDetail.setDescription(accountRequest.getDescription());
        expenseDetail.setSpentAt(accountRequest.getDate());

        ExpenseStatus expenseStatus = new ExpenseStatus();
        expenseStatus.setId(accountRequest.getStatusId());
        expenseDetail.setStatusId(expenseStatus);


        User user = new User();
        user.setId(accountRequest.getUserId());
        expenseDetail.setUserId(user);

        User user1 = new User();
        user1.setId(accountRequest.getAccountedBy());
        expenseDetail.setAccountedBy(user1);

        ExpenseType expenseType = new ExpenseType();
        expenseType.setId(accountRequest.getExpenseId());
        expenseDetail.setTypeId(expenseType);

        expenseDetail.setAccountedAt(LocalDateTime.now());
        expenseDetail.setCreatedAt(LocalDateTime.now());

        accountRepo.save(expenseDetail);
    }


    public void updateAccount(UpdateAccount updateAccount) {
        ExpenseDetail expenseDetail=this.accountRepo.findById(updateAccount.getExpenseId())
                .orElseThrow(()->new RuntimeException("user not found"));


        ExpenseStatus expenseStatus=new ExpenseStatus();
        expenseStatus.setId(updateAccount.getStatusId());
        expenseDetail.setStatusId(expenseStatus);

        User user=new User();
        user.setId(updateAccount.getAccountedBy());
        expenseDetail.setAccountedBy(user);

        accountRepo.save(expenseDetail);


    }

    public List<GetAccountStatusResponse> viewStatus(GetAccountStatusRequest getAccountStatusRequest){
        List<ExpenseDetail> expense=this.accountRepo.findByUserIdId(getAccountStatusRequest.getUserId());
        List<GetAccountStatusResponse> updatedStatus=new ArrayList<>();
        expense.forEach(expenses->{
            GetAccountStatusResponse status=new GetAccountStatusResponse();
            status.setExpenseId(expenses.getId());
            status.setStatusId(expenses.getStatusId().getId());
            updatedStatus.add(status);
        });
        return updatedStatus;
    }




    public void validateAccountant(Integer requestId, String resource) {
        User user = this.userDetailRepo.findById(requestId).
                orElseThrow(() -> new RuntimeException("user not found"));
        if (!user.getRole().getName().equalsIgnoreCase(resource)) {
            throw new RuntimeException("unauthorized user");
        }
    }
}