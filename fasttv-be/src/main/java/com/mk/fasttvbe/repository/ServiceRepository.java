package com.mk.fasttvbe.repository;

import com.mk.fasttvbe.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
