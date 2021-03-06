package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class ForumComment extends Entity {

    private String discussion;
    private Integer forumTopicId;
    private Integer userId;
    private String userName;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public ForumComment(String discussion){
        setDiscussion(discussion);
    }

    public ForumComment() {

    }

    public String getDiscussion() {return discussion;}
    public void setDiscussion(String discussion) {this.discussion = discussion;}

    public int getForumTopicId() {return forumTopicId;}
    public void setForumTopicId(Integer forumTopicId) {this.forumTopicId = forumTopicId;}

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
