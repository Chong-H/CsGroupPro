package cs.backend.pojo;

import jakarta.persistence.*;

@Table(name = "classes")
@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer headTeacherId;

    @Column(nullable = false)
    private String creationDate;


    public Class() {
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeadTeacherId() {
        return headTeacherId;
    }

    public void setHeadTeacherId(Integer headTeacherId) {
        this.headTeacherId = headTeacherId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}