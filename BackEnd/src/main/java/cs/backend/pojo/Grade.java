package cs.backend.pojo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "grade")
public class Grade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Integer gradeId;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "score")
    private BigDecimal score;

    @Column(name = "exam_type")
    private String examType;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "record_time", insertable = false, updatable = false)
    private LocalDateTime recordTime;


    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
    }
}