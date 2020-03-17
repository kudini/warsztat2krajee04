package pl.coderslab.model;

import java.time.LocalDateTime;

public class Solutions {
    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String description;
    private int excerciseId;
    private int usersId;

    public Solutions(LocalDateTime created, LocalDateTime updated, String description, int excerciseId, int usersId) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.excerciseId = excerciseId;
        this.usersId = usersId;
    }

    public Solutions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExcerciseId() {
        return excerciseId;
    }

    public void setExcerciseId(int excerciseId) {
        this.excerciseId = excerciseId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
}
