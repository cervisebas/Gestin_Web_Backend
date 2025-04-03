package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.ExamRequest;
import com.isft194.gestin.dtos.response.ExamResponse;
import com.isft194.gestin.dtos.response.ExamsResponse;
import com.isft194.gestin.mappers.ExamMapper;
import com.isft194.gestin.mappers.SubjectMapper;
import com.isft194.gestin.models.Exam;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.models.User;
import com.isft194.gestin.repositories.IExamRepository;
import com.isft194.gestin.repositories.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService extends ServiceIMPL<Exam, Long>{

    private final ExamMapper examMapper;

    @Autowired
    private IExamRepository examRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    protected ExamService(IRepository<Exam, Long> iRepository, ExamMapper examMapper) {
        super(iRepository);
        this.examMapper = examMapper;
    }

    public Exam newExam(ExamRequest examRequest) throws Exception{
        try{
            User existingTeacher = findExistingUser(examRequest.getTeacher().getId());
            User existingFirstVocal = findExistingUser(examRequest.getFirstVocal().getId());
            User existingSecondVocal = findExistingUser(examRequest.getSecondVocal().getId());
            User existingThirdVocal = findExistingUser(examRequest.getThirdVocal().getId());
            Subject existingSubject = findExistingSubject(examRequest.getSubject().getId());

            Exam exam = mapToExamModel(examRequest);

            exam.setTeacher(existingTeacher);
            exam.setFirstVocal(existingFirstVocal);
            exam.setSecondVocal(existingSecondVocal);
            exam.setThirdVocal(existingThirdVocal);
            exam.setSubject(existingSubject);

            return saveExam(exam);
        } catch (Exception e) {
            throw new Exception("Error al crear un Exámen: " + e.getMessage());
        }
    }

// Metodo que utiliza el ExamMapper para pasar de Request a Model el Exam.
    private Exam mapToExamModel(ExamRequest examRequest){
        return examMapper.toExam(examRequest);
    }

// Metodo que guarda el nuevo o actualizado Exam.
    private Exam saveExam(Exam exam){
        return examRepository.save(exam);
    }

    public ExamsResponse listAllExams() throws Exception{
        try{
            List<Exam> examList = examRepository.findAll();
            return mapToExamsResponse(examList);
        } catch (Exception e) {
            throw new Exception("Error al listar Exámen: " + e.getMessage());
        }
    }

// Metodo que mapea una lista de Exam a una List de ExamRespone.
    private ExamsResponse mapToExamsResponse(List<Exam> examList){
        List<ExamResponse> examsResponse = examList.stream()
                .map(examMapper::toExamResponse)
                .collect(Collectors.toList());
        return new ExamsResponse(examsResponse);
    }

    public ExamResponse getExamById (Long id) throws Exception {
    try {
            return mapToExamResponse(findExamById(id));
        } catch (Exception e) {
            throw new Exception("Error al encontrar el Exámen: " + e.getMessage());
        }
    }

// Metodo que utiliza el ExamMapper para pasar de Exam(model) a ExamResponse.
    private ExamResponse mapToExamResponse(Exam exam){
        return examMapper.toExamResponse(exam);
    }

// Metodo que encuentra un Exam por ID.
    public Exam findExamById(Long id) throws Exception{
        return examRepository.findById(id)
                .orElseThrow(() -> new Exception("Error al encontrar el ID: " + id));
    }

    public ExamResponse updateExam (Long id, ExamRequest examRequest) throws Exception {
        try {
            Exam exam = findExamById(id);
            exam.setExamDate(examRequest.getExamDate());
            exam.setExamTime(examRequest.getExamTime());
            return mapToExamResponse(saveExam(exam));
        } catch (Exception e) {
            throw new Exception("Error al actualizar el Exámen: " + e.getMessage());
        }
    }

    public boolean deleteExam(Long id) throws Exception{
        try{
            if(existExamById(id)){
                examRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encontró el ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error al borrar el Exámen: " + e.getMessage());
        }
    }

// Metodo que verifica que existe Exam a traves del ID.
    private Boolean existExamById(Long id){
        return examRepository.existsById(id);
    }

// Metodo que usa SubjectService para traer Subject.
    private Subject findExistingSubject (Long subjectId) throws Exception {
        return subjectService.findSubjectById(subjectId);
    }

// Metodo que usa UserService para traer User.
    private User findExistingUser (Long userId) throws Exception {
        return userService.findExistingUserById(userId);
    }

}
