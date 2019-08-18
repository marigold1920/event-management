package group2.candidates.enumaration;

public enum CandidateEnrollmentStatus {
        SUCCESS("All candidates attended successfully!"),
        FAIL("Data might be not valid!");

        private final String value;

        CandidateEnrollmentStatus(String value) {
            this.value = value;
        }

        String getValue() { return value; }
}
