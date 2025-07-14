package cs.backend.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "teacher_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "job_title")
    private String jobTitle;

    // 添加 CascadeType.ALL 和 orphanRemoval = true
    // CascadeType.ALL: 当我们保存/更新/删除学生时，相关的User也会被自动处理。
    // orphanRemoval = true: 如果一个User不再被任何Teacheer引用，它将被删除。
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "work_id", referencedColumnName = "work_id")
    private User user;

}
