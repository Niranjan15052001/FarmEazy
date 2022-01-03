package com.example.farmeazy2;

public class User {
    String displayname;
    String Email;
    long createdAt;
    public User (){};
    public User(String displayname,String email,long createdAt){
        this.displayname=displayname;
        this.Email=email;
        this.createdAt=createdAt;
    }
    public String getDisplayname() {
        return displayname;
    }

    public String getEmail() {
        return Email;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
