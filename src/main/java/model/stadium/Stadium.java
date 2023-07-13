package model.stadium;

import lombok.*;

import java.sql.Timestamp;

@Getter
public class Stadium {
    // 모델링 자리 입니다 : )
    private Integer id;
    private String name;
    private Timestamp createAt;

    @Builder
    public Stadium(Integer id, String name, Timestamp createAt) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
    }
}
