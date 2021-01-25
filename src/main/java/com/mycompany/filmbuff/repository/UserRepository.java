package com.mycompany.filmbuff.repository;

import com.mycompany.filmbuff.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
