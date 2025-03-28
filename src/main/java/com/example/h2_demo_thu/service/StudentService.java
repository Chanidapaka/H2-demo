package com.example.h2_demo_thu.service;

import com.example.h2_demo_thu.entities.Student;
import com.example.h2_demo_thu.repositories.StudentRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepositories studentRepo;

    //ดึงข้อมูลนักศึกษาทั้งหมดจากฐานข้อมูลและคืนค่ากลับเป็น List<Student>.
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    //ค้นหานักศึกษาตาม id หากไม่พบจะโยนข้อผิดพลาด (ResourceNotFoundException)
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student id " + id + " not found")
        );
    }

    //เพิ่มข้อมูลนักศึกษาใหม่เข้าไปในฐานข้อมูล
    public Student addStudent(Student students) {
        return studentRepo.save(students);
    }

    //ตรวจสอบว่ามีนักศึกษาที่มี id นี้อยู่ในฐานข้อมูลหรือไม่ ถ้าไม่มีจะโยนข้อผิดพลาด
    private void checkExistingStudent(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new ResourceNotFoundException("Student id " + id + " not found");
        }
    }

    //อัพเดตข้อมูลนักศึกษาที่มี id เดิมในฐานข้อมูล
    public Student updateStudent(Long id, Student student) {
        getStudentById(id);
        student.setId(id);
        return studentRepo.save(student);
    }

    //ลบข้อมูลนักศึกษาตาม id ที่ระบุ
    public void deleteStudent(Long id) {
        checkExistingStudent(id);
        studentRepo.deleteById(id);
    }

    //ฟังก์ชันนี้ใช้เพิ่มข้อมูลนักศึกษาหลายคนลงในฐานข้อมูล โดยใช้ saveAll() ซึ่งจะบันทึกหลายๆ รายการพร้อมกัน.
    public List <Student> addAll(List <Student> students) {
        return studentRepo.saveAll(students); //save ทุกคน
    }
}
