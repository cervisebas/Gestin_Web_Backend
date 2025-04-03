package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.EnrolmentExamRequest;
import com.isft194.gestin.dtos.response.EnrolmentExamResponse;
import com.isft194.gestin.dtos.response.EnrolmentExamsResponse;
import com.isft194.gestin.mappers.EnrolmentExamMapper;
import com.isft194.gestin.models.EnrolmentExam;
import com.isft194.gestin.repositories.IEnrolmentExamRepository;
import com.isft194.gestin.repositories.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrolmentExamService extends ServiceIMPL<EnrolmentExam, Long>{

    private final EnrolmentExamMapper enrolmentExamMapper;

    @Autowired
    private IEnrolmentExamRepository enrolmentExamRepository;

    public EnrolmentExamService(IRepository<EnrolmentExam, Long> iRepository, EnrolmentExamMapper enrolmentExamMapper) {
        super(iRepository);
        this.enrolmentExamMapper = enrolmentExamMapper;
    }

    public EnrolmentExam newEnrolmentExam(EnrolmentExamRequest enrolmentExamRequest) throws Exception {
        try {
            EnrolmentExam enrolmentExam = mapToEnrolentExam(enrolmentExamRequest);
            return saveEnrolmentExam(enrolmentExam);
        } catch (Exception e) {
            throw new Exception("Error al crear la Incrpcion a Examen: " + e.getMessage());
        }
    }

// Metodo que utiliza el mapper para pasar de request a model el EnrolmentExam.
    private EnrolmentExam mapToEnrolentExam(EnrolmentExamRequest enrolmentExamRequest) throws Exception{
        return enrolmentExamMapper.toEnrolmentExam(enrolmentExamRequest);
    }

// Metodo que guarda en la base de datos EnrolmentExam.
    private EnrolmentExam saveEnrolmentExam(EnrolmentExam enrolmentExam){
        return enrolmentExamRepository.save(enrolmentExam);
    }


    public boolean deleteEnrolmentExam(Long id) throws Exception {
        try {
            if (existEnromentExamById(id)) {
                enrolmentExamRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("Error no se encontró el ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar la Inscripción a Exámen: " + e.getMessage());
        }
    }

// Metodo para verificar si existe EnrolmentExam a traves del ID.
    private Boolean existEnromentExamById(Long id){
        return enrolmentExamRepository.existsById(id);
    }

    public EnrolmentExamsResponse listAllEnrolmentExams() throws Exception {
        try {
            List<EnrolmentExam> enrolmentExamList = enrolmentExamRepository.findAll();
            return mapEnrolmentExamList(enrolmentExamList);
        } catch (Exception e) {
            throw new Exception("Error al listar las Inscripciones a Examenes: " + e.getMessage());
        }
    }

// Metodo mapea una lista de EnrolmentExam(modelo) a una lista de EnrolmentExamResponse.
    private EnrolmentExamsResponse mapEnrolmentExamList(List<EnrolmentExam> enrolmentExamList){
        List<EnrolmentExamResponse> enrolmentExamResponses = enrolmentExamList.stream()
                .map(enrolmentExamMapper::toEnrolmentExamResponse)
                .collect(Collectors.toList());
        return new EnrolmentExamsResponse(enrolmentExamResponses);
    }

    public EnrolmentExamResponse updateEnrolmentExam(Long id, EnrolmentExamRequest examRequest) throws Exception {
        try {
            EnrolmentExam existingExam = findEnrolmentExamById(id);
                EnrolmentExam updatedExam = mapToEnrolentExam(examRequest);
                updatedExam.setId(existingExam.getId());
                saveEnrolmentExam(updatedExam);
                return mapToEnrolmentExamResponse(updatedExam);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el examen de inscripción: " + e.getMessage());
        }
    }

// Metodo que busca un EnromentExam por su ID.
    private EnrolmentExam findEnrolmentExamById(Long id) throws Exception{
        return enrolmentExamRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontro el ID: " + id));
    }

// Metodo que utiliza el mapper, para pasarle un model de EnrolmentExam y devuelve un EnrolmentExamResponse.
    private EnrolmentExamResponse mapToEnrolmentExamResponse(EnrolmentExam enrolmentExam){
        return enrolmentExamMapper.toEnrolmentExamResponse(enrolmentExam);
    }

    public EnrolmentExamResponse getEnrolmentExam(Long id) throws Exception {
        try {
            EnrolmentExam enrolmentExam = findEnrolmentExamById(id);
            return mapToEnrolmentExamResponse(enrolmentExam);
        } catch (Exception e) {
            throw new Exception("Error al obtener la Inscripción a Exámen: " + e.getMessage());
        }
    }
}
