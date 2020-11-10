package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class AdoptionRequirement extends Entity {

    private Double maxExpense;
    private Double requiredSpace;
    private Integer agePreference;
    private Boolean isAngry;
    private Boolean isHappy;
    private Boolean isNeedy;
    private Boolean isCaring;
    private Boolean isQuiet;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public AdoptionRequirement (Boolean isAngry, Boolean isHappy, Boolean isNeedy, Boolean isCaring, Boolean isQuiet,
                                Double maxExpense, Double requiredSpace, Integer agePreference){
        setIsAngry(isAngry);
        setIsHappy(isHappy);
        setIsNeedy(isNeedy);
        setIsCaring(isCaring);
        setIsQuiet(isQuiet);
        setMaxExpense(maxExpense);
        setRequiredSpace(requiredSpace);
        setAgePreference(agePreference);
    }

    public AdoptionRequirement(){}

    public Double getMaxExpense() {
        return maxExpense;
    }

    public void setMaxExpense(Double maxExpense) { this.maxExpense = maxExpense; }

    public Integer getAgePreference() {
        return agePreference;
    }

    public void setAgePreference(Integer agePreference) { this.agePreference = agePreference; }

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
