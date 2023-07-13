package model.outplayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;



@Getter

public class OutPlayer {
    // 모델링 자리 입니다 : )

    private Integer id;
    private Integer playerId;
    private String reason;
    private Timestamp createdAt;


    @Builder
    public OutPlayer(Integer id, Integer playerId, String reason, Timestamp createdAt) {
        this.id = id;
        this.playerId = playerId;
        this.reason = reason;
        this.createdAt = createdAt;
    }
}