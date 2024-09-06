import java.util.*;
import java.io.*;

public class Main {
    public static List<ArrayList<String>> map = new ArrayList<>();
    public static int n;
    public static int m;

    public class Point{
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
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            map.add(new ArrayList<>(Arrays.asList(line.split(""))));
        }
        int maximumDistance = -1;
        // L이 나오면 항상 bfs 를 수행해서 가장 큰 값을 가진다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map.get(i).get(j).equals("W")) continue;
                maximumDistance = Math.max(maximumDistance, bfs(i, j));
            }
        }
        System.out.println(maximumDistance);
    }

    public Integer bfs(int startX, int startY){
        int[][] visited = new int[n][m];

        Deque<Point> queue = new ArrayDeque<>();
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};

        int distance = -1;
        visited[startX][startY] = 1; // 시작점은 1로 시작한다.
        queue.addLast(new Point(startX, startY));

        while (!queue.isEmpty()) {
            Point point = queue.removeFirst();
            int xPos = point.xPos;
            int yPos = point.yPos;

            for (int i = 0; i < 4; i++) {
                int nx = xPos + dx[i];
                int ny = yPos + dy[i];

                if(nx>=0 && nx < n && ny >= 0 && ny < m){
                    if(map.get(nx).get(ny).equals("L") && visited[nx][ny] == 0){
                        visited[nx][ny] = visited[xPos][yPos] + 1;
                        distance = Math.max(distance, visited[nx][ny]);
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }

        return distance-1;
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
