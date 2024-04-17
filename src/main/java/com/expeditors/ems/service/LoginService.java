package com.expeditors.ems.service;

import com.expeditors.ems.dto.LoginRequest;
import com.expeditors.ems.entity.Role;
import com.expeditors.ems.entity.User;
import com.expeditors.ems.repository.RoleRepo;
import com.expeditors.ems.repository.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {
    @Autowired
    UserDetailRepo userDetailRepo;

    @Autowired
    RoleRepo roleRepo;

    public Integer authenticateUser(LoginRequest loginRequest) {
        User userDetail = userDetailRepo.findByEmail(loginRequest.getUserName());

        if (userDetail == null) {
            throw new RuntimeException("incorrect user");
        }
        if (userDetail != null) {
            if (!Objects.equals(userDetail.getPassword(), loginRequest.getLoginPassword())) {
                throw new RuntimeException("incorrect password");
            }
        }
        Integer id = userDetail.getId();

        //String id= Objects.toString(userid);//to convert integer to string
        return id;
        //we need id to return as data in the login response
    }

    public String returnRole(LoginRequest loginRequest) {
        User userDetail = userDetailRepo.findByEmail(loginRequest.getUserName());
        if (userDetail == null) {
            throw new RuntimeException("incorrect user");
        }
        String roleName = null;
        if (userDetail != null) {
            if (!Objects.equals(userDetail.getPassword(), loginRequest.getLoginPassword())) {
                throw new RuntimeException("incorrect password");
            }
            Role role = this.roleRepo.findById(userDetail.getRole().getId()).orElseThrow(() -> new RuntimeException("no role found"));
            roleName = role.getName();

        }
        return roleName;
    }
}

