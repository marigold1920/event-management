package group2.candidates.model.data;

import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "EventHistory")
public class EventHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "EventHistoryId")
    private Integer eventHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UpdatedBy", referencedColumnName = "Username")
    private Account updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EventId")
    private Event event;

    @Column(name = "UpdatedDate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate updatedDate;
}
