package com.example.homework36.controller;


import com.example.homework36.record.FacultyRecord;
import com.example.homework36.record.StudentRecord;
import com.example.homework36.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
            }
@PostMapping
    public StudentRecord create(@RequestBody @Valid StudentRecord studentRecord){
        return studentService.create(studentRecord);
    }

    @GetMapping("/{id}")
    public StudentRecord read(@PathVariable long id){
        return studentService.read(id);
    }

    @PutMapping("/{id}")
    public StudentRecord update(@PathVariable long id,
                          @RequestBody @Valid StudentRecord studentRecord){
        return studentService.update(id, studentRecord);
    }

    @DeleteMapping("/{id}")
    public StudentRecord delete(@PathVariable long id){
        return studentService.delete(id);
    }

    @GetMapping(params = "age")
    public Collection<StudentRecord> findByAge(@RequestParam int age){
        return studentService.findByAge(age);
    }

    @GetMapping(params = {"minAge", "maxAge"})
    public Collection<StudentRecord> findByAgeBetween(@RequestParam int minAge,
                                                      @RequestParam int maxAge){
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/{id}/faculty")
    public FacultyRecord getFacultyByStudent(@PathVariable long id){
        return studentService.getFacultyByStudent(id);
    }

    @PatchMapping("/{id}/avatar")
    public StudentRecord patchStudentAvatar(@PathVariable long id,
                             @RequestParam("avatarI d") long avatarId){
        return studentService.patchStudentAvatar(id, avatarId);
    }

}
