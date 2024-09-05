package com.pms.repository;

import com.pms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * LHCMD SILVA | 22783 | 30.08.2024 | CREATED User Repository
 * */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
