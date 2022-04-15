package business.managers;

import business.entities.User;
import persistence.SQL.SQLUserDAO;
import persistence.UserDAO;

public class UserManager {
    private User user;
    private UserDAO userDAO;

    public UserManager(){
        this.userDAO = new SQLUserDAO();
    }


    public void logIn(String username,String password){
        User user = userDAO.getUser(username);
        if(password.equals(user.getPassword())){
            this.user = user;
        }
    }

    public void deleteAccount(){
        userDAO.deleteUser(user.getUsername());
    }

    public void createUser(User user){
        userDAO.addUser(user);
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
