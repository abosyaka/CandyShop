package com.epam.candy.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
