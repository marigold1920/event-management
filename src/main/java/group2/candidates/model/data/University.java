package group2.candidates.model.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "University")
public class University implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "UniversityId")
    private Integer universityId;

    @Column(name = "HotKey")
    private String uniHotKey;
    @Column(name = "UniversityCode")
    private String universityCode;
    @Column(name = "UniversityName")
    private String universityName;
    @Column(name = "Site")
    private String site;
}
