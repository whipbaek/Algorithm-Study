import java.util.*;
import java.io.*;

/**
 *
 * "그 상태가 틱택토 게임에서 발생할 수 있는 최종 상태인지를 판별"
 * -> 최종상태?
 * [Default] 꽉 찬 경우 or O가 하나 작은 경우 (X 부터 시작하기 때문에.)
 * 1. 한 유저가 승리하는 경우
 * 2. 꽉 찬 경우
 *
 * 꽉 찬 경우에는 -> O X 의 개수만 맞는지 판단하면 된다.
 * if 빈 칸이 존재하는 경우에는 -> O X 의 개수를 판단한다.
 *  if OX의 개수가 유효하지 않다면 False
 *  if OX의 개수가 유효하다면
 *      승리자가 있는지 판별한다.
 */

public class Main {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String line = br.readLine();
            boolean possible = false;
            if(line.equals("end")) break;

            Integer firstCount = 0;
            Integer secondCount = 0;
            Integer emptyCount = 0;

            // Initialize
            char[][] board = new char[3][3];
            int boardIdx = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char target = line.charAt(boardIdx++);
                    board[i][j] = target;

                    if(target == 'X') firstCount++;
                    if(target == 'O') secondCount++;
                    if(target == '.') emptyCount++;

                }
            }

            if (firstCount.equals(secondCount) || firstCount.equals(secondCount + 1)) {

                boolean firstWin = false;
                boolean secondWin = false;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // 연속되는 3개가 있는지 탐색한다.
                        char target = board[i][j];
                        if(target == '.') continue;

                        int dx[] = new int[]{0, 0, 1, -1, 1, -1, 1, -1};
                        int dy[] = new int[]{1, -1, 0, 0, 1, -1, -1, 1};


                        // 8 방향 모두 검사한다.
                        for (int k = 0; k < 8; k++) {
                            int x = i;
                            int y = j;
                            int consecutive = 1;

                            while (true) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];

                                if(nx >= 0 && nx < 3 && ny >= 0 && ny < 3 && target == board[nx][ny]){
                                    consecutive++;
                                    x = nx;
                                    y = ny;
                                    continue;
                                }
                                break;
                            }

                            if (consecutive == 3) {
                                if(target == 'X') firstWin = true;
                                if(target == 'O') secondWin = true;
                            }
                        }
                    }
                }

                // 정상적인 수순이라면 O가 이겨야함.
                if(firstCount.equals(secondCount)){
                    if(!firstWin && secondWin) possible = true;
                }

                // 정상적인 수순이라면 X가 이기거나, 꽉차서 판정이 안남.
                if(firstCount.equals(secondCount+1)){
                    if(firstWin && !secondWin) possible = true;
                    if(emptyCount == 0){
                        if(!firstWin && !secondWin) possible = true;
                    }
                }
            }

            if(possible)  System.out.println("valid");
            if(!possible) System.out.println("invalid");

        }
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
