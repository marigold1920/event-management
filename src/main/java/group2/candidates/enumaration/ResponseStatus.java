package group2.candidates.enumaration;

public enum ResponseStatus {
        SUCCESS("All candidates attended successfully!"),
        FAIL("Data might be not valid!");

        private final String value;

        ResponseStatus(String value) {
            this.value = value;
        }

       public  String getValue() { return value; }
}
