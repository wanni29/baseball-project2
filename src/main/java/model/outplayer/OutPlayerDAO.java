package model.outplayer;

import dto.OutPlayerRespDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {
    // C - create
    // R - read
    // U - upadate
    // D - delete

    private Connection connection;

    public OutPlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Integer playerId, String reason) throws SQLException {
        String sql = "insert into out_player( player_id, reason, created_at) values(?, ?, now())";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, playerId);
            statement.setString(2, reason);
            statement.executeUpdate();
        }

    }

    public void delete(Integer player_id) throws SQLException {
        String sql = "delete from out_player where player_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, player_id);
            statement.executeUpdate();
        }
    }

    public void update(String reason, Integer player_id) throws SQLException {
        String sql = "update out_player\n" +
                "set reason = ?\n" +
                "where player_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, reason);
            statement.setInt(2, player_id);
            statement.executeUpdate();
        }
    }

    public List<OutPlayerRespDTO> findAll() throws SQLException {
        List<OutPlayerRespDTO> outPlayers = new ArrayList<>();
        String sql = "select\n" +
                "o_tb.player_id,\n" +
                "o_tb.reason,\n" +
                "p_tb.name,\n" +
                "p_tb.position,\n" +
                "p_tb.created_at\n" +
                "from out_player o_tb\n" +
                "inner join player p_tb\n" +
                "on o_tb.player_id = p_tb.id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                OutPlayerRespDTO dto = OutPlayerRespDTO.builder()
                        .playerId(rs.getInt("o_tb.player_id"))
                        .name(rs.getString("p_tb.name"))
                        .position(rs.getString("p_tb.position"))
                        .reason(rs.getString("o_tb.reason"))
                        .createdAt(rs.getTimestamp("p_tb.created_at"))
                        .build();
                outPlayers.add(dto);
            }
        }
        return outPlayers;
    }

    public OutPlayer findById(Integer player_id) throws SQLException {
        OutPlayer outPlayer = null;
        String sql = "select *\n" +
                "from out_player\n" +
                "where player_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, player_id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                outPlayer = new OutPlayer(
                        rs.getInt("id"),
                        rs.getInt("player_id"),
                        rs.getString("reason"),
                        rs.getTimestamp("created_at")
                );
            }
        }
        return outPlayer;
    }
}