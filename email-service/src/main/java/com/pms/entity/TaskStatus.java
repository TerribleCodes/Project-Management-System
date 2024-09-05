package com.pms.entity;

/*
 * LHCMD SILVA | 22783 | 30.08.2024 | CREATED TaskStatus Enumeration
 * */

public enum TaskStatus {
    PENDING ("PENDING"),
    ASSIGNED("ASSIGNED"),
    DONE("DONE");

    TaskStatus(String done) {

    }
}
