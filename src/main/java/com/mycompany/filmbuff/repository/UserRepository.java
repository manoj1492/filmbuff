package com.mycompany.filmbuff.repository;

import com.mycompany.filmbuff.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer>{
    
}
