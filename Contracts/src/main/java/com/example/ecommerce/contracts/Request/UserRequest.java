package com.example.ecommerce.contracts.Request;


import com.example.ecommerce.contracts.Enums.City;
import com.example.ecommerce.contracts.Enums.Role;

import java.util.List;

public class UserRequest
{

    private String name;
    private String email;
    private String contact;
    private String country;
    private City city;



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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", country='" + country + '\'' +
                ", city=" + city +
                ", roles=" + roles +
                '}';
    }
}

