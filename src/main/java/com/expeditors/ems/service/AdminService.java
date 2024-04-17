package com.expeditors.ems.service;

import com.expeditors.ems.dto.UpdateUser;
import com.expeditors.ems.dto.UserCreateRequest;
import com.expeditors.ems.dto.UserResponse;
import com.expeditors.ems.entity.Role;
import com.expeditors.ems.entity.User;
import com.expeditors.ems.repository.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
  @Autowired
  UserDetailRepo userDetailRepo;

  public void createUser(UserCreateRequest userCreateRequest){
    User userDetail=new User();
    userDetail.setName(userCreateRequest.getName());
    userDetail.setEmail(userCreateRequest.getEmail());
    Role role=new Role();
    role.setId(userCreateRequest.getRole());
    userDetail.setRole(role);
    userDetail.setCreatedat(LocalDateTime.now());
    userDetailRepo.save(userDetail);//save is an inbuilt method to save the data
  }

  public List<UserResponse> getUsers(){
    List<User> users = this.userDetailRepo.findByRoleNameNot("Admin");
    List<UserResponse> userResponses = new ArrayList<>();
    users.forEach(user -> {//we are having all the users in the users list, so now  we create new objects and id,name,email
      UserResponse userResponse = new UserResponse();
      userResponse.setId(user.getId());
      userResponse.setName(user.getName());
      userResponse.setEmail(user.getEmail());
      userResponse.setRoleName(user.getRole().getName());//role name is present it in the role table, so we are using
      userResponses.add(userResponse);
    });
    return userResponses ;
  }

  public void validateUser(Integer requestId, String resource){
    User user = this.userDetailRepo.findById(requestId).orElseThrow(() -> new RuntimeException("user not found"));
    if (!user.getRole().getName().equalsIgnoreCase(resource)){
      throw new RuntimeException("unauthorized user");
    }
  }

  public void updateUser(UpdateUser updateUser){
    User user1=this.userDetailRepo.findById(updateUser.getId())
                    .orElseThrow(()->new RuntimeException("user not found"));

    user1.setName(updateUser.getName());
    user1.setEmail(updateUser.getEmail());
    user1.setPassword(updateUser.getPassword());
    userDetailRepo.save(user1);

  }
}
