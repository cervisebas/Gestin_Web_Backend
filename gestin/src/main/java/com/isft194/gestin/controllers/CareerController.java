package com.isft194.gestin.controllers;

import com.isft194.gestin.dtos.response.CareerSubjectResponse;
import com.isft194.gestin.models.Career;
import com.isft194.gestin.models.User;
import com.isft194.gestin.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/apigestin/career")
public class CareerController extends BaseController<Career,CareerService>
{

    @Autowired
    private CareerService careerService;

    //Testeado, Funciona
    @GetMapping("/listCareerWithSubject")
    public ResponseEntity<List<CareerSubjectResponse>> getCareerSubjectByIdTeacher() throws Exception {
        //Elimine el parametro teacherId y en lugar de eso lo va a buscar al contexto de seguridad
        //trayendo el id del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long idTeacher = user.getId();
        List<CareerSubjectResponse> careerSubjectResponseList = careerService.getListCareerSubjectResponse(idTeacher);
        return ResponseEntity.status(HttpStatus.OK).body(careerSubjectResponseList);
    }


}
