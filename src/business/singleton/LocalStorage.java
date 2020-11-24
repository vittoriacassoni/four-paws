package business.singleton;

import business.singleton.config.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LocalStorage {

    private static LocalStorage uniqueInstance;
    private static File file = new File("userDatas.txt");

    private String userEmail;
    private String userName;
    private String userLastName;
    private Integer userId;
    private Integer userRoleId;
    private Integer adoptionRequirementId;


    private LocalStorage() throws IOException {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(Pattern.quote("|"));
                setUserId(Integer.valueOf(data[0]));
                setUserEmail(data[1]);
                setUserName(data[2]);
                setUserLastName(data[3]);
                setUserRoleId(Integer.valueOf(data[4]));
                setAdoptionRequirementId(Integer.valueOf(data[5]));
            }
            reader.close();
        }
    }

    public static synchronized LocalStorage getInstance() throws SQLException, IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new LocalStorage();
        }
        return uniqueInstance;
    }

    public void saveUserId(String id) {
        if(userId == null){
            try (FileWriter fw = new FileWriter(file, true)){
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(id + "|");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            setUserId(Integer.valueOf(id));
        }
    }

    public void saveUserEmail(String email) {
        if(userEmail == null){
            try (FileWriter fw = new FileWriter(file, true)){
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(email + "|");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            setUserEmail(email);
        }
    }

    public void saveUserName(String name) {
        if(userName == null){
            try (FileWriter fw = new FileWriter(file, true)){
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(name + "|");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            setUserName(name);
        }
    }

    public void saveUserLastName(String lastName) {
        if(userLastName == null){
            try (FileWriter fw = new FileWriter(file, true)){
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(lastName + "|");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            setUserLastName(lastName);
        }
    }

    public void saveUserRoleId(String roleId)  {
        if(userRoleId == null) {
            try (FileWriter fw = new FileWriter(file, true)){
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(roleId + "|");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            setUserRoleId(Integer.valueOf(roleId));
        }
    }

    public void saveAdoptionRequirementId(String adoptionRequirement)  {
        if(adoptionRequirementId == null) {
            try (FileWriter fw = new FileWriter(file, true)){
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(adoptionRequirement);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            setAdoptionRequirementId(Integer.valueOf(adoptionRequirement));
        }
    }

    public void deleteLocalStorage(){
        try{
            file.delete();
            uniqueInstance = null;
        } catch (Exception error){
            error.printStackTrace();
        }
    }

    public static boolean checkLocalStorage(){
        try{
            return file.exists();
        } catch(Exception error){
            error.printStackTrace();
            return false;
        }
    }
    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public Integer getAdoptionRequirementId() {
        return adoptionRequirementId;
    }

    public void setAdoptionRequirementId(Integer adoptionRequirementId) {
        this.adoptionRequirementId = adoptionRequirementId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserRoleId(Integer userRoleId) {this.userRoleId = userRoleId;}
}
