package group2.candidates.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Access(AccessType.FIELD)
public class SectionPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "EventId") private Integer eventId;
    @Column(name = "CandidateId") private Integer candidateId;
}
