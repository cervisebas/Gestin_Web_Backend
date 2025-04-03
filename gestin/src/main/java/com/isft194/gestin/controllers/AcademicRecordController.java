package com.isft194.gestin.controllers;

import com.isft194.gestin.dtos.request.AcademicRecordRequest;
import com.isft194.gestin.dtos.request.StudentWithNoteRequest;
import com.isft194.gestin.dtos.response.AcademicRecordResponse;
import com.isft194.gestin.dtos.response.StudentSubjectResponse;
import com.isft194.gestin.models.AcademicRecord;
import com.isft194.gestin.services.AcademicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/apigestin/academicRecords")
public class AcademicRecordController extends BaseController<AcademicRecord,AcademicRecordService>
{

    @Autowired
    private AcademicRecordService academicRecordService;

    @GetMapping("/students/{studentId}")
    public List<AcademicRecordResponse> getSubjectsByStudent(@PathVariable Long studentId) {
        return academicRecordService.findAcademicRecordsByStudentId(studentId);
    }

    @PutMapping("/updateGrades/{id}")
    public ResponseEntity<AcademicRecordResponse> updateAcademicRecordGrades(@PathVariable Long id, @RequestBody AcademicRecordRequest academicRecordRequest) throws Exception{
            AcademicRecordResponse updatedAcademicRecord = academicRecordService.updateAcademicRecord(id, academicRecordRequest);
            return ResponseEntity.ok(updatedAcademicRecord);
    }

    @GetMapping("/getAllRecords")
    public ResponseEntity<List<AcademicRecordResponse>> getAllAcademicRecords() throws Exception{
        List<AcademicRecordResponse> showAcademicRecords = academicRecordService.listAllAcademicRecord();
        return ResponseEntity.status(HttpStatus.OK).body(showAcademicRecords);
    }

    //Testeado, Funciona (Devuelve una lista de AcademicRecords de una materia
    // con el nombre del estudiante y el nombre de la materia
    @GetMapping("/getAllStudentFromSubject")
    public ResponseEntity<List<StudentSubjectResponse>> getStudentsByTeacherAndSubject
            (@RequestParam Long subjectId, @RequestParam Long teacherId) throws Exception {
        try{
            List<StudentSubjectResponse> students = academicRecordService.getStudentByTeacherAndSubject(subjectId, teacherId);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/putStudentsNote")
    public ResponseEntity<?> updateStudentsNote(@RequestBody StudentWithNoteRequest studentWithNoteRequest) throws Exception{
        academicRecordService.updateStudents(studentWithNoteRequest);
        return ResponseEntity.status(HttpStatus.OK).body(("string"));
    }
}
