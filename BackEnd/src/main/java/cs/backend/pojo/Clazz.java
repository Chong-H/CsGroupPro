package cs.backend.pojo;

import jakarta.persistence.*;

@Table(name = "classes")
@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clazzId;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer headTeacherId;

    @Column(nullable = false)
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