package com.pensil.com.projectcilegon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class TestSchedule {

    @Id
    @GeneratedValue
    private long id;

    private long userId;

    private LocalDateTime testDateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getTestDateTime() {
        return testDateTime;
    }

    public void setTestDateTime(LocalDateTime testDateTime) {
        this.testDateTime = testDateTime;
    }
}
