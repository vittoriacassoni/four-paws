package business.log;

public class Audit {
    private String userId;
    private String action;

    public  Audit (String userId, String action){
        this.userId = userId;
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }
}
