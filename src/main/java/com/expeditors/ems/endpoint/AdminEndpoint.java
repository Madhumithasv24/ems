package com.expeditors.ems.endpoint;

import com.expeditors.ems.dto.*;
import com.expeditors.ems.service.AdminService;
import com.expeditors.ems.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
//@RequestMapping("/user")
public class AdminEndpoint {
    @Autowired
    AdminService adminService;
    @Autowired
    LoginService loginService;

    @PostMapping("/user")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody UserCreateRequest userCreateRequest, HttpServletRequest request) {
        adminService.validateUser(Integer.parseInt(request.getHeader("X-userid")), "Admin");
        adminService.createUser(userCreateRequest);
        return new BaseResponseWithoutData("success", "user created successfully");
    }

    @GetMapping("/GetUser")
    public BaseResponse getAllUsersExceptAdmin() {//HttpServletRequest request
     // adminService.validateUser(Integer.parseInt(request.getHeader("X-userid")), "Admin");

        List<UserResponse> users = adminService.getUsers();
        return new BaseResponse("Success", "Got the users", users);
    }

    @PutMapping("/updateuser")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody UpdateUser updateUser1) {
//        adminService.validateUser(Integer.parseInt(request.getHeader("X-userid")), "Admin");

        adminService.updateUser(updateUser1);
        return new BaseResponseWithoutData("success","user updated successfully");
    }

    @PostMapping("/login")
    public LoginResponse loginResponse(@RequestBody LoginRequest loginRequest) {
        Integer id = loginService.authenticateUser(loginRequest);
        String roleName=loginService.returnRole(loginRequest);
        return new LoginResponse("true" ,"user logged in successfully",roleName,id);

    }
    }
