package group2.candidates.model.data;

import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "eventid")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer eventId;

    @OneToMany(mappedBy = "event", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    @Setter private Set<Section> candidates;

    @OneToMany(mappedBy = "oldEvent", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    private Set<EventHistory> histories;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campuslinkprogramcode", referencedColumnName = "code")
    private CampusLinkProgram campusLinkProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subsubjecttypeid")
    @Setter private SubSubjectType subSubjectType;

    @Setter @Column(name = "coursecode", unique = true)
    private String courseCode;
    @Transient private String courseName;
    @Column(name = "budgetcode")
    private String budgetCode;
    @Column(name = "subjecttype")
    private String subjectType;
    @Column(name = "formattype")
    private String formatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier", referencedColumnName = "universityid")
    @Setter private University supplier;

    @Column(name = "plannedstartdate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate plannedStartDate;
    @Column(name = "plannedenddate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate plannedEndDate;
    @Column(name = "plannedexpense")
    private String plannedExpense;
    @Column(name = "actualstartdate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate actualStartDate;
    @Column(name = "actualenddate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate actualEndDate;
    @Column(name = "actuallearningtime")
    private Integer actualLearningTime;

    @Transient private int actualNumberOfTrainees; //plannedNumberOfStudents - attend with status Canceled
    @Transient private int actualNumberOfEnrolled; //plannedNumberOfStudents - attend with status Drop-out
    @Transient private int numberOfCandidateCitified; //total of attend with status Passed

    @Column(name = "actualexpense")
    private String actualExpense;
    @Column(name = "trainingfeedback")
    private String trainingFeedback;
    @Column(name = "trainingfeedbackteacher")
    private String trainingFeedbackTeacher;
    @Column(name = "trainingfeedbackcontent")
    private String trainingFeedbackContent;
    @Column(name = "trainingfeedbackorganization")
    private String trainingFeedbackOrganization;
    @Column(name = "note")
    @Setter private String note;
    @Column(name = "eventstatus")
    @Setter private String eventStatus;

    @PrePersist
    private void setEventStatus() {
        setTime();
        updateEventStatus();
    }

    @PreUpdate
    private void updateCourseCode() {
        var currentCountOfEvent = courseCode.substring(courseCode.lastIndexOf("_") + 1);
        var year  = plannedStartDate.getYear() % 100;

        courseCode = String.join("_", supplier.getUniversityCode(), campusLinkProgram.getCode(),
                subSubjectType.getSubSubjectTypeName(), supplier.getSite() + year, currentCountOfEvent);
        setTime();
        updateEventStatus();
    }

    @PostLoad
    private void calculateTransientFields() {
        actualNumberOfTrainees = candidates.stream()
                .filter(s -> !s.getCandidateStatus().equals("Cancel"))
                .mapToInt(s -> 1)
                .sum();

        numberOfCandidateCitified = candidates.stream()
                .filter(s -> s.getCandidateStatus().equals("Passed"))
                .mapToInt(s -> 1)
                .sum();

        actualNumberOfEnrolled = actualNumberOfTrainees
                - candidates.stream()
                .filter(s -> s.getCandidateStatus().equals("Drop-out"))
                .mapToInt(s -> 1)
                .sum();
        updateEventStatus();
    }

    private void setTime() {
        if (plannedStartDate == null) plannedStartDate = LocalDate.now();
        if (plannedEndDate == null) plannedEndDate = LocalDate.now();
        if (actualStartDate == null) actualStartDate = plannedStartDate;
        if (actualEndDate == null) actualEndDate = plannedEndDate;
    }

    private void updateEventStatus() {
        if (eventStatus.equals("Cancel")) return;
        var current = LocalDate.now();

        if (current.isAfter(actualEndDate)) {
            eventStatus = "Done";
            return;
        }

        if (current.isAfter(actualStartDate) || current.equals(actualStartDate)) {
            eventStatus = "On-going";
            return;
        }
        eventStatus = "Planning";
    }

    public void addHistory(EventHistory history) {
        if (histories == null) histories = new HashSet<>();
        histories.add(history);
    }
}
