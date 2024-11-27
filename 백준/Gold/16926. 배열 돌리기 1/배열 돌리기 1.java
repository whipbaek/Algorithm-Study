import java.util.*;
import java.io.*;

public class Main {

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());

        int[][] board = new int[r][c];
        int[][] copyBoard = new int[r][c];
        // 행 만큼 입력받는다.
        for (int i = 0; i < r; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                board[i][j] = Integer.parseInt(line[j]);
                copyBoard[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < cnt; i++) {

            Point lt = new Point(0, 0);
            Point rt = new Point(0, c - 1);
            Point ll = new Point(r - 1, 0);
            Point rl = new Point(r - 1, c - 1);

            // 내부적으로는 r/2 번 회전한다.
            for (int l = 0; l < Math.min(r, c) / 2; l++) {

                // down (left top -> left low)
                for (int j = lt.x; j < ll.x; j++) {
                    board[j + 1][lt.y] = copyBoard[j][lt.y];
                }
                // right (left low -> right low)
                for (int j = ll.y; j < rl.y; j++) {
                    board[ll.x][j + 1] = copyBoard[ll.x][j];
                }

                // up (right low -> right top)
                for (int j = rl.x; j > rt.x; j--) {
                    board[j - 1][rl.y] = copyBoard[j][rl.y];
                }

                // left (right top -> left top
                for (int j = rt.y; j > lt.y; j--) {
                    board[rt.x][j - 1] = copyBoard[rt.x][j];
                }

                lt = new Point(lt.x + 1, lt.y + 1);
                rt = new Point(rt.x + 1, rt.y - 1);
                ll = new Point(ll.x - 1, ll.y + 1);
                rl = new Point(rl.x - 1, rl.y - 1);
            }

            // 회전이 끝나면 배열을 복사해준다.
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    copyBoard[j][k] = board[j][k];
                }
            }
        }


        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}


