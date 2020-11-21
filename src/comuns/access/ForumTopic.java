package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class ForumTopic extends Entity {

    private String title;
    private String discussion;
    private Integer userId;
    private String userName;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public ForumTopic(String title, String discussion){
        setTitle(title);
        setDiscussion(discussion);
    }

    public ForumTopic() {

    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDiscussion() {return discussion;}
    public void setDiscussion(String discussion) {this.discussion = discussion;}

    public Integer getUserId() {return userId;}
    public void setUserId(Integer userId) {this.userId = userId;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public Date getCreatedAt() {return createdAt;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    public Date getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

    public Date getDeletedAt() {return deletedAt;}
    public void setDeletedAt(Date deletedAt) {this.deletedAt = deletedAt;}
}
