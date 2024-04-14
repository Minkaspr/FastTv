package com.mk.fasttvbe.repository;

import com.mk.fasttvbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
