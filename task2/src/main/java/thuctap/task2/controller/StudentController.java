package thuctap.task2.controller;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thuctap.task2.dto.StudentDTO;
import thuctap.task2.model.Student;
import thuctap.task2.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    @PostMapping
    public String addNewStudent(@RequestBody StudentDTO studentDTO){
        service.addStudent(studentDTO);
        return "Add student successful";
    }
    @PutMapping("/{id}")
    public String updateStudent(@RequestBody StudentDTO studentDTO,@PathVariable int id){
        service.updateStudent(studentDTO,id);
        return "Update student successful";
    }
    @GetMapping("/all")
    public List<Student> getAll(){

        return service.getAllStudent();
    }
    @GetMapping("/gpa")
    public List<Student>getAllByGpa(){
        return service.sxGpa();
    }
    @GetMapping("/name")
    public List<Student> getAllByName(){
        return service.sxTen();
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        service.delete(id);
        return "Delete student successful "+id;
    }
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id){
        return service.getStudentById(id);
    }

}
