package group2.candidates.model.data;

import group2.candidates.tool.LocalDatePersistenceConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Candidate")
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CandidateId")
    private Integer candidateId;
    
    @OneToMany(mappedBy = "candidate", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    @Expose private Set<Section> events;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    @JoinColumn(name = "DepartmentId")
    @Setter
    private Department university;
    
    @Column(name = "Account")
    private String account;
    @Column(name = "NationalId")
    private String nationalId;
     @Column(name = "Name")
     private String name;
    // @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "DOB")
    private String dayOfBirth;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Facebook")
    private String facebook;
    // @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "GraduationDate")
    private Integer graduationDate;
    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "FullTimeWorking")
    private LocalDate fullTimeWorking;
    @Column(name = "Skill")
    private String skill;
    @Column(name = "GPA")
    private Double gpa;
}
