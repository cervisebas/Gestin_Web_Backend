package com.isft194.gestin.services;

import com.isft194.gestin.dtos.request.CareerRequest;
import com.isft194.gestin.dtos.request.SubjectRequest;
import com.isft194.gestin.dtos.response.CareerResponse;
import com.isft194.gestin.dtos.response.CareerSubjectResponse;
import com.isft194.gestin.dtos.response.SubjectResponse;
import com.isft194.gestin.mappers.CareerMapper;
import com.isft194.gestin.mappers.SubjectMapper;
import com.isft194.gestin.models.Career;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.repositories.ICareerRepository;
import com.isft194.gestin.repositories.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CareerService extends ServiceIMPL<Career, Long>{

    private final CareerMapper careerMapper;

    private final SubjectMapper subjectMapper;

    @Autowired
    private ICareerRepository careerRepository;

    @Autowired
    private SubjectService subjectService;

    protected CareerService(IRepository<Career, Long> iRepository, CareerMapper careerMapper, SubjectMapper subjectMapper) {
        super(iRepository);
        this.careerMapper = careerMapper;
        this.subjectMapper = subjectMapper;
    }

    // Metodo para crear una nueva carrera.
    public Career newCareer(CareerRequest careerRequest) throws Exception{
        try{
            Career career = mapCareer(careerRequest);
            List<Subject> subjects = getSubjects(careerRequest.getSubjectsRequest());
            career.setSubjects(subjects);
            return saveCareer(career);
        } catch (Exception e) {
            throw new Exception("Error al crear la nueva carrera: " + e.getMessage(), e);
        }
    }
// Metodo que mapea el CareerRequest a Career.
    private Career mapCareer(CareerRequest careerRequest){
        return careerMapper.toCareer(careerRequest);
    }

// Metodo para guardar la carrera.
    private Career saveCareer(Career career){
        return careerRepository.save(career);
    }

// Metodo para traer una Lista de Subjects desde el SubjectService.
    private List<Subject> getSubjects(List<SubjectRequest> subjectsRequest) throws Exception {
        return subjectService.mapSubjects(subjectsRequest);
    }

// Metodo para actualizar una carrera por id.
    public CareerResponse updateCareer(Long id, CareerRequest careerRequest) throws Exception {
        try {
            Career existingCareer = findCareerById(id);
            existingCareer.setName(careerRequest.getName());
            existingCareer.setTurn(careerRequest.getTurn());
            updateCareerSubjects(existingCareer, careerRequest.getSubjectsRequest());
            Career updatedCareer = saveCareer(existingCareer);
            return mapToCareerResponse(updatedCareer);
        } catch (Exception e){
            throw new Exception("Error al actualizar la carrera: " + e.getMessage(), e);
        }
    }

// Metodo para econtrar una Career por ID.
    private Career findCareerById(Long id) throws Exception{
        return careerRepository.findById(id)
                .orElseThrow(() -> new Exception("ID no encontrado: " + id));
    }

// Metodo para actualizar los Subjects de Career.
    private void updateCareerSubjects(Career career, List<SubjectRequest> subjectsRequest) throws Exception {
        Set<Long> newSubjectIds = subjectsRequest.stream()
                .map(SubjectRequest::getId)
                .collect(Collectors.toSet());

        // Elimina los Subjects que no están en el CareerRequest
        career.getSubjects().removeIf(subject -> !newSubjectIds.contains(subject.getId()));

        // Agrega o mantiene los Subjects del CareerRequest
        for (SubjectRequest subjectRequest : subjectsRequest) {
            if (career.getSubjects().stream().noneMatch(subject -> subject.getId().equals(subjectRequest.getId()))) {
                career.getSubjects().add(getSubject(subjectRequest.getId()));
            }
        }
    }

// Metodo para traer Subject por ID desde SubjectService.
    private Subject getSubject(Long id) throws Exception{
        return subjectService.findSubjectById(id);
    }

// Metodo que mapea el modelo Career a un Response de Career.
    private CareerResponse mapToCareerResponse(Career career){
        return careerMapper.toCareerResponse(career);
    }

// Metodo para hacer una lista de todas las carreras.
    public List<CareerResponse> listAllCareer() throws Exception{
        try{
            List<Career> careerList = careerRepository.findAll();
            return mapCareerToCareerResponseList(careerList);
        }  catch (Exception e){
            throw new Exception("Error al listar las carreras: " + e.getMessage(), e);
        }
    }

//Metodo que recibe una lista del modelo Career y lo de vuelve como una lista de CareerResponse.
    private List<CareerResponse> mapCareerToCareerResponseList(List<Career> careerList){
        List<CareerResponse> careerResponseList = new ArrayList<>();
        for(Career career : careerList){
            careerResponseList.add(mapToCareerResponse(career));
        }
        return careerResponseList;
    }

// Metodo para borrar una carrera por id.
    public boolean deleteCareer(Long id) throws Exception{
        try{
            if(existCareerById(id)){
                careerRepository.deleteById(id);
                return true;
            } else{
                throw new Exception("No se encontro el ID: " +id);
            }
        } catch (Exception e){
            throw new Exception("Error al eliminar la carrera: " + e.getMessage(), e);
        }
    }

// Metodo para verificar la existencia de la carrera por ID.
    private Boolean existCareerById(Long id){
        return careerRepository.existsById(id);
    }

// Metodo para encontrar una carrera por id.
    public CareerResponse getCareerResponseById(Long id) throws Exception{
        try{
            Career career = findCareerById(id);
            return mapToCareerResponse(career);
        }catch (Exception e){
            throw new Exception("Error al encontrar la carrera: " + e.getMessage(), e);
        }
    }
// Metodo para listar CareerName y SubjectName en base al ID Teacher.
    public List<CareerSubjectResponse> getListCareerSubjectResponse(Long idTeacher) throws Exception {
        try {
            List<CareerSubjectResponse> careerSubjectList = new ArrayList<>();
            // Encuentra Subejct por ID del profesor.
            List<Subject> subjects = subjectService.findSubjectsByIdUser(idTeacher);
            // Recorre Subject, para encontrar el nombre de Career en base al idSubject.
            for (Subject subject : subjects) {
                Career career = findCareerByIdSubject(subject.getId());
                CareerSubjectResponse response = new CareerSubjectResponse(career.getName(), subject.getName());
                careerSubjectList.add(response);
            }
            return careerSubjectList;
        } catch(Exception exception){
            throw new Exception("Error al listar la carrera con materias: " + exception.getMessage(), exception);
        }
    }


    // Metodo para encontrar Career por el ID Subject.
    public Career findCareerByIdSubject(Long idSubject){
        return careerRepository.findBySubjectId(idSubject);
    }

    public Career findCareerByIdSubjectNameCareer(Long idSubject, String nameCareer){
        return careerRepository.findBySubjectIdAndName(idSubject, nameCareer);
    }
}

