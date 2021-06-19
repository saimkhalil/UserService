package com.example.ecommerce.contracts.Enums;

import org.springframework.stereotype.Component;

public enum Test
{
    {student, teacher, admin;}
    private String role;

    public Test(roleType type) {
        this.role = type.toString();
    }

    public String getRole() {
        return role;
    }

    public void setRole(roleType type) {
        this.role = type.toString();
    }
}
