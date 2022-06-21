import javax.xml.transform.Result;
import java.sql.*;

public class Conect {
    private String url;
    private String user;
    private String password;
    private Connection con;

    Conect() {
        url = "jdbc:postgresql://localhost:5432/registro_vacinal";
        user = "postgres";
        password = "123";

        try {
            Class.forName("org.postgresql.Driver" );
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão Realizada com Sucesso!!!" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int executeSql(String sql) {
        try {
            Statement statement = con.createStatement();
            int res = statement.executeUpdate(sql);
            con.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(sql);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}




