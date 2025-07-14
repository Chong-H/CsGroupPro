package cs.backend.pojo;

import jakarta.persistence.*;

@Table(name = "classes")
@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id") // 映射数据库的 class_id 字段
    private Integer clazzId;

    @Column(name = "name", nullable = false) // 映射 name 字段，非空
    private String name;

    @Column(name = "head_teacher_id") // 映射 head_teacher_id 字段
    private Integer headTeacherId;

    @Column(name = "creation_date", nullable = false) // 映射 creation_date 字段，非空
    private String creationDate;



    public Clazz() {
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
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