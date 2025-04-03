package com.isft194.gestin.controllers;

import com.isft194.gestin.models.InscriptionPeriod;
import com.isft194.gestin.services.InscriptionPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apigestin/inscriptionPeriod")
public class InscriptionPeriodController extends BaseController<InscriptionPeriod, InscriptionPeriodService>
{
    @Autowired
    private InscriptionPeriodService inscriptionPeriodService;
}
