import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
       /*
        Given
          User connects to the database

        When
          User sends the query to get the region ids from "countries" table

        Then
          Assert that the number of region ids greater than 1 is 17.

        And
          User closes the connection
       */

    @Test
    public void countryTest() throws SQLException {
        //User connects to the database
        JdbcUtils.connectToDataBase("localhost","postgres","postgres","1234");
        Statement statement = JdbcUtils.createStatement();

        // User sends the query to get the region ids from "countries" table
        String sql1 = "SELECT region_id FROM countries";

        ResultSet resultSet1 = statement.executeQuery(sql1);
        List<Integer> ids = new ArrayList<>();

        while (resultSet1.next()){
            ids.add(resultSet1.getInt(1));
        }


        //Assert that the number of region ids greater than 1 is 17.
        System.out.println("ids = " + ids);
        List<Integer> idsGreaterThan1 = new ArrayList<>();
        for(int w:ids){
            if(w>1){
                idsGreaterThan1.add(w);
            }
        }

        System.out.println("idsGreaterThan1 = " + idsGreaterThan1);

        Assert.assertEquals(17,idsGreaterThan1.size());


        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();



    }

}