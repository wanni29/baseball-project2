package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter @AllArgsConstructor @Builder
public class TeamRespDTO {
    // stadium
    private Integer id;
    private String stadiumName;

    // team
    private String teamName;
    private Timestamp reatedAt;
}
