package com.isft194.gestin.services;

import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.models.User;
import com.isft194.gestin.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthService authService;

    public User getCurrent() throws NotAuthenticatedException {
        return authService.getCurrentSession();
    }

    public User update(User data) throws NotAuthenticatedException {
        User current = getCurrent();

        current.setNames(data.getNames());
        current.setLastNames(data.getLastNames());
        current.setDni(data.getDni());
        current.setBirthdate(data.getBirthdate());
        current.setPhone(data.getPhone());
        current.setGestinId(data.getGestinId());
        current.setEmail(data.getEmail());
        current.setBirthplace(data.getBirthplace());
        current.setIsTeacher(data.getIsTeacher());
        current.setGender(data.getGender());
        current.setEmergencyPhone(data.getEmergencyPhone());

        userRepository.save(current);
        return current;
    }
}
