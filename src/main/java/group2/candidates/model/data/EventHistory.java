package group2.candidates.model.data;

import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "eventhistory")
public class EventHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "eventhistoryid")
    private Integer eventHistoryId;

    @Column(name = "updatedby")
    private String updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventid")
    private Event oldEvent;

    @Column(name = "updateddate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate updatedDate;
    @Column(name = "databackup", length = 100000)
    private String dataBackUp;
}
