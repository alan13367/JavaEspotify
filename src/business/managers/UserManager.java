package business.managers;

import business.entities.User;
import persistence.SQL.SQLUserDAO;
import persistence.UserDAO;

import java.math.BigInteger;
import java.security.MessageDigest;


public class UserManager {
    private User user;
    private UserDAO userDAO;

    public UserManager(){
        this.userDAO = new SQLUserDAO();
    }

    private String MD5Encryption (String s) throws Exception {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        return new BigInteger(1,m.digest()).toString(16);
    }

    public boolean logIn(String username,String password)  {
        User user = userDAO.getUser(username);
        if(user == null) {
            return false;
        }

        try {
            if(MD5Encryption(password).equals(user.getPassword())){
                this.user = user;
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void logOut(){
        user = null;
    }

    public void deleteAccount(){
        userDAO.deleteUser(user.getUsername());
        user = null;
    }

    public void createUser(String username,String email,String password){
        User user = null;
        try {
            user = new User(username,email, MD5Encryption(password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        userDAO.addUser(user);
        this.user = user;
    }

    public String getUsername(){
        return user.getUsername();
    }
    //User Functionalities

    public boolean checkPasswordFormat(String password){
        if(password.length() >= 8){

            int numbers = checkRegexPassword(password,".*[0-9].*");
            int capitals = checkRegexPassword(password,".*[A-Z].*");
            int lowercase = checkRegexPassword(password,".*[a-z].*");
            int special = checkRegexPassword(password,".*[!@#\\$%&/=?_.,:;-\\\\].*");

            return numbers + capitals + lowercase + special >= 2;
        }
        return false;
    }

    public boolean checkEmailFormat(String email){
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailPattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private int checkRegexPassword(String password,String regex){
        if(password.matches(regex)){
            return 1;
        }
        return 0;
    }

}
