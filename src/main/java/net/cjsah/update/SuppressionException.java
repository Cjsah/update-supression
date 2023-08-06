package net.cjsah.update;

public class SuppressionException extends Exception {
    private final SuppressionData data;

    public SuppressionException(SuppressionData data) {
        this.data = data;
    }

    public String getDataMsg() {
        return this.data.toGameString();
    }
}
