package com.design.pattern.es;

import lombok.Data;

@Data
public class Tests {

    private Long id;

    private String name;

    @Override
    public String toString() {
        return "Tests [id=" + id + ", name=" + name + "]";
    }

}