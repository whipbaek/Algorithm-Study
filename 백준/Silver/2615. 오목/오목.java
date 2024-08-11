import java.util.*;
import java.io.*;

/**
 * [문제] (https://www.acmicpc.net/problem/2615)
 * 검은 바둑알 1, 흰 바둑알 2
 */

public class Main {


    public boolean search(List<List<Integer>> board, int x, int y) {

        int[] dx = new int[]{1, -1, 0, 0, 1, -1, 1, -1 };
        int[] dy = new int[]{0, 0, 1, -1, 1, -1, -1, 1 };
        int startX = x;
        int startY = y;
        int stone = board.get(x).get(y);

        // 탐색을 시작한다. 좌우세트로, 상하세트로, 대각세트로
        for (int i = 0; i < 8; i+=2) {
            int continuous = 1;

            while (true) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위에 속하며, 같은 돌일경우 진행한다.
                if (nx >= 0 && nx < 19 && ny >= 0 && ny < 19 && board.get(nx).get(ny) == stone) {
                    continuous++;
                    x = nx;
                    y = ny;
                    continue;
                }
                break;
            }

            x = startX;
            y = startY;

            while (true) {
                int nx = x + dx[i+1];
                int ny = y + dy[i+1];

                // 범위에 속하며, 같은 돌일경우 진행한다.
                if (nx >= 0 && nx < 19 && ny >= 0 && ny < 19 && board.get(nx).get(ny) == stone) {
                    continuous++;
                    x = nx;
                    y = ny;
                    continue;
                }
                break;
            }

            x = startX;
            y = startY;

            // 승자가 판가름 난 경우
            if (continuous == 5) {

                System.out.println(stone);
                System.out.println((startX+1) + " " + (startY+1));
                return true;
            }
        }

        return false;
    }

    public void solution() throws Exception {

        List<List<Integer>> board = new ArrayList<>();
        inputProcess(board);

        // 좌우상하 대각선 모두 검사를 한다.
        // 보드의 끝에 도달했을 때, 다음 칸이 같은 값이 아닐 경우 멈춘다
        // 멈춘후에 연속된 돌이 몇 개 인지 카운트 한 값을 보고 정답을 판단한다.
        for (int i = 0; i < 19; i++) {
            for (int j =0; j < 19; j++) {
                if (board.get(j).get(i) == 0) continue;
                if (search(board, j, i)) return;
            }
        }

        // 이긴 경우가 없으면 0 을 출력한다.
        System.out.println(0);
    }

    public void inputProcess(List<List<Integer>> board) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;

        for (int i = 0; i < 19; i++) {
            inputLine = br.readLine();
            List<Integer> li = new ArrayList<>();

            String[] vals = inputLine.split(" ");
            for (String val : vals) {
                li.add(Integer.parseInt(val));
            }
            board.add(li);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
