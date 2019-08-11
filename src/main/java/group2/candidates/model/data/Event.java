package group2.candidates.model.data;

import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "EventId")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer eventId;

    @OneToMany(mappedBy = "event", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    private Set<Section> candidates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CampusLinkProgramCode", referencedColumnName = "Code")
    @Setter
    private CampusLinkProgram campusLinkProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubSubjectTypeId")
    private SubSubjectType subSubjectType;

    @Column(name = "CourseCode", unique = true)
    private String courseCode;
    @Transient private String courseName;
    @Column(name = "BudgetCode")
    private String budgetCode;
    @Column(name = "SubjectType")
    private String subjectType;
    @Column(name = "FormatType")
    private String formatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Supplier", referencedColumnName = "UniversityId")
    private University supplier;

    @Column(name = "PlannedStartDate")
    private LocalDate plannedStartDate;
    @Column(name = "PlannedEndDate")
    private LocalDate plannedEndDate;
    @Column(name = "PlannedExpense")
    private String plannedExpense;
    @Column(name = "ActualStartDate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate actualStartDate;
    @Column(name = "ActualEndDate")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate actualEndDate;
    @Column(name = "ActualLearningTime")
    private Integer actualLearningTime;

    @Transient private int actualNumberOfTrainees; //plannedNumberOfStudents - attend with status Canceled
    @Transient private int actualNumberOfEnrolled; //plannedNumberOfStudents - attend with status Drop-out
    @Transient private int numberOfCandidateCitified; //total of attend with status Passed

    @Column(name = "ActualExpense")
    private String actualExpense;
    @Column(name = "TrainingFeedBack")
    private String trainingFeedback;
    @Column(name = "TrainingFeedBackTeacher")
    private String trainingFeedbackTeacher;
    @Column(name = "TrainingFeedBackContent")
    private String trainingFeedbackContent;
    @Column(name = "TrainingFeedBackOrganization")
    private String trainingFeedbackOrganization;
    @Column(name = "Note")
    private String note;
    @Column(name = "EventStatus")
    private String eventStatus;

    @PrePersist
    public void setEventStatus() {
        if (plannedStartDate == null) plannedStartDate = LocalDate.now();
        if (plannedEndDate == null) plannedEndDate = LocalDate.now();
        actualStartDate = actualStartDate == null ? plannedStartDate : actualStartDate;
        actualEndDate = actualEndDate == null ? plannedEndDate : actualEndDate;
        updateEventStatus();
    }

    @PreUpdate
    public void updateCourseCode() {
        var currentCountOfEvent = courseCode.substring(courseCode.lastIndexOf("_") + 1);
        var year  = plannedStartDate.getYear() % 100;

        courseCode = String.join("_", supplier.getUniversityCode(), campusLinkProgram.getCode(),
                subSubjectType.getSubSubjectTypeName(), supplier.getSite() + year, currentCountOfEvent);
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

    private void updateEventStatus() {
        if (eventStatus.equals("Cancel")) return;
        var current = LocalDate.now();

        if (current.isAfter(actualEndDate) || current.equals(actualEndDate)) {
            eventStatus = "Finish";
            return;
        }

        if (current.isAfter(actualStartDate) || current.equals(actualStartDate)) {
            eventStatus = "On-going";
            return;
        }
        eventStatus = "Planning";
    }
}
