package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.UserRequest;
import com.isft194.gestin.dtos.response.UserResponse;
import com.isft194.gestin.interfaces.IMapper;
import com.isft194.gestin.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements IMapper<User, UserRequest, UserResponse> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User fromRequestToModel(UserRequest request) {
        return modelMapper.map(request, User.class);
    }

    @Override
    public UserResponse fromModelToResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
