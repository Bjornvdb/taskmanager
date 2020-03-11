package be.bjornvdb.taskmanager.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private long id;
    private String title, description;
    private List<Task> subTasks;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    public Task() {

    }

    public Task(long id, String title, String description, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.subTasks = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d u 'at' h a");
        return date.format(formatter);
    }

    public String getFormattedDateHTML() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddThh:mm");
        return date.format(formatter);
    }
}
