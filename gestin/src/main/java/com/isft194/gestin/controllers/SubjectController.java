package com.isft194.gestin.controllers;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/apigestin/subject")
public class SubjectController extends BaseController<Subject, SubjectService>
{

    @Autowired
    public SubjectService subjectService;


}
