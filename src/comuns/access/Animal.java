package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class Animal extends Entity {

    private String name;
    private String breed;
    private String color;
    private float size;
    private float weight;
    private String image;
    private Date dateOfBirth;
    private String history;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private int adoptionRequirementId;

    public Animal(String name, String breed, String color, float size, float weight, String image, Date dateOfBirth,
                  String history, Date createdAt, Date updatedAt, Date deletedAt, int adoptionRequirementId) {
        setName(name);
        setBreed(breed);
        setColor(color);
        setSize(size);
        setWeight(weight);
        setImage(image);
        setDateOfBirth(dateOfBirth);
        setHistory(history);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        setAdoptionRequirementId(adoptionRequirementId);
    }

    public Animal() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
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

    public int getAdoptionRequirementId() {
        return adoptionRequirementId;
    }

    public void setAdoptionRequirementId(int adoptionRequirementId) {
        this.adoptionRequirementId = adoptionRequirementId;
    }
}