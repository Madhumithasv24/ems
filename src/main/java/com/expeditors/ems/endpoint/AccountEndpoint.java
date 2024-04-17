package com.expeditors.ems.endpoint;

import com.expeditors.ems.dto.*;
import com.expeditors.ems.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AccountEndpoint {
    @Autowired
    AccountService accountService;


    @PostMapping("/createAccount")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody AccountRequest accountRequest, HttpServletRequest request) {
       // accountService.validateAccountant(Integer.parseInt(request.getHeader("X-userid")), "Accountant");

        accountService.setAccount(accountRequest);
        return new BaseResponseWithoutData("success", "account created successfully");
    }

    @PutMapping("/updateAccount")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody UpdateAccount updateAccount, HttpServletRequest request) {
        accountService.validateAccountant(Integer.parseInt(request.getHeader("X-userid")), "Accountant");

        accountService.updateAccount(updateAccount);
        return new BaseResponseWithoutData("success", "account updated successfully");
    }

    @GetMapping("/getStatus")
    public BaseResponse baseResponse(@RequestBody GetAccountStatusRequest getAccountStatus) {
        List<GetAccountStatusResponse> update =accountService.viewStatus(getAccountStatus);
        return new BaseResponse("Success", "Got the status", update);
}
    }
