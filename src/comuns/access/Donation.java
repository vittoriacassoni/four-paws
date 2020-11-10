package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class Donation extends Entity {

    private float value;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private int userId;


    public Donation(float value, String description, Date createdAt, Date updatedAt, Date deletedAt, int userId) {
        setValue(value);
        setDescription(description);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        setUserId(userId);
    }

    public Donation() {

    }


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
