package group2.candidates.model.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "university")
public class University implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "universityid")
    private Integer universityId;

    @Column(name = "hotkey")
    private String uniHotKey;
    @Column(name = "universitycode")
    private String universityCode;
    @Column(name = "universityname")
    private String universityName;
    @Column(name = "site")
    private String site;
}
