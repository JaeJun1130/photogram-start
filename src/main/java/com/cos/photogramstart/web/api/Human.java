package com.cos.photogramstart.web.api;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class Human {
    private int id;
    private String name;

    public Human(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Human e = (Human) o;
        return (this.getId() == e.getId());
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + getId();
        return result;
    }


    public static void main(String[] args) {
        Human data1 = new Human(1, "jaejun");
        Human data2 = new Human(1, "jaejun");

        System.out.println(data1.hashCode());
        System.out.println(data2.hashCode());

        Map<Human, String> hashMap = new HashMap<>();
        hashMap.put(data1, "my");

        String str = null;
        str = hashMap.get(new Human(1, "jaejun"));
        System.out.println("str = " + str);
    }
}
