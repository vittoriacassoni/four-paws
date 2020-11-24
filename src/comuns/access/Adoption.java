package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class Adoption extends Entity {

    private int animalId;
    private int userId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Adoption(Integer animalId, Integer userId) {
        setAnimalId(animalId);
        setUserId(userId);
    }

    public Adoption() {

    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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


}
