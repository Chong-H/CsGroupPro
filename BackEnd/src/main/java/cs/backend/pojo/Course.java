package cs.backend.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "courses")
@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(nullable = false)
    private String name;

    @Column()
    private Integer classId;
    @Column()
    private Integer teacherId;
    @Column()
    private String location;
    @Column()
    private String semester;
    @Column()
    private Integer schedule;

}