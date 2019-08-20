package group2.candidates.model.data;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "departmentid")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer departmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultyid")
    @Getter private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universityid")
    @Getter private University university;

    @Column(name = "cooperationstartdate")
    // @Convert(converter = LocalDatePersistenceConverter.class)
    private String cooperationStartDate;
    @Column(name = "level")
    private Integer level;
}
