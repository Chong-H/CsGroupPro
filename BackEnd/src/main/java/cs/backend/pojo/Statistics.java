package cs.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    private Long studentNumber;
    private Long techerNumber;
    private Long courseNumber;
    private Long classNumber;

}
