package com.buinam.userservice.repository;

import com.buinam.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.buinam.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserId(Long userId); 
}
