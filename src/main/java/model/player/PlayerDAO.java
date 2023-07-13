package model.player;

import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PlayerDAO {
    // C - create
    // R - read
    // U - upadate
    // D - delete


    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }


    public void insert(Integer teamId, String name, String position) throws Exception {
        String sql = "insert into player(team_id, name, position, created_at) values(?, ?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teamId);
            statement.setString(2, name);
            statement.setString(3, position);
            statement.executeUpdate();
        }

    }

    public void update(Integer team_id, Integer player_id) throws SQLException {
        String sql = "update player set team_id = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (team_id != null) {
                statement.setInt(1, team_id);
            } else {
                statement.setNull(1, java.sql.Types.INTEGER);
            }
            statement.setInt(2, player_id);
            statement.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from player where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Player findById(int id) throws SQLException {
        Player player = null;
        String sql = "select * from player where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // 오브젝트 매핑 (테이블 데이터 -> 자바 오브젝트)
                    player = new Player(
                            rs.getInt("id"),
                            rs.getInt("team_id"),
                            rs.getString("name"),
                            rs.getString("position"),
                            rs.getTimestamp("created_at")
                    );
                }
            }

        }
        return player;
    }

    public List<Player> findAll() throws SQLException {
        List<Player> playerList = new ArrayList<>();
        String sql = "select * from player";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("id"),
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")
                );
                playerList.add(player);
            }
        }
        return playerList;
    }

    public List<Player> findByTeamId(int teamId) throws SQLException {
        List<Player> playerList = new ArrayList<>();
        String sql = "SELECT * FROM player WHERE team_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teamId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Player player = new Player(
                            rs.getInt("id"),
                            rs.getInt("team_id"),
                            rs.getString("name"),
                            rs.getString("position"),
                            rs.getTimestamp("created_at")
                    );
                    playerList.add(player);
                }
            }
        }
        return playerList;
    }

}