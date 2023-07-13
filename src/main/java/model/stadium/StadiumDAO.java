package model.stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {
    // C - create
    // R - read
    // U - upadate
    // D - delete

    private Connection connection;

    public StadiumDAO(Connection connection) {
        this.connection = connection;
    }

    // stadium 추가 (name 입력시 추가)
    public void insert(String name)throws SQLException{
        String sql = "INSERT INTO stadium (name, create_at) VALUES (?, now())";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,name);
            statement.executeUpdate();
        }
    }

    // stadium 삭제 (name 입력시 삭제)
    public void delete(String name)throws SQLException{
        String sql = "delete from stadium where name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,name);
            statement.executeUpdate();
        }
    }

    // stadium 수정(id 입력시 name 수정)
    public void update(int id, String name)throws SQLException{
        String sql = "update stadium set id = ? where name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.executeUpdate();
        }
    }

    // stadium 전체 조회
    public List<Stadium> findAll()throws SQLException{
        List<Stadium> StadiumList = new ArrayList<>();
        String sql = "select * from stadium";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Stadium stadium = new Stadium(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("create_at")
                );
                StadiumList.add(stadium);
            }
        }
        return StadiumList;
    }

    // stadium 상세 조회 (id 입력시 조회)
    public Stadium findById(int id)throws SQLException{
        Stadium stadium = null;
        String sql = "select * from stadium where id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                stadium = new Stadium(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("create_at")
                );
            }
        }
        return stadium;
    }
}
