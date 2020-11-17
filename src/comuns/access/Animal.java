package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class Animal extends Entity {

    private String name;
    private String breed;
    private String color;
    private Double size;
    private Double weight;
    private String image;
    private Date dateOfBirth;
    private String history;
    private Integer adoptionRequirementId;
    private Integer age;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Animal(String name, String breed, String color, Double size, Double weight, Integer age) {
        setName(name);
        setBreed(breed);
        setColor(color);
        setSize(size);
        setWeight(weight);
        setAge(age);
    }

    public Animal (){

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

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
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
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAdoptionRequirementId() {
        return adoptionRequirementId;
    }

    public void setAdoptionRequirementId(Integer adoptionRequirementId) {
        this.adoptionRequirementId = adoptionRequirementId;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}