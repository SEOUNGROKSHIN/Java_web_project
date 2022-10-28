package org.zerock.jdbcex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoDAO {
    
    public String getTime() {
        
        String now = null;
        
        try(Connection connection = ConnectionUtil.INSTANCE.getConnection();
            
            PreparedStatement pstmt = connection.prepareStatement("select now()");
            
            ResultSet resultSet = pstmt.executeQuery();
            ) {
            
            resultSet.next();
            
            now = resultSet.getString(1);
            
        }  catch (Exception e) {
            e.printStackTrace();
        }
    return now;
    }
    
}

