package com.isft194.gestin.repositories;

import org.springframework.stereotype.Repository;

import com.isft194.gestin.models.UserSession;

@Repository
public interface IUserSessionRepository extends IRepository<UserSession, Long> {
}
