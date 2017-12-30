package com.company.homework;

import com.company.homework.Annotations.*;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id = 0;

    @DisplayName(printValue = "Username")
    @NotNull
    @NotBlank
    @Length(minValue = 3, maxValue = 15)
    private String username = "";
    @DisplayName(printValue = "Password")
    @NotBlank
    @Length(minValue = 6, maxValue = 15)
    private String password;
    @DisplayName(printValue = "First name")
    @NotBlank
    @Length(minValue = 1, maxValue = 15)
    private String firstName;
    @DisplayName(printValue = "Last name")
    @NotBlank
    @Length(minValue = 1, maxValue = 15)
    private String lastName;
    @DisplayName(printValue = "Age")
    @NotBlank
    @NumberLength(minValue = 18, maxValue = 99)
    private String age;
    @DisplayName(printValue = "E-mail")
    @NotBlank
    @Email
    private String email;


    public User() {
    }

    public User(int id, String username, String password, String firstName, String lastName, String age, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
