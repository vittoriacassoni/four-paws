package comuns.access;

import comuns.bases.Entity;

import java.util.Date;

public class ReportAbandonment extends Entity {
    private String address;
    private String hostName;
    private String hostContact;
    private Date lastSeen;
    private Integer userId;
    private Boolean temporaryHome;

    public ReportAbandonment(String address, Date lastSeen, Integer userId,  Boolean temporaryHome, String hostName,
                             String hostContact){
        setAddress(address);
        setLastSeen(lastSeen);
        setUserId(userId);
        setTemporaryHome(temporaryHome);

        if(temporaryHome){
            setHostName(hostName);
            setHostContact(hostContact);
        }
    }

    public ReportAbandonment(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostContact() {
        return hostContact;
    }

    public void setHostContact(String hostContact) {
        this.hostContact = hostContact;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getTemporaryHome() {
        return temporaryHome;
    }

    public void setTemporaryHome(Boolean temporaryHome) {
        this.temporaryHome = temporaryHome;
    }
}
