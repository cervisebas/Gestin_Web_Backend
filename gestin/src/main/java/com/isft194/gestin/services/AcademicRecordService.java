package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.AcademicRecordRequest;
import com.isft194.gestin.dtos.request.StudentWithNoteRequest;
import com.isft194.gestin.dtos.request.StudentsRequest;
import com.isft194.gestin.dtos.response.AcademicRecordListResponse;
import com.isft194.gestin.dtos.response.AcademicRecordResponse;
import com.isft194.gestin.dtos.response.StudentSubjectResponse;
import com.isft194.gestin.mappers.AcademicRecordMapper;
import com.isft194.gestin.models.AcademicRecord;
import com.isft194.gestin.models.Career;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;
import com.isft194.gestin.repositories.IAcademicRecordRepository;
import com.isft194.gestin.repositories.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicRecordService extends ServiceIMPL<AcademicRecord, Long>{

    private final AcademicRecordMapper academicRecordMapper;

    @Autowired
    private IAcademicRecordRepository academicRecordRepository;

    @Autowired
    private CareerService careerService;

    @Autowired
    private SubjectService subjectService;

    protected AcademicRecordService(IRepository<AcademicRecord, Long> iRepository, AcademicRecordMapper academicRecordMapper) {
        super(iRepository);
        this.academicRecordMapper = academicRecordMapper;
    }

    private AcademicRecord newAcademicRecord(AcademicRecordRequest academicRecordRequest) throws Exception{
        try{
            //User existingStudent = findExistingUser(academicRecordRequest.getUserRequestId().getId());
            Subject existingSubject = findExistingSubject(academicRecordRequest.getSubjectRequestId().getId());

            AcademicRecord academicRecord = mapAcademicRecordRequestToAcademicRecord(academicRecordRequest);

            //academicRecord.setStudent(existingStudent);
            academicRecord.setSubject(existingSubject);
            return saveAcademicRecord(academicRecord);
        } catch (Exception e) {
            throw new Exception("Ocurrio un error al crear el Registro Academico: " + e.getMessage());
        }
    }

    // Metodo que trae mapeado el modelo de AcademicRecord.
    private AcademicRecord mapAcademicRecordRequestToAcademicRecord(AcademicRecordRequest academicRecordRequest) throws Exception {
        return academicRecordMapper.fromRequestToObj(academicRecordRequest);
    }
    // Metodo para guardar el nuevo o actualizado AcademicRecord.
    private AcademicRecord saveAcademicRecord(AcademicRecord academicRecord){
        return academicRecordRepository.save(academicRecord);
    }

    //Esta funcion devuelve una lista response de AcademicRecord
    public List<AcademicRecordResponse> listAllAcademicRecord() throws Exception{
        try{
            List<AcademicRecord> academicRecordList = academicRecordRepository.findAll();
            return listAcademicRecordResponse(academicRecordList);
        } catch (Exception e) {
            throw new Exception("Ocurrio un error al listar el Registro Academico: " + e.getMessage());
        }
    }

    // Metodo que usa el mapper, envia el modelo de AcademicRecord y trae mapeado AcedemicRecordResponse.
    private AcademicRecordResponse toAcademicRecordResponse(AcademicRecord academicRecord){
        return academicRecordMapper.fromObjToResponse(academicRecord);
    }

    // Metodo que mapea de un model a un response el AcademicRecord y los guarda en una lista.
    private List<AcademicRecordResponse> listAcademicRecordResponse(List<AcademicRecord> academicRecordList){
        List<AcademicRecordResponse> academicRecordResponseList = new ArrayList<>();
        for(AcademicRecord academicRecord: academicRecordList) {
            academicRecordResponseList.add(toAcademicRecordResponse(academicRecord));
        }
        return academicRecordResponseList;
    }

    //Busca si existe el id y lo borra, en caso de no existir arroja un mensaje
    public boolean deleteAcademicRecord(Long id) throws Exception{
        try{
            if(existAcademicRecordById(id)){
                academicRecordRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encontro el ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

// Metodo que verifica que exista AcademicRecord en base al ID.
    private Boolean existAcademicRecordById(Long id){
        return academicRecordRepository.existsById(id);
    }

    //busca si existe un AcademicRecord por id, lo guarda asigna a un nuevo objeto
    //lo guarda en un objeto actualizado y llama al mapper para devolver el response
    public AcademicRecordResponse updateAcademicRecord(Long id, AcademicRecordRequest academicRecordRequest) throws Exception{
        try{
            AcademicRecord existingAcademicRecord = getAcademicRecordById(id);
            existingAcademicRecord.setNote(academicRecordRequest.getNote());
            existingAcademicRecord.setFinalGrade(academicRecordRequest.getFinalGrade());
            existingAcademicRecord.setFirstQuarterGrade(academicRecordRequest.getFirstQuarterGrade());
            existingAcademicRecord.setSecondQuarterGrade(academicRecordRequest.getSecondQuarterGrade());
            AcademicRecord updatedAcademicRecord = academicRecordRepository.save(existingAcademicRecord);
            return toAcademicRecordResponse(updatedAcademicRecord);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el Registro Academico: " + e.getMessage());
        }
    }

// Metodo para encontrar AcademicRecord por ID.
    private AcademicRecord getAcademicRecordById(Long id) throws Exception{
        return academicRecordRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el ID: " + id));
    }

    //trae un academicRecordResponse por id
    public AcademicRecordResponse findAcademicRecordById(Long id) throws Exception{
        try{
            AcademicRecord academicRecord = getAcademicRecordById(id);
            return toAcademicRecordResponse(academicRecord);
        } catch (Exception e) {
            throw new Exception("Error al encontrar el Registro Academico" + e.getMessage());
        }
    }

    //Metodo para traer un Usuario por id desde UserService.
    //private User findExistingUser(Long userId) throws Exception {
      //  return userService.findExistingUserById(userId);
    //}

    // Metodo para traer Subject por id desde SubjectService.
    private Subject findExistingSubject(Long subjectId) throws Exception {
        return subjectService.findSubjectById(subjectId);
    }

    //metodo para traer registros por estudiante
    public List<AcademicRecordResponse> findAcademicRecordsByStudentId(Long studentId){
        List<AcademicRecord> academicRecord = academicRecordRepository.findAllByStudentId(studentId);
        return listAcademicRecordResponse(academicRecord);
    }

    // Metodo para traer una List de AcademicRecord en base al ID de Subject.
    public List<AcademicRecord> findListAcademicRecordByIdSubject(Long idSubject){
        return academicRecordRepository.findAllBySubjectId(idSubject);
    }

    public List<StudentSubjectResponse> getStudentByTeacherAndSubject(Long subjectId, Long teacherId) throws Exception {
        try{
            List<StudentSubjectResponse> studentSubjectResponseList = new ArrayList<>();

            //Obtener todos los registros academicos de la materia
            List<AcademicRecord> academicRecords = academicRecordRepository.findAllBySubjectIdAndTeacherId(subjectId, teacherId);

            //Extraer los estudiantes de los registros academicos
            for(AcademicRecord academicRecord : academicRecords){
                User student = academicRecord.getStudent();

                //Crear la respuesta para el estudiante y la materia
                StudentSubjectResponse response = new StudentSubjectResponse(
                        student.getName(), // Tomas el nombre del estudiante
                        academicRecord.getSubject().getName() // Tomas el nombre de la materia
                );
                studentSubjectResponseList.add(response);
            }

            return studentSubjectResponseList;

        } catch (Exception e) {
            throw new Exception("Error al traer los datos");
        }
    }

    public Boolean updateStudents(StudentWithNoteRequest student){
        //Buscar la materia en baseal idTeacher y nombre del Subject.
        Boolean status = false;
        List<Subject> subjects = subjectService.findSubjectByIdTeacherNameSubject(student.getIdTeacher(), student.getNameSubject());
        for(Subject subject : subjects){
            Career career = careerService.findCareerByIdSubjectNameCareer(subject.getId(), student.getNameCareer());
            if(student.getNameCareer().equals(career.getName()) && student.getNameSubject().equals(subject.getName())){
                status = updateNoteInStudents(subject.getId(), student.getStudentsRequestList());
            }
        }
        return status;
    }
    private Boolean updateNoteInStudents(Long idSubject, List<StudentsRequest> studentsRequestList){
        Boolean status = true;
        List<AcademicRecord> academicRecordList = findListAcademicRecordByIdSubject(idSubject);
        for(AcademicRecord academicRecord : academicRecordList){
            for(StudentsRequest studentsRequest : studentsRequestList){
                if(academicRecord.getStudent().getName().equals(studentsRequest.getNameStudent()) && academicRecord.getStudent().getLastName().equals(studentsRequest.getLastNameStudent())){
                    academicRecord.setNote(studentsRequest.getNote());
                    academicRecordRepository.save(academicRecord);
                }
            }
        }
        return status;
    }
}
