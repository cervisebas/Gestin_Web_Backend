package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.UserRequest;
import com.isft194.gestin.dtos.response.StudentResponse;
import com.isft194.gestin.dtos.response.UserNecessaryResponse;
import com.isft194.gestin.dtos.response.UserResponse;
import com.isft194.gestin.mappers.UserMapper;
import com.isft194.gestin.models.AcademicRecord;
import com.isft194.gestin.models.Career;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;
import com.isft194.gestin.repositories.IRepository;
import com.isft194.gestin.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends ServiceIMPL<User,Long>
{
    private final UserMapper userMapper;

    @Autowired
    private AcademicRecordService academicRecordService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private CareerService careerService;

    protected UserService(IRepository<User, Long> iRepository, UserMapper userMapper, AcademicRecordService academicRecordService) {
        super(iRepository);
        this.userMapper = userMapper;
    }

    public User newUser(UserRequest userRequest) {

        if (userRequest.getMail() == null || userRequest.getMail().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede ser nulo o vacío.");
        }

        if(existsUserBymail(userRequest.getMail())){
                throw new IllegalArgumentException("this user:" + userRequest.getMail() + ", already exists");
        }
        User user = mapUserRequestToUser(userRequest);
        return saveUser(user); //uso la funcion creada abajo

    }


    public Boolean deleteUser(Long id) throws Exception
    {
        try {
            if(existsUserById(id)){
                userRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encontro el ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



// Metodo para encontrar User por ID.
    public UserNecessaryResponse findUserById(Long id) throws Exception {
        try{
            User user = userRepository.findById(id)
                    .orElseThrow(()-> new Exception("No existe el Usuario con el ID:" + id));
            return mapUserToUserNecessaryResponse(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


// Metodo que lista todos los User.
    public List<UserNecessaryResponse> listAll() throws Exception
    {
        try
        {
            List<User> users=userRepository.findAll();
            List<UserNecessaryResponse> usersNecessaryResponse = new ArrayList<>();
            for(User user: users)
            {
                usersNecessaryResponse.add(mapUserToUserNecessaryResponse(user));
            }
            return usersNecessaryResponse;
        } catch (Exception e) {
            throw new Exception("Error al listar Usuarios" + e.getMessage());
        }
    }

    // Metodo para actualizar User.
    public UserNecessaryResponse updatedUser(Long id, UserResponse userResponse) throws Exception {
        try {
            User existingUser = findExistingUserById(id);
            existingUser.setType(userResponse.getType());
            existingUser.setMail(userResponse.getMail());
            existingUser.setName(userResponse.getName());
            existingUser.setLastName(userResponse.getLastName());
            existingUser.setPassword(userResponse.getPassword());
            existingUser.setIdentityDoc(userResponse.getIdentityDoc());
            existingUser.setPhoneNumber(userResponse.getPhoneNumber());
            existingUser.setEmergencyPhoneNumber(userResponse.getEmergencyPhoneNumber());
            existingUser.setBirthdate(userResponse.getBirthdate());
            existingUser.setPlaceOfBirth(userResponse.getPlaceOfBirth());
            existingUser.setGender(userResponse.getGender());
            saveUser(existingUser);
            return mapUserToUserNecessaryResponse(existingUser);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
//Metodo que trae un User por id (name, lastName, email)
    public UserNecessaryResponse getUserNecessaryById(Long id) throws Exception {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(()-> new Exception("No existe el Usuario con el ID:" + id));
            return mapUserToUserNecessaryResponse(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
// Metodo que devuelve una Lista de usuarios necesarios (name, lastName, email)
    public List<UserNecessaryResponse> getAllUserNecessary()throws Exception{
        try {
            List<User> users = userRepository.findAll();
            List<UserNecessaryResponse> usersNecessariesResponses = new ArrayList<>();
            for (User user : users) {
                usersNecessariesResponses.add(mapUserToUserNecessaryResponse(user));
            } return usersNecessariesResponses;
        } catch (Exception e) {
            throw new Exception("Error al listar Usuarios Necesarios" + e.getMessage());
        }
    }

    // Metodo para verificar si existe User por su email
    private Boolean existsUserBymail(String mail) {
        return userRepository.existsByMail(mail);
    }

    // Metodo que llama al mapper y mapea UserRequest a User
    private User mapUserRequestToUser(UserRequest userRequest){
        return userMapper.toUser(userRequest);
    }

    // Metodo para guardar el User creado o actualizado
    private User saveUser (User user){
        return userRepository.save(user);
    }

    // Metodo para saber si existe User por ID.
    private Boolean existsUserById(Long id){
        return userRepository.existsById(id);
    }

    // Metodo para encontrar User por email.
    public User findUserByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    // Metodo que llama al UserMapper y mapea el model de User en Response.
    private UserNecessaryResponse mapUserToUserNecessaryResponse(User user){
        return userMapper.toUserResponseNecessary(user);
    }

    // Metodo para encontrar un User por su ID.
    public User findExistingUserById(Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(()-> new Exception("Usuario no existe con el ID: " + userId));
    }

    // Metodo para encontrar un User en base a Type.
    public User findUserByType(String userType) {
        return userRepository.findByType(userType);
    }

    // Metodo para que devuelva un StudentsResponse en base al ID de Subject.
    public List<StudentResponse> getListStudent(Long idTeacher, String careerName, String subjectName){
        List<StudentResponse> studentsResponse = new ArrayList<>();
        List<Subject> subjects = subjectService.findSubjectByIdTeacherNameSubject(idTeacher, subjectName);
        for(Subject subject : subjects){
            Career career = careerService.findCareerByIdSubjectNameCareer(subject.getId(), careerName);
            if(careerName.equals(career.getName()) && subjectName.equals(subject.getName())){
                studentsResponse = findListStudentBySubject(subject.getId());
            }
        }
        return studentsResponse;
    }
    private List<StudentResponse> findListStudentBySubject(Long idSubject){
        List<StudentResponse> studentResponseList = new ArrayList<>();
        List<AcademicRecord> academicRecordList = getListAcademicRecordByIdSubject(idSubject);
        for(AcademicRecord academicRecord : academicRecordList){
            StudentResponse studentResponse = new StudentResponse(
                    academicRecord.getStudent().getName(),
                    academicRecord.getStudent().getLastName(),
                    academicRecord.getNote());
            studentResponseList.add(studentResponse);
        }
        return studentResponseList;
    }
    private List<AcademicRecord> getListAcademicRecordByIdSubject(Long idSubject){
        return academicRecordService.findListAcademicRecordByIdSubject(idSubject);
    }
}
