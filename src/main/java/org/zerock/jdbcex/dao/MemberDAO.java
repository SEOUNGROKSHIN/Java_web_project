package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.jdbcex.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {


    public MemberVO getWithPassword(String mid, String mpw) throws Exception {


        String query = "select mid, mpw , mname from tbl_member where mid = ? and mpw = ?";

        MemberVO memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, mid);
        pstmt.setString(2, mid);

        @Cleanup ResultSet rset = pstmt.executeQuery();

        rset.next();

        memberVO = MemberVO.builder()
                .mid(rset.getString(1))
                .mpw(rset.getString(2))
                .mname(rset.getString(3))
                .build();

        return memberVO;
    }
}
