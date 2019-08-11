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
@Table(name = "Faculty")
public class Faculty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "FacultyId")
    private Integer facultyId;

    @Column(name = "FacultyCode", nullable = false)
    private String facultyCode;
    @Column(name = "Name")
    private String name;
}
