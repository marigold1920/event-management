package group2.candidates.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Access(AccessType.FIELD)
@Table(name = "sectionpk")
public class SectionPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "eventid") private Integer eventId;
    @Column(name = "candidateid") private Integer candidateId;
}
