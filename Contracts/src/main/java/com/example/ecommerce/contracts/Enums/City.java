package com.example.ecommerce.contracts.Enums;

public enum City
{
    BHOPAL("BHOPAL"),
    BHAGALPUR("BHAGALPUR"),
    INDORE("INDORE"),
    PATNA("PATNA"),
    MUMBAI("MUMBAI");

    private String val;

    City(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
