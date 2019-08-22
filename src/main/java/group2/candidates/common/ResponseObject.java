package group2.candidates.common;

import group2.candidates.enumaration.ResponseStatus;

import java.util.HashSet;
import java.util.Set;

public class ResponseObject {

    private String status;
    private Set<String> errors = new HashSet<>();
    private Object identifiedObject;

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

    public void  addIdentifiedObject(Object identifiedObject) {
        this.identifiedObject = identifiedObject;
    }
}
