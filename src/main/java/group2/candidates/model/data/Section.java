package group2.candidates.model.data;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Section")
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId @Setter private SectionPK sectionId;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "EventId", insertable = false, updatable = false)
    private Event event;

    @Setter
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "CandidateId", insertable = false, updatable = false)
    private Candidate candidate;

    @Column(name = "ContractType") //ENUMERATION
    private String contractType;
    @Column(name = "CandidateStatus")
    private String candidateStatus;
    @Column(name = "FinalGrade")
    private String finalGrade;
    @Column(name = "CompletionLevel")
    private String completionLevel;
    @Column(name = "CertificatedId")
    private String certificatedId;
    @Column(name = "Note")
    private String note;

    @PrePersist
    public void setStatus() {
        candidateStatus = candidateStatus == null ? "Active" : candidateStatus;
        sectionId = new SectionPK(event.getEventId(), candidate.getCandidateId());
    }

     @Override
     public boolean equals(Object obj) {
         if (this == obj) return true;
         if (!(obj instanceof Section)) return false;
         var section = (Section) obj;

         return this.sectionId.equals(section.getSectionId());
     }

     @Override
     public int hashCode() {
         return Objects.hash(sectionId);
     }
}
