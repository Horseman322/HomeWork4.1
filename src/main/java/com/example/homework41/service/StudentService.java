package com.example.homework36.service;



import com.example.homework36.component.RecordMapper;
import com.example.homework36.entity.Avatar;
import com.example.homework36.entity.Faculty;
import com.example.homework36.entity.Student;
import com.example.homework36.exception.AvatarNotFoundException;
import com.example.homework36.exception.StudentNotFoundException;
import com.example.homework36.record.FacultyRecord;
import com.example.homework36.record.StudentRecord;
import com.example.homework36.repository.AvatarRepository;
import com.example.homework36.repository.FacultyRepository;
import com.example.homework36.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    private final AvatarRepository avatarRepository;
    private final RecordMapper recordMapper;

    public  StudentService(StudentRepository studentRepository,
                           FacultyRepository facultyRepository,
                           AvatarRepository avatarRepository,
                          RecordMapper recordMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.avatarRepository = avatarRepository;
        this.recordMapper = recordMapper;
    }



    public StudentRecord create(StudentRecord studentRecord){
        Student student = recordMapper.toEntity(studentRecord);
        student.setFaculty(
        Optional.ofNullable(student.getFaculty())
                .map(Faculty::getId)
                .flatMap(facultyRepository::findById)
                .orElse(null));
        return recordMapper.toRecord(studentRepository.save(student));
    }

    public StudentRecord read(long id){
        return recordMapper.toRecord(studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id)));
    }

    public StudentRecord update(long id,
                                StudentRecord studentRecord){
        Student oldStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        oldStudent.setAge(studentRecord.getAge());
        oldStudent.setName(studentRecord.getName());
        return recordMapper.toRecord(studentRepository.save(oldStudent));
    }

    public StudentRecord delete(long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return recordMapper.toRecord(student);
    }

    public Collection<StudentRecord> findByAge(int age){
        return studentRepository.findAllByAge(age).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }


    public Collection<StudentRecord> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findAllByAgeBetween(minAge, maxAge).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public FacultyRecord getFacultyByStudent(long id) {
        return read(id).getFaculty();
    }

    public StudentRecord patchStudentAvatar(long id, long avatarId) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Optional<Avatar> optionalAvatar = avatarRepository.findById(avatarId);
        if (optionalStudent.isEmpty()){
            throw new StudentNotFoundException(id);
        }
        if (optionalAvatar.isEmpty()){
            throw new AvatarNotFoundException(id);
        }
        Student student = optionalStudent.get();
        student.setAvatar(optionalAvatar.get());
        return recordMapper.toRecord(studentRepository.save(studentRepository.save(student)));
    }
}
