package group2.candidates.builder;

import group2.candidates.model.data.Candidate;
import group2.candidates.model.data.Department;
import group2.candidates.model.data.Section;
import group2.candidates.model.data.SectionPK;

import java.time.LocalDate;
import java.util.Objects;

public class SectionCandidateBuilder extends SectionBuilder {

    SectionCandidateBuilder(Section section) {
        this.section = section;
    }

    /**
     * This function will build Section, and then add it to Collection<Section> candidates in this Event
     * This function also builds Candidate that needs to create Section
     * @param account account of candidates
     * @param nationalId national Id
     * @param name name of candidates
     * @param dob day of birth
     * @param gender gender of candidates
     * @param email email of candidates
     * @param phone phone number of candidates
     * @param facebook facebook of candidates
     * @param universityGraduationDate graduation time of candidates
     * @param fullTimeWorking time for ready to working in event
     * @param gpa gpa
     * @return SectionCandidateBuilder
     */
    public SectionCandidateBuilder attend(String account, String nationalId, String name, String dob, String gender,
                                          String email, String phone, String facebook, Integer universityGraduationDate, LocalDate fullTimeWorking, double gpa) {
        var candidate = Candidate.builder()
                .candidateId(Objects.hash(name, email))
                .account(account)
                .nationalId(nationalId)
                .name(name)
                .dayOfBirth(dob)
                .email(email)
                .gender(gender)
                .phone(phone)
                .facebook(facebook)
                .gpa(gpa)
                .graduationDate(universityGraduationDate)
                .fullTimeWorking(fullTimeWorking)
                .build();
        section.setCandidate(candidate);
        section.setSectionId(new SectionPK(section.getEvent().getEventId(), candidate.getCandidateId()));
        return this;
    }

    public SectionCandidateBuilder department(Department department) {
        this.section.getCandidate().setUniversity(department);

        return this;
    }
}
