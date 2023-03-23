import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Datbase'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro","postgres","GOKSEL52");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        String sql1="select company,number_of_employees from companies order by number_of_employees desc offset 1 limit 1;";
        ResultSet resultset1=st.executeQuery(sql1);

        while (resultset1.next()) {
            System.out.println(resultset1.getString("company") + "--" + resultset1.getInt("number_of_employees"));
        }

            //2.Subquery Kullanarak
            String sql2="SELECT company, number_of_employees\n" +
                    "FROM companies\n" +
                    "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                    "                            FROM companies\n" +
                    "                            WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                    "                                                         FROM companies));";

            ResultSet resultSet2=st.executeQuery(sql2);
            while (resultSet2.next()){
                System.out.println(resultSet2.getString("company")+ "--"+ resultSet2.getInt("number_of_employees"));

            }

            con.close();
            st.close();
            resultset1.close();
            resultSet2.close();

        }
    }

