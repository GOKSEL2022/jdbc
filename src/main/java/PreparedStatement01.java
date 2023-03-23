import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Datbase'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Techpro","postgres","GOKSEL52");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();

        /*
        PreparedStatement interface,birden çok kez çalıştırılabilen önceden derlenmiş
        bir SQL kodunu temsil eder,onun yerine kullanılabilir.
        Parametrelendirilmiş SQL sorguları(query) ile çalışır.Bu sorguyu(queryi) 0 veya daha fazla
        parametre ile kullanabiliriz.
         */

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees
        // değerini 9999 olarak güncelleyin.

        //1.Adım :PreparedStatement query'sini(sorgu) oluştur.
        // normalde böyleyken aşağıdaki gibi olur.-->update companies set number_of_employees=9999 where company='IBM';
        //String sq11="update companies set number_of_employees=? where company=?";  // dinamik yapmak ,değişiklik yapmak istediğimiz kısımlara soru işareti(?) koyduk.
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ? ";

        //2. Adım: PreparedStatement objesini oluştur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.Adım: setInt(),setString(),...methodlarını kullanarak soru işaretleri yerine deger gir.
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        //4.adım: Query'i çalıştır.
        int guncellenenSatirSayisi=pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2="SELECT*FROM companies";
        ResultSet rs1=st.executeQuery(sql2);
        while (rs1.next()){
            System.out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+ rs1.getInt(3));
        }
        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        ResultSet rs2 =  st.executeQuery(sql2);

        while(rs2.next()){
            System.out.println(rs2.getInt(1)+"--"+rs2.getString(2)+"--"+rs2.getInt(3));
        }

        con.close();
        st.close();
        rs1.close();
        rs2.close();
    }
}