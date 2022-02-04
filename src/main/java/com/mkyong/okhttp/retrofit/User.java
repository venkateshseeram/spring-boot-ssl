package com.mkyong.okhttp.retrofit;

public class User{
    public int id;
    public String name;
    public String email;
    public String gender;
    public String status;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}

