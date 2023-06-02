package com.pensil.com.projectcilegon.reporsitory;

import com.pensil.com.projectcilegon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}

