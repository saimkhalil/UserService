package com.example.ecommerce.app.Persistence.Model;

import com.example.ecommerce.contracts.Enums.City;
import com.example.ecommerce.contracts.Enums.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(value = "User")
public class User
{
    @Id
    private String id;
    private String name;
    private String email;
    private String contact;
    private String country;
    private City city;
    private boolean isActive;
    private Long creationTime;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", roles=" + roles +
                '}';
    }

    public static class Constants
    {
        public static final String ID = "id";
        public static final String CITY = "city";
        private static final String COUNTRY = "country";
        private static final String NAME  = "name";
        private static final String CONTACT  = "contact";

    }

}
