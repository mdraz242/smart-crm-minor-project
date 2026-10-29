package com.enterprise.crm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnectionTest {
    public static void main(String[] args) {
        String username = "MDRAZATECH_SCHEMA_GMAXC";
        String password = "8VTaH9$WQAOBMZ01T093TPJ2EET9JH";

        String[] urls = {
            "jdbc:oracle:thin:@//db.freesql.com:1521/26ai_un3c1",
            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcps)(HOST=db.freesql.com)(PORT=2484))(CONNECT_DATA=(SERVICE_NAME=26ai_un3c1)))",
            "jdbc:oracle:thin:@tcps://db.freesql.com:2484/26ai_un3c1"
        };

        for (String url : urls) {
            System.out.println("Trying connection to: " + url);
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT 'Connection Successful! Oracle Version: ' || BANNER_FULL FROM V$VERSION")) {
                if (rs.next()) {
                    System.out.println(">>> SUCCESS! " + rs.getString(1));
                    System.out.println(">>> Working URL: " + url);
                    return;
                }
            } catch (Exception e) {
                System.out.println("FAILED for " + url + " : " + e.getMessage());
            }
        }
    }
}
