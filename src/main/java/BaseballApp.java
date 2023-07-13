
import db.DBConnection;
import dto.OutPlayerService;
import dto.PlayerService;
import dto.StadiumService;
import dto.TeamService;
import lombok.Getter;
import model.outplayer.OutPlayerDAO;
import model.player.PlayerDAO;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;
import java.util.InputMismatchException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

@Getter
public class BaseballApp {
    private static String stadiumRegister = "야구장등록";
    private static String stadiumViewList = "야구장목록";
    private static String teamRegister = "팀등록";
    private static String teamViewList = "팀목록";
    private static String playerRegister = "선수등록";
    private static String playerTeamViewList = "선수목록";
    private static String outPlayerRegister = "퇴출등록";
    private static String outPlayerViewList = "퇴출목록";
    private static String positionPlayerViewList = "포지션별목록";

    public static <InputMismatchException> void main(String[] args) throws Exception{

        // 연결
        Connection connection = DBConnection.getInstance();
        StadiumDAO stadiumDAO = new StadiumDAO(connection);
        StadiumService stadiumService = new StadiumService(connection,stadiumDAO);
        TeamDAO teamDAO = new TeamDAO(connection);
        PlayerDAO playerDAO = new PlayerDAO(connection);
        TeamService teamService = new TeamService(connection,teamDAO,playerDAO);
        PlayerService playerService = new PlayerService(connection,playerDAO,teamDAO);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);
        OutPlayerService outPlayerService = new OutPlayerService(connection);

        // 화면 출력 시작
        System.out.println("---야구장 관리 프로그램---");
        System.out.println("어떤 기능을 요청하시겠습니다?");
        System.out.println("1. " + stadiumRegister);
        System.out.println("2. " + stadiumViewList);
        System.out.println("3. " + teamRegister);
        System.out.println("4. " + teamViewList);
        System.out.println("5. " + playerRegister);
        System.out.println("6. " + playerTeamViewList);
        System.out.println("7. " + outPlayerRegister);
        System.out.println("8. " + outPlayerViewList);
        System.out.println("9. " + positionPlayerViewList);
        System.out.println("10. 프로그램 종료");

        // 스캐너 입력받기 시작
        Scanner sc = new Scanner(System.in);

        boolean invalidInput = true;
        while (invalidInput) {
            try {
                System.out.println("원하는 기능의 번호를 입력하세요 : ");
                int number = sc.nextInt();
                if (number == 1 || number == 3 || number == 5 || number == 6 || number == 7) {
                    System.out.println("등록할 내용을 입력하세요 : ");
                    String request = sc.next();
                    String[] requestSplit = request.split("\\?");
                    String requestConfirm = requestSplit[0];
                    String requestContent = requestSplit[1];

                    if (requestConfirm.equals(stadiumRegister)) {
                        stadiumService.StadiumRegister(requestContent);
                    }
                    if (requestConfirm.equals(teamRegister)) {
                        teamService.TeamRegister(requestContent);
                    }
                    if (requestConfirm.equals(playerTeamViewList)) {
                        teamService.TeamPlayerList(requestContent);
                    }
                    if (requestConfirm.equals(playerRegister)) {
                        playerService.addPlayer(requestContent);
                    }
                    if (requestConfirm.equals(outPlayerRegister)) {
                        outPlayerService.addOutPlayer(requestContent);
                    }
                }
                if (number == 2 || number == 4 || number == 8 || number == 9) {
                    if (number == 2) {
                        stadiumService.StadiumViewList();
                    } else if (number == 4) {
                        teamService.TeamViewList();
                    } else if (number == 8) {
                        outPlayerService.outPlayerList();
                    } else if (number == 9) {
                        playerService.showMeThePlayer();
                    }
                }
                if (number == 10) {
                    System.out.println("프로그램이 종료됩니다.");
                    sc.close();
                }

                invalidInput = false;
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 정수를 입력해야 합니다.");
                sc.nextLine();
            }
        }
    }
}
