package com.isft194.gestin.mappers;

import com.isft194.gestin.dtos.request.UserRequest;
import com.isft194.gestin.dtos.response.UserNecessaryResponse;
import com.isft194.gestin.dtos.response.UserResponse;
import com.isft194.gestin.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements IMapper<User,UserRequest,UserResponse>
{
    @Autowired
    private ModelMapper modelMapper;

    public User toUser(UserRequest userRequest)
    {
        User user = User.builder()
                .type(userRequest.getType())
                .mail(userRequest.getMail())
                .name(userRequest.getName())
                .lastName(userRequest.getLastName())
                .password(userRequest.getPassword())
                .identityDoc(userRequest.getIdentityDoc())
                .phoneNumber(userRequest.getPhoneNumber())
                .emergencyPhoneNumber(userRequest.getEmergencyPhoneNumber())
                .birthdate(userRequest.getBirthdate())
                .placeOfBirth(userRequest.getPlaceOfBirth())
                .gender(userRequest.getGender())
                .build();
        return user;
    }
    public UserResponse toUserResponse(User user)
    {
        UserResponse userResponse=new UserResponse();
        userResponse.setType(user.getType());
        userResponse.setMail(user.getMail());
        userResponse.setName(user.getName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPassword(user.getPassword());
        userResponse.setIdentityDoc(user.getIdentityDoc());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setBirthdate(user.getBirthdate());
        userResponse.setPlaceOfBirth(user.getPlaceOfBirth());
        userResponse.setGender(user.getGender());
        return userResponse;
    }

//Cree un Response en user que devuelve solo los datos necesarios.
    public UserNecessaryResponse toUserResponseNecessary(User user)
    {
        UserNecessaryResponse userNecessaryResponse = new UserNecessaryResponse();
        userNecessaryResponse.setType(user.getType());
        userNecessaryResponse.setName(user.getName());
        userNecessaryResponse.setLastName(user.getLastName());
        userNecessaryResponse.setMail(user.getMail());

        return userNecessaryResponse;
    }

    @Override
    public User fromRequestToObj(UserRequest request) throws Exception {
        User user = User.builder()
                .type(request.getType())
                .mail(request.getMail())
                .name(request.getName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .identityDoc(request.getIdentityDoc())
                .phoneNumber(request.getPhoneNumber())
                .emergencyPhoneNumber(request.getEmergencyPhoneNumber())
                .birthdate(request.getBirthdate())
                .placeOfBirth(request.getPlaceOfBirth())
                .gender(request.getGender())
                .build();
        return user;
    }

    @Override
    public UserResponse fromObjToResponse(User user) {
        UserResponse userResponse=new UserResponse();
        userResponse.setType(user.getType());
        userResponse.setMail(user.getMail());
        userResponse.setName(user.getName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPassword(user.getPassword());
        userResponse.setIdentityDoc(user.getIdentityDoc());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setBirthdate(user.getBirthdate());
        userResponse.setPlaceOfBirth(user.getPlaceOfBirth());
        userResponse.setGender(user.getGender());
        return userResponse;
    }
}
