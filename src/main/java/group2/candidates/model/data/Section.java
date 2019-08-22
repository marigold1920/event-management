package group2.candidates.model.data;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "section")
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @Column(name = "sectionid")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Setter private Integer sectionId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventid")
    private Event event;

    @Setter
    @ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidateid")
    private Candidate candidate;

    @Column(name = "contracttype") //ENUMERATION
    private String contractType;
    @Column(name = "candidatestatus")
    @Setter private String candidateStatus;
    @Column(name = "finalgrade")
    private String finalGrade;
    @Column(name = "completionlevel")
    private String completionLevel;
    @Column(name = "certificatedid")
    private String certificatedId;
    @Column(name = "note")
    private String note;

    @OneToOne(fetch = FetchType.LAZY)
    SectionHistory sectionHistory;

    @PrePersist
    public void setStatus() {
        candidateStatus = candidateStatus == null ? "Active" : candidateStatus;
        contractType = contractType == null ? "No contract" : contractType;
    }
}
