package model.team;

import lombok.*;

import java.sql.Timestamp;

@Getter
public class Team {
    // 모델링 자리 입니다 : )
    private Integer id;
    private Integer stadiumId;
    private String name;
    private Timestamp reatedAt;

    @Builder
    public Team(Integer id, Integer stadiumId, String name, Timestamp reatedAt) {
        this.id = id;
        this.stadiumId = stadiumId;
        this.name = name;
        this.reatedAt = reatedAt;
    }
}
