import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDeneme {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Datbase'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro","postgres","GOKSEL52");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();

        boolean sgl1=st.execute("CREATE TABLE WORKERS(worker_id VARCHAR(20),worker_name VARCHAR (20),worker_salary INT);");
        System.out.println("sgl1 = " + sgl1);

        String sql2="alter table workers add worker_address VARCHAR(80);";
        boolean sql2b=st.execute(sql2);
        System.out.println("sql2b = " + sql2b);

        String sql3="drop table workers";
        st.execute(sql3);

        con.close();
        st.close();




    }
}
