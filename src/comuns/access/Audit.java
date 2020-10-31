package comuns.access;

import comuns.bases.Entity;

public class Audit extends Entity {
    private String userId;
    private String action;
    private String createdAt;

    public Audit() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String UserId) {
        this.userId = UserId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String Action) {
        this.action = Action;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.action = createdAt;
    }
}
