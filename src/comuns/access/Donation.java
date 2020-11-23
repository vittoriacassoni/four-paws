package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class Donation extends Entity {

    private Double value;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Integer userId;


    public Donation(Double value, String description, Integer userId) {
        setValue(value);
        setDescription(description);
        setUserId(userId);
    }

    public Donation() {

    }


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
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

    @Override
    public String toString() {
        return getId() + "|" + getValue() + "|" + getDescription() + "|" + getCreatedAt() + "|" + getUpdatedAt() + "|" +
                getDeletedAt() + "|" + getUserId();
    }
}
