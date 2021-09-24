package com.vec.practicalexercise.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "A user.", type = "object", example = "|\n" +
        "        {\n" +
        "          \"email\": \"some.one@email.com\",\n" +
        "          \"password\": \"s0m3s3cr3tp455w0rd\",\n" +
        "          \"firstName\": \"Some\",\n" +
        "          \"lastName\": \"One\"\n" +
        "        }", requiredProperties = {"email", "password"})
@Data
public class User {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
