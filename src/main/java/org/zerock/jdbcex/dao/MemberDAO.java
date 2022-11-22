package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.mariadb.jdbc.export.Prepare;
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
        pstmt.setString(2, mpw);

        @Cleanup ResultSet rset = pstmt.executeQuery();

        rset.next();

        memberVO = MemberVO.builder()
                .mid(rset.getString(1))
                .mpw(rset.getString(2))
                .mname(rset.getString(3))
                .build();

        return memberVO;
    }

    public void updateUuid(String mid , String uuid) throws Exception {

        String sql = "update tbl_member set uuid = ? where mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1 , uuid);
        pstmt.setString(2 , mid);
        pstmt.executeUpdate();
    }

    public MemberVO selectUUID(String uuid) throws Exception {

        String sql = "select mid , mpw , mname , uuid from tbl_member where uuid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1 , uuid);

        @Cleanup ResultSet rset = pstmt.getResultSet();

        rset.next();

        MemberVO memberVO = MemberVO.builder()
                .mid(rset.getString(1))
                .mpw(rset.getString(2))
                .mname(rset.getString(3))
                .uuid(rset.getString(4))
                .build();

        return memberVO;
    }
}
