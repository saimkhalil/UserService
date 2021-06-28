package com.example.authserver.contracts.Response;

import com.example.authserver.contracts.Enums.City;
import com.example.authserver.contracts.Enums.Role;

import java.util.List;

public class UserResponse
{
    private String name;
    private String email;
    private String contact;
    private String country;
    private City city;
    private Long creationTime;
    private boolean isActive;

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public City getCity() {
        return city;
    }

    public UserResponse() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
