package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class ForumComment extends Entity {

    private String discussion;
    private int forumTopicId;
    private int userId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public String getDiscussion() {return discussion;}
    public void setDiscussion(String discussion) {this.discussion = discussion;}

    public int getForumTopicId() {return forumTopicId;}
    public void setForumTopicId(int forumTopicId) {this.forumTopicId = forumTopicId;}

    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

    public Date getCreatedAt() {return createdAt;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    public Date getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

    public Date getDeletedAt() {return deletedAt;}
    public void setDeletedAt(Date deletedAt) {this.deletedAt = deletedAt;}
}
