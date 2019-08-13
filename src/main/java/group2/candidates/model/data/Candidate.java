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
@Table(name = "candidate")
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private Integer candidateId;
    
    @OneToMany(mappedBy = "candidate", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    @Expose private Set<Section> events;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    @JoinColumn(name = "departmentId")
    @Setter
    private Department university;
    
    @Column
    private String account;
    @Column
    private String nationalId;
     @Column
     private String name;
    // @Convert(converter = LocalDatePersistenceConverter.class)
    @Column
    private String dayOfBirth;
    @Column
    private String gender;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String facebook;
    // @Convert(converter = LocalDatePersistenceConverter.class)
    @Column
    private Integer graduationDate;
    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column
    private LocalDate fullTimeWorking;
    @Column
    private String skill;
    @Column
    private Double gpa;
}
