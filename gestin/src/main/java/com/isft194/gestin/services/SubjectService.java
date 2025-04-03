package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.SubjectRequest;
import com.isft194.gestin.dtos.response.SubjectResponse;
import com.isft194.gestin.dtos.response.SubjectsResponse;
import com.isft194.gestin.mappers.SubjectMapper;
import com.isft194.gestin.models.AcademicRecord;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.repositories.IAcademicRecordRepository;
import com.isft194.gestin.repositories.IRepository;
import com.isft194.gestin.repositories.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService extends ServiceIMPL<Subject, Long> {

    @Autowired
    public ISubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    protected SubjectService(IRepository<Subject, Long> iRepository, SubjectMapper subjectMapper) {
        super(iRepository);
        this.subjectMapper = subjectMapper;
    }

    public Subject createSubject(SubjectRequest subjectRequest) throws Exception{
        try{
            return saveSubject(mapToSubject(subjectRequest));
        } catch (Exception e) {
            throw new Exception("Error al crear una Materia: " + e.getMessage());
        }
    }

// Metodo que utiliza el SubjectMapper y pasa de Request a Model.
    private Subject mapToSubject(SubjectRequest subjectRequest){
        return subjectMapper.toSubject(subjectRequest);
    }

// Metodo para guardar lo recien creado o actualizado de Subject.
    private Subject saveSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public SubjectResponse updateSubject(Long id, SubjectRequest subjectRequest) throws Exception{
        try{
            Subject existingSubject = findSubjectById(id);
            existingSubject.setName(subjectRequest.getName());
            Subject updatedSubject = saveSubject(existingSubject);
            return mapToSubjectResponse(updatedSubject);
        } catch (Exception e){
            throw new Exception("Error al actualizar la Materia: " + e.getMessage());
        }
    }

    private SubjectResponse mapToSubjectResponse(Subject subject){
        return subjectMapper.toSubjectResponse(subject);
    }

    public boolean deleteSubject(Long id) throws Exception{
        try{
            if(existSubjectById(id)){
                subjectRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encuentra la Materia con el ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar la Materia: " + e.getMessage());
        }
    }

// Metodo para verificar si existe Subject con ese ID:
    private Boolean existSubjectById(Long id){
        return subjectRepository.existsById(id);
    }

    public SubjectsResponse listAllSubjects() throws Exception{
        try{
            List<Subject> subjectList = subjectRepository.findAll();
            return mapToSubjectsResponse(subjectList);
        } catch (Exception e) {
            throw new Exception("Error al listar Materias: " + e.getMessage());
        }
    }

// Metodo que mapea una lista de Subject a una list de SubjectRespone.
    private SubjectsResponse mapToSubjectsResponse(List<Subject> subjectsList){
        List<SubjectResponse> subjectsResponse = subjectsList.stream()
                .map(subjectMapper::toSubjectResponse)
                .collect(Collectors.toList());
        return new SubjectsResponse(subjectsResponse);
    }

    public SubjectResponse getSubjectById(Long id) throws Exception{
        try{
            return mapToSubjectResponse(findSubjectById(id));
        } catch (Exception e) {
            throw new Exception("Error al encontrar la Materia con el ID: " + e.getMessage());
        }
    }

// Metodo para mapear una lista de SubjectRequest a Modelo de Subject.
    public List<Subject> mapSubjects(List<SubjectRequest> subjectsRequests) throws Exception{
        List<Subject> subjects = new ArrayList<>();
        for(SubjectRequest subjectRequest : subjectsRequests){
            Subject subject = findSubjectById(subjectRequest.getId());
            subjects.add(subject);
        }
        return subjects;
    }

//Metodo que encuentra Subject a traves del ID.
    public Subject findSubjectById(Long id) throws Exception{
        return subjectRepository.findById(id)
                .orElseThrow(()-> new Exception("No se encontro la Materia con el ID:" + id));
    }

// Metodo para encontrar una lista de Subjects por el ID de User(Teacher).
    public List<Subject> findSubjectsByIdUser(Long idTeacher){
        return subjectRepository.findAllSubjectByTeacherId(idTeacher);
    }

    public List<Subject> findSubjectByIdTeacherNameSubject(Long idTeacher, String subjectName){
        return subjectRepository.findAllSubjectByTeacherIdAndName(idTeacher, subjectName);
    }

}
