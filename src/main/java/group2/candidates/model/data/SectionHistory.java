package group2.candidates.model.data;

import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "SectionHistory")
public class SectionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "SectionHistoryId")
    private Integer sectionHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CandidateId"),
        @JoinColumn(name = "EventId")
    })
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UpdatedBy", referencedColumnName = "Username")
    private Account updatedBy;

    @Column(name = "UpdatedDate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate updatedDate;
}
