package com.company.exceptions;

public class BuildingUnderArrestException extends Throwable {
    public String printMessage() {
        return "Building was arrested!";
    }
}
