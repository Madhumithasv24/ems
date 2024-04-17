package com.expeditors.ems.repository;

import com.expeditors.ems.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository <TaskEntity,Integer> {
//    TaskEntity findByTaskId(Integer TaskId);
}
