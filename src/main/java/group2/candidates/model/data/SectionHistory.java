package group2.candidates.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "sectionhistory")
public class SectionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "sectionhistoryid")
    private Integer sectionHistoryId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "sectionid")
    private Section section;

    @Column(name = "updatedby")
    private String updatedBy;

    @Column(name = "updateddate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate updatedDate;

    @Column(name = "contracttype")
    private String contractType;

    @Column(name = "candidatestatus")
    @Setter
    private String candidateStatus;
    @Column(name = "finalgrade")
    private String finalGrade;
    @Column(name = "completionlevel")
    private String completionLevel;
    @Column(name = "certificatedid")
    private String certificatedId;
    @Column(name = "note")
    private String note;
}
