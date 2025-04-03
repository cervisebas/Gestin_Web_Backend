package com.isft194.gestin.repositories;

import com.isft194.gestin.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IRepository<User,Long>
{
    User findByMail(String mail);
    Boolean existsByMail(String email);
    User findByType(String type);
}
