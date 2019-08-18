package group2.candidates.common;

import group2.candidates.enumaration.CandidateEnrollmentStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionResponseEntity {

    private CandidateEnrollmentStatus status;
    private List<String> errors = new ArrayList<>();
    private List<Integer> identifiedSections = new ArrayList<>();

    public void addErrors(String message) {
        errors.add(message);
    }

    public void addIdentifiedData(Integer sectionId) {
        identifiedSections.add(sectionId);
    }

    public SectionResponseEntity setStatus() {
        if (!errors.isEmpty()) {
           status = CandidateEnrollmentStatus.FAIL;
        } else {
            status = CandidateEnrollmentStatus.SUCCESS;
        }

        return this;
    }
}
