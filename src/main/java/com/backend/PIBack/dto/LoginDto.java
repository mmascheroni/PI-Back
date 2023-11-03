package com.backend.PIBack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LoginDto {

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    private String jwt;

    public LoginDto() {
    }

    public LoginDto(String email, String jwt) {
        this.email = email;
        this.jwt = jwt;
    }


    public LoginDto(String email, String password, String jwt) {
        this.email = email;
        this.password = password;
        this.jwt = jwt;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public static LoginDto fromLogin(LoginDto loginDto) {
        return new LoginDto(loginDto.getEmail(), loginDto.getJwt());
    }
}
