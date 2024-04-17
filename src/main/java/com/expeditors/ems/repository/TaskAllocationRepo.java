package com.expeditors.ems.repository;

import com.expeditors.ems.dto.TaskAllocation;
import com.expeditors.ems.entity.TaskAllocationEntity;
import com.expeditors.ems.entity.TaskEntity;
import com.expeditors.ems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskAllocationRepo extends JpaRepository<TaskAllocationEntity,Integer>{
    List<TaskAllocationEntity> findByDeveloperIdId(Integer DeveloperId);


    TaskAllocationEntity findByDeveloperIdIdAndTaskIdId(Integer DeveloperId,Integer TaskId);
   List<TaskAllocationEntity> findAll();


}
