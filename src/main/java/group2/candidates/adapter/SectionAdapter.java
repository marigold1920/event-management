package group2.candidates.adapter;

import group2.candidates.builder.SectionBuilder;
import group2.candidates.common.ResponseObject;
import group2.candidates.model.data.Event;
import group2.candidates.model.data.Section;
import group2.candidates.service.DepartmentService;
import group2.candidates.tool.PoolService;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SectionAdapter {
    private PoolService pool = PoolService.getPoolService();

    private Integer sectionId;
    private String nationalId;
    private String account;
    private String name;
    private String universityName;
    private String facultyCode;
    private String dob;
    private String gender;
    @Getter private String email;
    private String phone;
    private String facebook;
    private Integer universityGraduationDate;
    private LocalDate fullTimeWorking;
    private double gpa;

   @Getter private String courseCode;
   private String candidateId;

    private String status;
    private String finalGrade;
    private String completionLevel;
    private String certificateId;
    private String note;
    private String contractType;

    public Section buildSection(Event event, DepartmentService departmentService, ResponseObject responseObject) {
        var department = pool.getDepartment(universityName, facultyCode, departmentService);

        if (department == null) {
            responseObject.addErrors("System was not found " + universityName + " with " + facultyCode + "!");
            return null;
        }

        var builder =  new SectionBuilder()
                .section()
                    .join(event, sectionId, status, finalGrade, completionLevel, certificateId, note, contractType)
                .candidate()
                    .attend(responseObject, pool, account, nationalId, name, dob, gender,
                                        email, phone, facebook, universityGraduationDate, fullTimeWorking, gpa)
                    .department(department);

        return builder.isValid() ? builder.build() : null;
    }
}