package cs.backend.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="body")
    private String body;
    @Column(name="author")
    private String author;
    @Column(name="create_time")
    private String create_date;
    @Column(name="label")
    private String label;

}
