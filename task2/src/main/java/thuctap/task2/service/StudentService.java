package thuctap.task2.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import thuctap.task2.dto.StudentDTO;
import thuctap.task2.model.Student;
import thuctap.task2.repository.StudentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

        public void addStudent(StudentDTO studentDTO){
            Student student = new Student();
            student.setName(studentDTO.getName());
            student.setGender(studentDTO.getGender());
            student.setAge(studentDTO.getAge());
            student.setMath(studentDTO.getMath());
            student.setChemistry(studentDTO.getChemistry());
            student.setPhysics(studentDTO.getPhysics());

            double gpa = (studentDTO.getMath() + studentDTO.getChemistry() + studentDTO.getPhysics()) / 3;
            student.setGpa(gpa);

            String rank;
            if (gpa >= 8) {
                rank = "Gioi";
            } else if (gpa >= 6.5) {
                rank = "Kha";
            } else if (gpa >= 5) {
                rank = "TB";
            } else {
                rank = "Yeu";
            }
            student.setRank(rank);

            studentRepo.save(student);
        }


    public void updateStudent(StudentDTO studentDTO,int id){
        Student student=studentRepo.getById(id);

            student.setAge(studentDTO.getAge());
            student.setGender(studentDTO.getGender());
            student.setMath(studentDTO.getMath());
            student.setPhysics(studentDTO.getPhysics());
            student.setChemistry(studentDTO.getChemistry());
            studentRepo.save(student);

    }
    @Cacheable("studentsCache")
    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }

    @Cacheable(value = "studentsCache", key = "#id")
    public Student getStudentById(Integer id) {
        return studentRepo.findById(id).orElse(null);
    }

    public void delete(int id){

        studentRepo.deleteById(id);
    }
    public List<Student> sxGpa(){
        return studentRepo.arrangeByGPA();
    }
    public List<Student> sxTen(){
        return studentRepo.arrangeByName();
    }
}
