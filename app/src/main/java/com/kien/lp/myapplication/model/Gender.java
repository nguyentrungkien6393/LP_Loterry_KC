package com.kien.lp.myapplication.model;

public class Gender {
    String gender;
    String GenderCode;

    public String getGenderCode() {
        return GenderCode;
    }

    public void setGenderCode(String genderCode) {
        GenderCode = genderCode;
    }

    public Gender(String gender, String genderCode) {

        this.gender = gender;
        GenderCode = genderCode;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
