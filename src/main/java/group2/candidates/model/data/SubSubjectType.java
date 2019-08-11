package group2.candidates.model.data;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "SubSubjectType")
public class SubSubjectType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "SubSubjectTypeId")
    private Integer subSubjectTypeId;

    @Column(name = "SubSubjectTypeName")
    private String subSubjectTypeName;
}
