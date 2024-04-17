package com.expeditors.ems.service;

import com.expeditors.ems.dto.*;
import com.expeditors.ems.entity.Role;
import com.expeditors.ems.entity.TaskAllocationEntity;
import com.expeditors.ems.entity.TaskEntity;
import com.expeditors.ems.entity.User;
import com.expeditors.ems.repository.TaskAllocationRepo;
import com.expeditors.ems.repository.TaskRepo;
import com.expeditors.ems.repository.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    UserDetailRepo userDetailRepo;
    @Autowired
    TaskAllocationRepo taskAllocationRepo;

    public void createTask(TaskCreateUser taskCreateUser) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName(taskCreateUser.getTaskName());
        taskEntity.setTaskDescription(taskCreateUser.getTaskDesc());
        User user = this.userDetailRepo.findById(taskCreateUser.getCreatedBy()).
                orElseThrow(() -> new RuntimeException("user not found "));
        user.setId(taskCreateUser.getCreatedBy());
        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setCreatedBy(user);
        taskRepo.save(taskEntity);
    }

    public void validateManager(Integer requestId, String resource) {
        User user = this.userDetailRepo.findById(requestId).
                orElseThrow(() -> new RuntimeException("user not found"));
        if (!user.getRole().getName().equalsIgnoreCase(resource)) {
            throw new RuntimeException("unauthorized user");
        }
    }

    public void createTaskAllocation(TaskAllocation taskAllocation) {
        TaskAllocationEntity taskAllocationEntity = new TaskAllocationEntity();
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskAllocation.getTaskId());
        taskAllocationEntity.setTaskId(taskEntity);
        User user = new User();
        user.setId(taskAllocation.getDeveloperId());
        taskAllocationEntity.setDeveloperId(user);
        User user1 = new User();
        user1.setId(taskAllocation.getAssignedBy());
        taskAllocationEntity.setAssignedBy(user1);
        taskAllocationEntity.setAssignedAt(LocalDateTime.now());
        taskAllocationEntity.setStatus("Incomplete");
        taskAllocationRepo.save(taskAllocationEntity);
    }

    public List<TaskAllocationResponse> getTask(TaskAssignedToView taskAssignedToView) {
        List<TaskAllocationEntity> taskAllocationEntities = this.taskAllocationRepo.findByDeveloperIdId(taskAssignedToView.getDeveloper_id());
        List<TaskAllocationResponse> taskAllocationResponses = new ArrayList<>();
        taskAllocationEntities.forEach(entity -> {
            TaskAllocationResponse taskAllocationResponse = new TaskAllocationResponse();
            taskAllocationResponse.setTaskId(entity.getTaskId().getId());
            taskAllocationResponse.setDeveloperId(entity.getDeveloperId().getId());
            taskAllocationResponse.setDeveloperName(entity.getDeveloperId().getName());
            taskAllocationResponse.setAssignedBy(entity.getAssignedBy().getId());
            taskAllocationResponse.setStatus(entity.getStatus());
            taskAllocationResponses.add(taskAllocationResponse);
        });
        return taskAllocationResponses;
    }


    public void updateTask(StatusRequest statusRequest) {
        TaskAllocationEntity taskAllocationEntity = this.taskAllocationRepo.findByDeveloperIdIdAndTaskIdId(statusRequest.getDeveloperId(), statusRequest.getTaskId());
        taskAllocationEntity.setStatus(statusRequest.getTaskStatus());
        taskAllocationRepo.save(taskAllocationEntity);
    }

    private String status1 = "Complete";
    private String status2 = "Incomplete";

    public ListOfStatus getTaskStatus(TaskAssignedToView taskAssignedToView) {
        List<TaskAllocationEntity> taskAllocationEntities = this.taskAllocationRepo.findByDeveloperIdId(taskAssignedToView.getDeveloper_id());
        //here we are creating a list which contains details of entity task allocation .
        List<TaskStatusResponse> taskStatusResponses = new ArrayList<>();
        List<TaskStatusResponse> taskStatusResponses1 = new ArrayList<>();
        taskAllocationEntities.forEach(status -> {
            TaskStatusResponse taskAllocationResponse = new TaskStatusResponse();
            taskAllocationResponse.setTask_id(status.getTaskId().getId());
            taskAllocationResponse.setStatus(status.getStatus());
            if (status.getStatus().equalsIgnoreCase(status1)) {
                taskStatusResponses.add(taskAllocationResponse);
            } else {
                taskStatusResponses1.add(taskAllocationResponse);
            }
        });
        ListOfStatus task = new ListOfStatus();
        task.setCompletedList(taskStatusResponses);
        task.setIncompletedList(taskStatusResponses1);
        return task;
    }


    public List<GetAllTask> getAllTask() {
        List<TaskAllocationEntity> taskAllocationEntities = this.taskAllocationRepo.findAll();
        List<GetAllTask> getAllTasks = new ArrayList<>();
        taskAllocationEntities.forEach(task -> {
            GetAllTask allTask = new GetAllTask();
            allTask.setDeveloperId(task.getDeveloperId().getId());
            allTask.setTaskStatus(task.getStatus());
            allTask.setTaskId(task.getTaskId().getId());

            TaskEntity taskEntity = this.taskRepo.findById(task.getTaskId().getId()).get();
            allTask.setTaskDesc(taskEntity.getTaskDescription());
            allTask.setTaskName(taskEntity.getName());
            getAllTasks.add(allTask);


        });
        return getAllTasks;
    }

}

