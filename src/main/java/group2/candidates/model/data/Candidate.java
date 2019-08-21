package group2.candidates.model.data;

import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "candidate")
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "candidateid")
    @Setter private Integer candidateId;

    @Setter
    @OneToMany(mappedBy = "candidate", cascade = { CascadeType.MERGE })
    private Set<Section> events;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "departmentId")
    private Department university;

    @Column(name = "account")
    private String account;
    @Column(name = "nationalId")
    private String nationalId;
    @Column(name = "name")
     private String name;
    @Column(name = "dayOfBirth")
//     @Convert(converter = LocalDatePersistenceConverter.class)
    private String dayOfBirth;
    @Column(name = "gender")
    private String gender;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "facebook")
    private String facebook;
    @Column(name = "graduationDate")
    // @Convert(converter = LocalDatePersistenceConverter.class)
    private Integer graduationDate;
    @Column(name = "fullTimeWorking")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate fullTimeWorking;
    @Column(name = "skill")
    private String skill;
    @Column(name = "gpa")
    private Double gpa;
    @Transient private String universityName;
    @Transient private String facultyName;

    public boolean isAttendEvent(String courseCode) {

        return events.stream().filter(s -> s.getEvent().getCourseCode().equals(courseCode)).findFirst().isPresent();
    }
}
