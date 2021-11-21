/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnmq.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Minh Hoang
 */
public class DBHelper {
     public static Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("DBcon");
        return ds.getConnection();
    }

    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }
}
