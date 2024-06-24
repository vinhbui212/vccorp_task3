package thuctap.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import thuctap.task2.model.Student;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s ORDER BY s.gpa DESC")
    List<Student> arrangeByGPA();

    @Query("SELECT s FROM Student s ORDER BY s.name DESC")
    List<Student> arrangeByName();
}
