package com.isft194.gestin.repositories;

import com.isft194.gestin.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IRepository<User, Long> {
    public User findByEmail(String email);
    public Boolean existsByEmail(String email);
}
