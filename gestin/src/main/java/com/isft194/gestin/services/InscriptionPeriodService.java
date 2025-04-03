package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.ExamRequest;
import com.isft194.gestin.dtos.request.InscriptionPeriodRequest;
import com.isft194.gestin.dtos.response.InscriptionPeriodResponse;
import com.isft194.gestin.mappers.InscriptionPeriodMapper;
import com.isft194.gestin.models.Exam;
import com.isft194.gestin.models.InscriptionPeriod;
import com.isft194.gestin.repositories.IInscriptionPeriodRepository;
import com.isft194.gestin.repositories.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InscriptionPeriodService extends ServiceIMPL<InscriptionPeriod, Long> {

    @Autowired
    private IInscriptionPeriodRepository inscriptionPeriodRepository;

    private final InscriptionPeriodMapper inscriptionPeriodMapper;

    @Autowired
    private ExamService examService;

    protected InscriptionPeriodService(IRepository<InscriptionPeriod, Long> iRepository, InscriptionPeriodMapper inscriptionPeriodMapper) {
        super(iRepository);
        this.inscriptionPeriodMapper = inscriptionPeriodMapper;
    }

    public InscriptionPeriod createInscriptionPeriod (InscriptionPeriodRequest inscriptionPeriodRequest) throws Exception{
        try{
             InscriptionPeriod inscriptionPeriod = mapToInscriptionPeriod(inscriptionPeriodRequest);

             if (isClosed(inscriptionPeriod)){
                 throw new Exception("El período de inscripción ya está cerrado.");
             }

             return saveInscriptionPeriod(inscriptionPeriod);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

// Metodo que utiliza InscriptionPeriodMapper para pasar de Request a Model.
    private InscriptionPeriod mapToInscriptionPeriod(InscriptionPeriodRequest inscriptionPeriodRequest){
        return inscriptionPeriodMapper.toInscriptionPeriod(inscriptionPeriodRequest);
    }

// Metodo para guardar lo nuevo o actualizado de InscriptionPeriod.
    private InscriptionPeriod saveInscriptionPeriod(InscriptionPeriod inscriptionPeriod){
        return inscriptionPeriodRepository.save(inscriptionPeriod);
    }

// Metodo que utiliza InscriptionPeriodMapper para pasar de Model a Resposne.
    private InscriptionPeriodResponse mapToInscriptionPeriodResponse(InscriptionPeriod inscriptionPeriod){
        return inscriptionPeriodMapper.toInscriptionPeriodResponse(inscriptionPeriod);
    }

// Metodo que obtiene InscriptionPeriod por ID y lo devuelve como Response
    public InscriptionPeriodResponse getInscriptionPeriodById (Long id) throws Exception {
        InscriptionPeriod inscriptionPeriod = findInscriptionPeriodById(id);
        return mapToInscriptionPeriodResponse(inscriptionPeriod);
    }

// Metodo que busca InscriptionPeriod por ID.
    private InscriptionPeriod findInscriptionPeriodById(Long id) throws Exception{
        return inscriptionPeriodRepository.findById(id)
                .orElseThrow(() -> new Exception("Error al encontrar el ID: " + id));
    }

    public List<InscriptionPeriodResponse> getAllInscriptionPeriod(){
        return inscriptionPeriodRepository.findAll().stream()
                .map(inscriptionPeriodMapper::toInscriptionPeriodResponse)
                .collect(Collectors.toList());
    }

    public InscriptionPeriodResponse updateInscriptionPeriod(Long id, InscriptionPeriodRequest inscriptionPeriodRequest) throws Exception {
        try {
        InscriptionPeriod existingInscriptionPeriod = findInscriptionPeriodById(id);
        existingInscriptionPeriod.setStartPeriod(inscriptionPeriodRequest.getStartPeriod());
        existingInscriptionPeriod.setEndPeriod(inscriptionPeriodRequest.getEndPeriod());
        updateInscriptionPeriodExams(existingInscriptionPeriod, inscriptionPeriodRequest.getExamsRequest());
        InscriptionPeriod updateInscriptionPeriod =  saveInscriptionPeriod(existingInscriptionPeriod);
        return mapToInscriptionPeriodResponse(updateInscriptionPeriod);
    } catch (Exception e){
            throw new Exception("Error al actualizar la carrera: " + e.getMessage(), e);
        }
    }

// Metodo para actualizar Exams de InscriptionPeriod.
    private void updateInscriptionPeriodExams(InscriptionPeriod inscriptionPeriod, List<ExamRequest> examRequests) throws Exception {
        Set<Long> newExamIds = examRequests.stream()
                .map(ExamRequest::getId)
                .collect(Collectors.toSet());

        // Elimina los Exams que no estan en el InscriptionPeriodRequest
        inscriptionPeriod.getExams().removeIf(exam -> !newExamIds.contains(exam.getExamId()));

        // Agrega los nuevos Exams del InscriptionPeriodRequest que no estan en la lista
        for (ExamRequest examRequest : examRequests) {
            if (inscriptionPeriod.getExams().stream().noneMatch(exam -> exam.getExamId().equals(examRequest.getId()))) {
                inscriptionPeriod.getExams().add(getExam(examRequest.getId())); // Agregar nuevos Exam
            }
        }
    }

    public boolean deleteInscriptionPeriod(Long id) throws Exception {
        try{
        InscriptionPeriod inscriptionPeriod = findInscriptionPeriodById(id);
        inscriptionPeriodRepository.delete(inscriptionPeriod);
        return true;
        } catch(Exception exception){
            throw new Exception("Error al eliminar el Periodo de Inscripción: " + exception.getMessage());
        }
    }

// Metodo que usa a ExamSerivce para obtener Exam por ID.
    private Exam getExam(Long id) throws Exception{
        return examService.findExamById(id);
    }
    // Método que verifica si el período está cerrado
    private boolean  isClosed(InscriptionPeriod inscriptionPeriod) {

        Date currentDate = new Date();// Fecha actual

        return inscriptionPeriod.getEndPeriod() != null && currentDate.after(inscriptionPeriod.getEndPeriod());
    }
}
