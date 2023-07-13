package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PositionRespDto {


    private String postion;
    private String LG;
    private String NC;
    private String KIA;


    @Builder
    public PositionRespDto(String postion, String LG, String NC, String KIA) {
        this.postion = postion;
        this.LG = LG;
        this.NC = NC;
        this.KIA = KIA;
    }


}