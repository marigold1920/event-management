package group2.candidates.common;

import group2.candidates.enumaration.ResponseStatus;

import java.util.ArrayList;
import java.util.Collection;

public class ResponseObject {

    private String status;
    private Collection<String> errors = new ArrayList<>();
    private Collection<Integer> identifiedData = new ArrayList<>();

    public void addErrors(String message) {
        errors.add(message);
    }

    public ResponseObject setStatus() {
        if (!errors.isEmpty()) {
           status = ResponseStatus.FAIL.getValue();
        } else {
            status = ResponseStatus.SUCCESS.getValue();
        }

        return this;
    }

    public void addIdentifiedData(Collection<Integer> sectionIds) {
        identifiedData.addAll(sectionIds);
    }
}
