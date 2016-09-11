package com.menisamet.totake;

/**
 * Created by meni on 07/09/16.
 */
public class ItemData {
    private String name;
    private int numbers;
    private boolean check;

    public ItemData(String name, int numbers, boolean check) {
        this.name = name;
        this.numbers = numbers;
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
