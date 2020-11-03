package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class ForumTopic extends Entity {

    private String title;
    private Integer userId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public Integer getUserId() {return userId;}
    public void setUserId(Integer userId) {this.userId = userId;}

    public Date getCreatedAt() {return createdAt;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    public Date getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

    public Date getDeletedAt() {return deletedAt;}
    public void setDeletedAt(Date deletedAt) {this.deletedAt = deletedAt;}
}
