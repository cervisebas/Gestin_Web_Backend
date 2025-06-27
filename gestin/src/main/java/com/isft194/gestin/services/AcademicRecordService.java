package com.isft194.gestin.services;

import com.isft194.gestin.repositories.IAcademicRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicRecordService {

    @Autowired
    private IAcademicRecordRepository academicRecordRepository;
}
