import java.util.*;
import java.io.*;

public class Main {

    public class Point {
        public int xPos;
        public int yPos;

        public Point(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }
    }

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int answer = 0;

        int storeCount = Integer.parseInt(br.readLine());

        int block[][] = new int[n + 1][m + 1];
        // 테두리 세팅
        for(int i=0; i<m+1; i++){
            block[0][i] = 101;
            block[n][i] = 101;
        }
        for(int i=0; i<n+1; i++) {
            block[i][0] = 101;
            block[i][m] = 101;
        }

        for (int i = 0; i < storeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if(direction == 1) block[0][distance] = i+1; // 북쪽
            if(direction == 2) block[n][distance] = i+1;
            if(direction == 3) block[distance][0] = i+1;
            if(direction == 4) block[distance][m] = i+1;
        }

        st = new StringTokenizer(br.readLine());
        int direction = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());

        int startX = 0;
        int startY = 0;
        if(direction == 1) {
            startX = 0;
            startY = distance;
        }

        if(direction == 2){
            startX = n;
            startY = distance;
        }

        if(direction == 3) {
            startX = distance;
            startY = 0;
        }

        if (direction == 4) {
            startX = distance;
            startY = m;
        }


        // bfs
        for (int i = 0; i < storeCount; i++) {

            int dx[] = new int[]{0, 0, -1, 1};
            int dy[] = new int[]{1, -1, 0, 0};
            int moveArray[][] = new int[n+1][m+1];

            // 매번 복사.
            int tempBlock[][] = new int[n+1][m+1];
            for (int j = 0; j < n + 1; j++) {
                for (int k = 0; k < m + 1; k++) {
                    tempBlock[j][k] = block[j][k];
                }
            }

            Deque<Point> queue = new ArrayDeque<>();
            queue.addLast(new Point(startX, startY));
            tempBlock[startX][startY] = 0; // 방문 표시

            int targetNumber = i+1;

            while (!queue.isEmpty()) {
                Point point = queue.pollFirst();
                int xPos = point.xPos;
                int yPos = point.yPos;

                for (int j = 0; j < 4; j++) {
                    int nx = xPos + dx[j];
                    int ny = yPos + dy[j];

                    if(nx >= 0 && nx < n+1 && ny >= 0 && ny < m+1 && tempBlock[nx][ny] != 0){
                        queue.addLast(new Point(nx, ny));
                        moveArray[nx][ny] = moveArray[xPos][yPos] + 1;
                        if(tempBlock[nx][ny] == targetNumber) answer += moveArray[nx][ny];
                        tempBlock[nx][ny] = 0;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}
