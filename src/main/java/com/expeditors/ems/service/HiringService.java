package com.expeditors.ems.service;

import com.expeditors.ems.dto.CandidateStatusUpdate;
import com.expeditors.ems.dto.CreateCandidate;
import com.expeditors.ems.dto.UserCreateRequest;
import com.expeditors.ems.entity.Candidate;
import com.expeditors.ems.entity.HiringStatus;
import com.expeditors.ems.entity.User;
import com.expeditors.ems.repository.CandidateRepo;
import com.expeditors.ems.repository.HiringStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HiringService {
    @Autowired
    CandidateRepo candidateRepo;
   @Autowired
   HiringStatusRepo hiringStatusRepo;
  @Autowired
  AdminService adminService;


    public void createCandidate(CreateCandidate create){
        Candidate candidate=new Candidate();
        candidate.setId(create.getId());
        candidate.setName(create.getName());
        candidate.setEmail(create.getEmail());
        User user=new User();
        user.setId(create.getInterviewerId());
        candidate.setInterviewerId(user);
        HiringStatus hiringStatus=new HiringStatus();
        hiringStatus.setId(1);
        candidate.setStatusId(hiringStatus);
        candidate.setScheduledAt(create.getScheduledAt());
        candidateRepo.save(candidate);
    }

    public static Boolean createCandidate =  false;
    public void updateCandidate(CandidateStatusUpdate candidateStatusUpdate){
        HiringStatus hiringStatus=new HiringStatus();
        hiringStatus.setId(candidateStatusUpdate.getStatus());//candidate id
        Candidate candidate=candidateRepo.findById(candidateStatusUpdate.getId()).get();//get() is used to get the data from database to here
        candidate.setStatusId(hiringStatus);
        candidateRepo.save(candidate);
        if(candidateStatusUpdate.getStatus() == 5){
            createCandidate = true;
            UserCreateRequest userCreateRequest=new UserCreateRequest();//user-create request is the pojo where all the details for the create user is present
            userCreateRequest.setName(candidate.getName());
            userCreateRequest.setEmail(candidate.getEmail());
            userCreateRequest.setRole(2);
            userCreateRequest.setCreatedAt(LocalDateTime.now());
            adminService.createUser(userCreateRequest);
        }

        }

    }

