package fiveplay.dangchienhsgs.com.xosokienthiet.model;

public class StringCouple {
    private String key;
    private int value;

    public StringCouple(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
