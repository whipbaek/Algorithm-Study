import java.util.*;
import java.io.*;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static String[][] board;

    // 상하좌우 대각선
    public static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    public static int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};

    // Knight 이동 경로
    public static int[] kx = {1, -1, 1, -1, -2, -2, 2, 2 };
    public static int[] ky = {2, 2, -2, -2, 1, -1, 1, -1 };

    public void solution() throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 보드판 셋업
        board = new String[n][m];
        setup("Q");
        setup("K");
        setup("P");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String type = board[i][j];
                if(type == null) continue;
                // Queen 이 갈 수 있는곳을 체크한다.
                if(type.equals("Q")){
                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        while (true) {
                            if(nx >= 0 && nx < n && ny >= 0 && ny < m && (board[nx][ny] == null || board[nx][ny].equals("A"))){
                                board[nx][ny] = "A"; // Avaliable
                                nx += dx[k];
                                ny += dy[k];
                                continue;
                            }
                            break;
                        }
                    }
                }
                // Knight 가 갈 수 있는곳을 체크한다.
                if (type.equals("K")) {
                    for (int k = 0; k < 8; k++) {
                        int nx = i + kx[k];
                        int ny = j + ky[k];

                        if(nx >=0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == null){
                            board[nx][ny] = "A"; // Avaliable
                        }
                    }

                }

            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == null) answer++;
            }
        }
        System.out.println(answer);
    }

    public void setup(String type) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());

        for (int i = 0; i < count; i++) {
            board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = type;
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
