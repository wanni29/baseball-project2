package model.team;

import dto.PositionRespDto;
import dto.TeamRespDTO;
import model.player.Player;
import model.stadium.Stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    // C - create
    // R - read
    // U - upadate
    // D - delete

    private Connection connection;

    public TeamDAO(Connection connection) {
        this.connection = connection;
    }

    // team 추가 (stadiumId, name 입력시 추가)
    public void insert(int stadiumId, String name)throws SQLException {
        String sql = "INSERT INTO team (stadium_id, name, reated_at) VALUES (?, ?, now())";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,stadiumId);
            statement.setString(2,name);
            statement.executeUpdate();
        }
    }

    // team 삭제 (name 입력시 삭제)
    public void delete(String name)throws SQLException{
        String sql = "delete from team where name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,name);
            statement.executeUpdate();
        }
    }

    // team 수정(id 입력시 name 수정)
    public void update(int id, String name)throws SQLException{
        String sql = "update team set id = ? where name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.executeUpdate();
        }
    }

    // team 전체 조회
    public List<TeamRespDTO> findAll()throws SQLException{
        List<TeamRespDTO> teamList = new ArrayList<>();
        String sql = "select\n" +
                "st.id,\n" +
                "st.name,\n" +
                "te.name,\n" +
                "te.reated_at \n" +
                "from stadium st inner join team te\n" +
                "on st.id = te.stadium_id";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                TeamRespDTO dto = TeamRespDTO.builder()
                        .id(rs.getInt("st.id"))
                        .stadiumName(rs.getString("st.name"))
                        .teamName(rs.getString("te.name"))
                        .reatedAt(rs.getTimestamp("te.reated_at"))
                        .build();
                teamList.add(dto);
            }
        }
        return teamList;
    }

    // team 상세 조회 (id 입력시 조회)
    public Stadium findById(int id)throws SQLException{
        Stadium team = null;
        String sql = "select * from team where id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                team = new Stadium(
                        rs.getInt("id"),
                        //rs.getInt("stadium_id"),
                        rs.getString("name"),
                        rs.getTimestamp("reated_at")
                );
            }
        }
        return team;
    }

    public List<PositionRespDto> pivotStyle(int anythingTeamId, int teamId1, int teamId2, int teamId3) throws Exception {
        List<PositionRespDto> dtos = new ArrayList<>();
        String sql = "SELECT \n" +
                "  MAX(IF(team_id = ?, p_tb.position, '')) AS position,\n" +
                "  MAX(IF(team_id = ?, p_tb.name, '')) AS LG,\n" +
                "  MAX(IF(team_id = ?, p_tb.name, '')) AS NC,\n" +
                "  MAX(IF(team_id = ?, p_tb.name, '')) AS KIA\n" +
                "FROM player p_tb\n" +
                "INNER JOIN team t_tb ON p_tb.team_id = t_tb.id\n" +
                "GROUP BY p_tb.position";


        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, anythingTeamId);
            statement.setInt(2, teamId1);
            statement.setInt(3, teamId2);
            statement.setInt(4, teamId3);

            // 봐봐 윗부분까지는 jvm에서 sql구문을 이용해서 db 접근을 한다음 스캔을 한 상황이
            // 여기 까지인거야
            // 이제 밑에 부분에서는 jvm이 자바의 생태계로 다시 돌아와서
            // 또 다른 일을 하는거야.

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    PositionRespDto positionRespDto = PositionRespDto.builder()
                            .postion(rs.getString("position"))
                            .LG(rs.getString("LG"))
                            .NC(rs.getString("NC"))
                            .KIA(rs.getString("KIA"))
                            .build();
                    dtos.add(positionRespDto);
                }
            }
        }
        return dtos;

    }


}
