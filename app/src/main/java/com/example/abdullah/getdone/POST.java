package com.example.abdullah.getdone;

public class POST {

    private String UserName;
    private String Description;
    private String Title;
    private String Budget;
    private String Location;


    public POST(String userName, String description, String title, String budget, String location) {
        UserName = userName;
        Description = description;
        Title = title;
        Budget = budget;
        Location = location;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String toString(){
        return this.UserName+" . "+Description+" . "+Title+" . "+Budget;
    }

}
