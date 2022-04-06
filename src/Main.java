import persistence.SQL.SQLConnector;
import persistence.SQLConfigDAO;

public class Main {
    public static void main(String[] args) {
        String [] data = SQLConfigDAO.getInstance().getData();
        System.out.println("shdv");
    }
}
