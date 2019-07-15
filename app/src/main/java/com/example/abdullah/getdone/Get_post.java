package com.example.abdullah.getdone;

public class Get_post {
    private int id;
    private String name;
    private String description;
    private String last_date;
    private String type;
    private String type_of_task;
    private String date;
    private String status;
    private String budget;
    private String location;
    private String poster;
    private int no_of_persons;


    public Get_post(int id, String name, String description, String last_date,String type,String status, String date,String type_of_task,String budget,String location,int no_of_persons,String poster) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.last_date = last_date;
        this.type = type;
        this.type_of_task = type_of_task;
        this.date = date;
        this.status = status;
        this.budget = budget;
        this.location = location;
        this.no_of_persons = no_of_persons;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLastDate() {
        return last_date;
    }

    public String getType() {
        return type;
    }

    public String getType_of_task() {
        return type_of_task;
    }
    public String getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }
    public String getBudget() {
        return budget;
    }
    public String getPoster() {
        return poster;
    }
    public String getLocation() {
        return location;
    }
    public int getNo_of_persons() {
        return no_of_persons;
    }



}