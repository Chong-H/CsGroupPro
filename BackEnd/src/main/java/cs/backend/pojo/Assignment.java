package cs.backend.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Integer assignmentId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "clazz_id")
    private Integer clazzId;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "creation_time", updatable = false)
    private LocalDateTime creationTime;

    @Column(name = "due_time")
    private LocalDateTime dueTime;

}