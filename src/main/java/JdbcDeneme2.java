import java.sql.*;

public class JdbcDeneme2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Datbase'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro", "postgres", "GOKSEL52");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();

        String sql1 = "select country_name from countries where region_id=1";
        boolean r1 = st.execute(sql1);
        System.out.println("r1 = " + r1);

        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()) {

        }

            String sql2="select country_id,country_name from countries where region_id>2;";
            ResultSet resultSet2=st.executeQuery(sql2);

            while (resultSet2.next()){
                System.out.println(resultSet2.getString("country_name")+" "+resultSet2.getString("country_id"));


            }
        System.out.println("************");


        String sql4="select * from companies order by number_of_employees limit 1;";
        ResultSet resultSet5=st.executeQuery(sql4);

        while (resultSet5.next()){
            System.out.println(resultSet5.getInt(1)+" "+resultSet5.getString(2)+" "+resultSet5.getInt(3));
        }




    }
}
