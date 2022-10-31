package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public String getTime2() throws Exception {

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement("select now()");
        @Cleanup ResultSet rset = pstmt.executeQuery();

        rset.next();

        String now = rset.getString(1);


        System.out.println(rset.getString(1));

        return now;
    }

    public void insert(TodoVO vo) throws Exception {
        String sql = "insert into tbl_todo (title , dueDate , finished ) values (? , ? , ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1 , vo.getTitle());
        pstmt.setDate(2 , Date.valueOf(vo.getDueDate()));
        pstmt.setBoolean(3,  vo.isFinished());

        pstmt.executeUpdate();
    }

    public List<TodoVO> selectAll() throws Exception {

        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        @Cleanup ResultSet rset = pstmt.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while(rset.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rset.getLong("tno"))
                    .title(rset.getString("title"))
                    .dueDate(rset.getDate("dueDate").toLocalDate())
                    .finished(rset.getBoolean("finished"))
                    .build();

            list.add(vo);
        }

        return list;
    }

    public TodoVO selectOne(Long tno) throws Exception {

        String sql = "select * from tbl_todo where tno = ? ";

    @Cleanup    Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup    PreparedStatement pstmt = connection.prepareStatement(sql);

                pstmt.setLong(1 , tno);
    @Cleanup    ResultSet rset = pstmt.executeQuery();

    rset.next();
    TodoVO vo = TodoVO.builder()
            .tno(rset.getLong("tno"))
            .title(rset.getString("title"))
            .dueDate(rset.getDate("dueDate").toLocalDate())
            .finished(rset.getBoolean("finished"))
            .build();

    return vo;
    }

    public void deleteOne(Long tno) throws Exception {

        String sql = "delete from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setLong(1, tno);

        pstmt.executeQuery();

    }

    public void updateOne(TodoVO todoVO) throws Exception {

        String sql = "update tbl_todo set title = ? , dueDate = ? , finished = ? where tno = ? ";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1 , todoVO.getTitle());
        pstmt.setDate(2 , Date.valueOf(todoVO.getDueDate()));
        pstmt.setBoolean(3 , todoVO.isFinished());
        pstmt.setLong(4 , todoVO.getTno());

        pstmt.executeUpdate();
    }
}

