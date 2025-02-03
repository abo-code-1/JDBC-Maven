import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtils {
    private static String dbURL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'";
    private static String dbUsername = "sa";
    private static String dbPassword = "";

    public static Connection getConnection() {


        // Потоковое чтение данных из файла config
//        String dbURL = null;
//        String dbUsername = "sa";
//        String dbPassword = "";
//
//        FileInputStream fis = null;
//        Properties prop = new Properties();
//        try{
//            fis = new FileInputStream("config.properties");
//            prop.load(fis);
//            dbURL = prop.getProperty("db.host ");
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

////////////////////////////////////////////////////////////

        // Подключение к базе данных
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
