package dto;

import model.player.PlayerDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.util.List;

public class PlayerService {

    Connection connection;
    PlayerDAO playerDAO;
    TeamDAO teamDAO;

    public PlayerService(Connection connection, PlayerDAO playerDAO, TeamDAO teamDAO) {
        this.connection = connection;
        this.playerDAO = playerDAO;
        this.teamDAO = teamDAO;
    }

    public void addPlayer(String requestContent) throws Exception {
        String[] parts = requestContent.split("&");
        String[] parts1 = parts[0].split("=");
        String[] parts2 = parts[1].split("=");
        String[] parts3 = parts[2].split("=");

        int teamId = Integer.parseInt(parts1[1]);
        String name = parts2[1];
        String position = parts3[1];

        playerDAO.insert(teamId, name, position);

        System.out.println("성공");
    }

    public void showMeThePlayer() throws Exception {
        List<PositionRespDto> dtos = teamDAO.pivotStyle(1, 1, 2, 3);
        System.out.println("---------------- 포지션 목록 ----------------");
        System.out.println("포지션\t\tLG\t\t\tNC\t\t\tKIA");
        for (PositionRespDto dto : dtos) {
            System.out.println(dto.getPostion() + "  \t\t" + dto.getLG() + "\t\t" + dto.getNC() + "\t\t" + dto.getKIA());

        }
        System.out.println("------------------------------------------");

    }

}