package com.menisamet.totake;

import java.io.Serializable;

/**
 * Created by meni on 07/09/16.
 */
public class ItemData implements Serializable {
    private String name;
    private int numbers;

    public ItemData() {}
    public ItemData(String name, int numbers) {
        this.name = name;
        this.numbers = numbers;
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

    @Override
    public String toString() {
        return "ItemData{" +
                "name='" + name + '\'' +
                ", numbers=" + numbers +
                '}';
    }
}
