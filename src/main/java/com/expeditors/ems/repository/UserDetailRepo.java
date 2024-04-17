package com.expeditors.ems.repository;

import com.expeditors.ems.dto.GetAccountStatusResponse;
import com.expeditors.ems.dto.UserCreateRequest;
import com.expeditors.ems.dto.UserResponse;
import com.expeditors.ems.entity.TaskAllocationEntity;
import com.expeditors.ems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailRepo extends JpaRepository <User,Integer> {
    List<User> findByRoleNameNot(String role);

    User findByEmail(String email);


}
