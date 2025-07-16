package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Grade;
import cs.backend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("/batch")
    public ResponseMessage<List<Grade>> addGradesInBatch(@RequestBody List<Grade> grades) {
        try {
            List<Grade> savedGrades = gradeService.batchAddGrades(grades);
            return ResponseMessage.success(savedGrades);
        } catch (IllegalArgumentException e) {
            return ResponseMessage.error(400, e.getMessage(), null); // Bad Request
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "批量录入成绩失败", null);
        }
    }


    @GetMapping("/query")
    public ResponseMessage<List<Grade>> queryGrades(
            @RequestParam(required = false) Integer studentId,
            @RequestParam(required = false) Integer courseId,
            @RequestParam(required = false) String academicYear,
            @RequestParam(required = false) Integer semester,
            @RequestParam(required = false) String examType) {
        try {
            List<Grade> grades = gradeService.findGradesByCriteria(studentId, courseId, academicYear, semester, examType);
            return ResponseMessage.success(grades);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(500, "查询成绩失败", null);
        }
    }
}