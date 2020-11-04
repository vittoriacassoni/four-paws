package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class AdoptionRequirement extends Entity {

    private Double maxExpense;
    private Double requiredSpace;
    private Boolean isAngry;
    private Boolean isHappy;
    private Boolean isNeedy;
    private Boolean isCaring;
    private Boolean isQuiet;
    private Integer userId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;


    public Double getMaxExpense() {
        return maxExpense;
    }

    public void setMaxExpense(Double maxExpense) { this.maxExpense = maxExpense; }

    public Double getRequiredSpace() {
        return requiredSpace;
    }

    public void setRequiredSpace(Double requiredSpace) {
        this.requiredSpace = requiredSpace;
    }

    public Boolean getIsAngry() {
        return isAngry;
    }

    public void setIsAngry(Boolean isAngry) {
        this.isAngry = isAngry;
    }

    public Boolean getIsHappy() {
        return isHappy;
    }

    public void setIsHappy(Boolean isHappy) {
        this.isHappy = isHappy;
    }

    public Boolean getIsNeedy() {
        return isNeedy;
    }

    public void setIsNeedy(Boolean isNeedy) {
        this.isNeedy = isNeedy;
    }

    public Boolean getIsCaring() { return isCaring; }

    public void setIsCaring(Boolean isCaring) {
        this.isCaring = isCaring;
    }

    public Boolean getIsQuiet() {
        return isQuiet;
    }

    public void setIsQuiet(Boolean isQuiet) {
        this.isQuiet = isQuiet;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() { return updatedAt; }

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
