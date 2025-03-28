package com.example.h2_demo_thu.controller;

import com.example.h2_demo_thu.entities.Student;
import com.example.h2_demo_thu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    //เรียก Post ที่มีRequestBody
    @PostMapping("")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED) //ส่ง 201
                .body(studentService.addStudent(student)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student)); //ส่ง200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id); //ไม่มี value
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //เพื่อที่จะได้List สร้าง เพิ่มมาหลายคน
    @PostMapping("/imports")
    public ResponseEntity<List<Student>> importStudents(
            @RequestBody List<Student> students) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.addAll(students));
    }
}
