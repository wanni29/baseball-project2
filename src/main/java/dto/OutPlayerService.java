package dto;

import model.outplayer.OutPlayerDAO;
import model.player.PlayerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerService {

    // 소켓 연결
    Connection connection;
    OutPlayerDAO outPlayerDAO;
    PlayerDAO playerDAO;

    public OutPlayerService(Connection connection) {
        this.connection = connection;
        this.outPlayerDAO = new OutPlayerDAO(connection);
        this.playerDAO = new PlayerDAO(connection);

    }


    // 퇴출 선수 등록
    public void addOutPlayer(String requestContent) throws Exception {
        String[] parts = requestContent.split("&");
        String[] parts1 = parts[0].split("=");
        String[] parts2 = parts[1].split("=");

        int playerId = Integer.parseInt(parts1[1]);
        String reason = parts2[1];

        outPlayerDAO.insert(playerId, reason);
        playerDAO.update(null, playerId);

        System.out.println("성공");
    }

    // 퇴출 선수 목록 조회
    public void outPlayerList() throws Exception {
        List<OutPlayerRespDTO> outPlayers = outPlayerDAO.findAll();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("------ 퇴출 목록 ------");
        outPlayers.forEach(outPlayerRespDTO -> {
            System.out.println("선수 번호 : " + outPlayerRespDTO.getPlayerId());
            System.out.println("선수 이름 : " + outPlayerRespDTO.getName());
            System.out.println("선수 포지션 : " + outPlayerRespDTO.getPosition());
            System.out.println("퇴출 이유 : " + outPlayerRespDTO.getReason());
            System.out.println("퇴출일 : " + dateFormatter.format(outPlayerRespDTO.getCreatedAt()));
            System.out.println("--------------------");
        });
    }

}