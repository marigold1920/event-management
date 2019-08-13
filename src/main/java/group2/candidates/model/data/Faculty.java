package group2.candidates.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "faculty")
public class Faculty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "facultyid")
    private Integer facultyId;

    @Column(name = "facultycode", nullable = false)
    private String facultyCode;
    @Column(name = "name")
    private String name;
}
