package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.Street;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DirectoryDao {

    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jc_student",
                "postgres", "postgres");
        return con;
    }

    public List<Street> findStreets(String pattern) throws Exception{
        List<Street> result = new LinkedList<>();
        Connection con = getConnection();
        String sql = "select street_code, street_name from jc_street where upper(street_name) like upper('%" + pattern + "%')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM jc_street");
        while (rs.next()){
            Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
            result.add(str);
        }
        return result;
    }
}
