package com.expeditors.ems.repository;

import com.expeditors.ems.entity.HiringStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiringStatusRepo extends JpaRepository<HiringStatus,Integer> {
}
