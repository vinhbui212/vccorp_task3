package thuctap.task2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String name;
    private String gender;
    private int age;
    private double math;
    private double physics;
    private double chemistry;
    private double gpa;
    private String rank;
}
