package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class User extends Entity {

    private String name;
    private String lastName;
    private String email;
    private String passwordHash;
    private String image;
    private Date dateOfBirth;
    private int userRoleld;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public User(String name, String lastName, String email, String password, Date dateOfBirth){
       setName(name);
       setLastName(lastName);
       setEmail(email);
       setPasswordHash(password);
       setDateOfBirth(dateOfBirth);
    }

    public User(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public int getUserRoleld() {
        return userRoleld;
    }

    public void setUserRoleld(int userRoleld) {
        this.userRoleld = userRoleld;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

}
