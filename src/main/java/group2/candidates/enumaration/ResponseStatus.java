package group2.candidates.enumaration;

public enum ResponseStatus {
        SUCCESS("200"),
        FAIL("400");

        private final String value;

        ResponseStatus(String value) {
            this.value = value;
        }

       public  String getValue() { return value; }
}
