package com.menisamet.totake;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by meni on 07/09/16.
 */
public class ItemData implements Serializable, Comparable<ItemData>, Comparator<ItemData> {
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

    @Override
    public int compareTo(ItemData o) {
        return name.compareTo(o.name);
    }


    @Override
    public int compare(ItemData o1, ItemData o2) {
        return o1.name.compareTo(o2.name);
    }
}
