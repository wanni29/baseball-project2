package dto;

import db.DBConnection;
import model.stadium.Stadium;
import model.stadium.StadiumDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StadiumService {

    Connection connection;
    StadiumDAO stadiumDAO;

    public StadiumService(Connection connection, StadiumDAO stadiumDAO) {
        this.connection = connection;
        this.stadiumDAO = stadiumDAO;
    }

    // 3.1 야구장 등록
    //    요청 : 야구장등록?name=잠실야구장
    //    응답 : 성공이라는 메시지를 출력한다.
    public void StadiumRegister(String requestContent)throws Exception{
        String[] parts = requestContent.split("=");
        String name = parts[1];
        stadiumDAO.insert(name);
        System.out.println("성공");
    }

    // 3.2 전체 야구장 목록보기
    //    요청 : 야구장목록
    //    응답 : 야구장 목록은 Model -> Stadium을 List에 담아서 출력한다.
    public void StadiumViewList() throws Exception {
        List<Stadium> stadiumList = stadiumDAO.findAll();
        int length = stadiumList.size();

        System.out.println("------ 야구장 목록 ------");
        for (int i = 0; i < length; i++) {
            Stadium stadium = stadiumList.get(i);
            System.out.println((i+1)+ ":" + stadium.getName());
        }
        System.out.println("---------------------");
    }
}
