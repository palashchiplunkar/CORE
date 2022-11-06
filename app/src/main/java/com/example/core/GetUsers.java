package com.example.core;

public class GetUsers {
    String name,email,usn,year,section;

    public GetUsers(String name, String email, String usn,String year,String section) {
        this.name = name;
        this.email = email;
        this.usn = usn;
        this.year=year;
        this.section=section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
