package com.example.taskmanager;

/**
 * Created by Nish on 3/7/15.
 */
public class TaskItem {
    String title;
    String description;
    int color;

    public TaskItem(String title, String description, int color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
