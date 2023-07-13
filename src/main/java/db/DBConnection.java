package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getInstance(){
        // MySQL 연결 정보
        String url = "jdbc:mysql://localhost:3306/baseballdb"; // db 설정 해뒀어요 ! db에서 생성만 하시면 됩니다 !
        String username = "root";
        String password = "root1234";

        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("디버그 : DB연결 성공");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}