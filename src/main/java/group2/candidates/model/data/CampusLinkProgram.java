package group2.candidates.model.data;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "CampusLinkProgram")
public class CampusLinkProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Code", length = 50)
    private String code;
    @Column(name = "Name", length = 50, nullable = false)
    private String name;
    @Column(name = "LearningTime")
    private Integer learningTime;
}
