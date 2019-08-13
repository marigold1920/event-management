package group2.candidates.model.data;

import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "candidatehistory")
public class CandidateHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "candidatehistoryid")
    private Integer candidateHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updatedby", referencedColumnName = "username")
    private Account updatedBy;

    @Column(name = "updateddate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateid")
    private Candidate candidate;
}
