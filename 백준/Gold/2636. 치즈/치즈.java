import java.util.*;
import java.io.*;

public class Main {

    public static int cheese[][];
    public static int x;
    public static int y;
    public static int meltingCheeses = 0; // 가장 최근 녹을 치즈의 개수
    public static int meltingCount = 0; // 치즈가 녹는데까지 걸리는 시간

    public class Point {
        public int xPos;
        public int yPos;

        public Point(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }
    }

    public boolean process() {

        int currentMeltingCheeses = 0; // 현 스텝에서 녹을 치즈의 개수

        // 방문 및 변경을 체크할 배열을 만들어준다.
        int cheeseVisited[][] = new int[x][y];


        Deque<Point> deque = new ArrayDeque<>();
        int dx[] = new int[]{0, 0, 1, -1};
        int dy[] = new int[]{1, -1, 0, 0};

        deque.addLast(new Point(0, 0));
        cheeseVisited[0][0] = 1;

        // 큐(덱)가 없어질때까지
        while (!deque.isEmpty()) {
            Point point = deque.pollFirst();
            int xPos = point.xPos;
            int yPos = point.yPos;

            // 상하좌우를 검사한다.
            for (int k = 0; k < 4; k++) {
                int nx = xPos + dx[k];
                int ny = yPos + dy[k];

                // 범위안에 들어가면서, 방문체크를 하지 않은곳을 검사한다.
                if (nx >= 0 && nx < x && ny >= 0 && ny < y && cheeseVisited[nx][ny] == 0) {
                    if (cheese[nx][ny] == 1) {
                        cheeseVisited[nx][ny] = 2;
                        currentMeltingCheeses++;
                    }

                    // 계속 탐색을 이어가야함.
                    if (cheese[nx][ny] == 0) {
                        cheeseVisited[nx][ny] = 1;
                        deque.addLast(new Point(nx, ny));
                    }
                }
            }
        }


        // 현재 지울 치즈가 0개라면, 이전의 치즈를 세어둔게 마지막임.
        if (currentMeltingCheeses == 0) {
            return false;
        }

        // 치즈의 개수를 기억한다.
        meltingCheeses = currentMeltingCheeses;
        meltingCount++;

        // 치즈를 지워주는 로직을 수행한다.
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (cheeseVisited[i][j] == 2) {
                    cheese[i][j] = 0;
                }
            }
        }

        return true;
    }

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        cheese = new int[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (process()) {}

        System.out.println(meltingCount);
        System.out.println(meltingCheeses);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}

