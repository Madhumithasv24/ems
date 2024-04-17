package com.expeditors.ems.endpoint;

import com.expeditors.ems.dto.*;
import com.expeditors.ems.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ManagerEndpoint {

    @Autowired
    TaskService taskService;

    @PostMapping("/task")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody TaskCreateUser taskCreateUser, HttpServletRequest request) {
        taskService.validateManager(Integer.parseInt(request.getHeader("X-userId")), "Manager");
        taskService.createTask(taskCreateUser);
        return new BaseResponseWithoutData("success", "task created successfully");
    }

    @PostMapping("/taskAllocation")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody TaskAllocation taskAllocation, HttpServletRequest request) {
        taskService.validateManager(Integer.parseInt(request.getHeader("X-userId")), "Manager");
        taskService.createTaskAllocation(taskAllocation);
        return new BaseResponseWithoutData("success", "task allocated successfully");
    }

    @GetMapping("/tasks")
    public BaseResponse baseResponse(@RequestBody TaskAssignedToView taskAssignedToView, HttpServletRequest request) {
        //taskService.validateManager(Integer.parseInt(request.getHeader("X-userId")), "Manager");
        List<TaskAllocationResponse> users = taskService.getTask(taskAssignedToView);
        return new BaseResponse("success", "task allocated successfully", users);

    }

    @PutMapping("/updateTask")
    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody StatusRequest statusRequest, HttpServletRequest request) {
        taskService.updateTask(statusRequest);
        return new BaseResponseWithoutData("success", "task updated successfully");
    }

//    @PutMapping("/updateAllInTask")
//    public BaseResponseWithoutData baseResponseWithoutData(@RequestBody StatusRequest statusRequest, HttpServletRequest request) {
//        taskService.validateManager(Integer.parseInt(request.getHeader("X-userId")), "Manager");
//
//    }



        @GetMapping("/getTask")
    public BaseResponse baseResponse(@RequestBody TaskAssignedToView taskAssignedToView) {
        ListOfStatus list = taskService.getTaskStatus(taskAssignedToView);
        return new BaseResponse("Success", "Got the tasks", list);
    }

    @GetMapping("/getAllTask")
    public BaseResponse baseResponse() {
        List<GetAllTask> getAllTasks = taskService.getAllTask();
        return new BaseResponse("Success", "Got all the tasks", getAllTasks);

    }
}
