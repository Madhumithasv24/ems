package com.expeditors.ems.endpoint;

import com.expeditors.ems.dto.*;
import com.expeditors.ems.service.HiringService;
import com.expeditors.ems.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HiringEndpoint {
    @Autowired
    HiringService hiringService;
    @Autowired
    TaskService taskService;

    @PostMapping("/createCandidate")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody CreateCandidate candidate, HttpServletRequest request) {
        taskService.validateManager(Integer.parseInt(request.getHeader("X-userId")), "Manager");
        hiringService.createCandidate(candidate);
        return new BaseResponseWithoutData("success", "candidate created successfully");
    }

    @PutMapping("updateCandidate")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody CandidateStatusUpdate candidateStatusUpdate, HttpServletRequest request) {
        taskService.validateManager(Integer.parseInt(request.getHeader("X-userId")), "Manager");
        hiringService.updateCandidate(candidateStatusUpdate);
        return new BaseResponseWithoutData("success", "candidate updated successfully");
    }
    }
