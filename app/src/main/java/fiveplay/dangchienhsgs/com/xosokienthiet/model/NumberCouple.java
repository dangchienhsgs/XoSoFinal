package fiveplay.dangchienhsgs.com.xosokienthiet.model;

/**
 * Created by dangchienbn on 19/11/2014.
 */
public class NumberCouple {
    private int index;
    private int value;

    public NumberCouple(int index, int value) {
        this.index = index;
        this.value = value;
    }


    public int getIndex() {

        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
