package com.isft194.gestin.controllers;

import com.isft194.gestin.models.EnrolmentExam;
import com.isft194.gestin.services.EnrolmentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apigestin/enrolmentExam")
public class EnrolmentExamController extends BaseController<EnrolmentExam, EnrolmentExamService>
{

    @Autowired
    private EnrolmentExamService enrolmentExamService;

}
