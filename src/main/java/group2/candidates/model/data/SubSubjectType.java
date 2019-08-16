package group2.candidates.model.data;

import lombok.Getter;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "subsubjecttype")
public class SubSubjectType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "subsubjecttypeid")
    private Integer subSubjectTypeId;

    @Column(name = "subsubjecttypename")
    private String subSubjectTypeName;
}
