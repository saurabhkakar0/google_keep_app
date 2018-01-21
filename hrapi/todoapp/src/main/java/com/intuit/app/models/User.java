package com.intuit.app.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private String firstName;
    private String lastName;
    private String userId;
    private String email;
    private Sex sex;

    public enum Sex{
        MALE("M"),
        FEMALE("F");

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        private String value;
        Sex(String value){
            this.value = value;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
