import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 2변 이상에 닿은 치즈 -> 상하좌우를 보았을 때 2개 이상 없는 경우
// 밖에서 bfs 를 한다. 닿는 면에 count 를 해주고, 그 치즈들을 없애준다.
public class Main {

    static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] board = new int[r][c];
        int cheeseCnt = 0;
        int answer = 0;


        // Input
        for (int i = 0; i < r; i++) {
            String[] list = br.readLine().split(" ");
            for (int j = 0; j < list.length; j++) {
                int val = Integer.parseInt(list[j]);
                board[i][j] = val;
                if(val == 1) cheeseCnt++;
            }
        }

        // dfs -> 0, 0 부터에서 외벽 count 해주기.
        while(cheeseCnt != 0){ // 치즈 다 없어질때까지

            // 1. [0, 0] 부터  bfs 수행한다.
            // 2. 빈 공간에서 4방향을 확인하고 거기에 치즈가 있으면 count 를 더해준다.
            // 3. 탐색을 모두 끝내고 count가 2이상인 치즈는 없애준다.
            // 4. 시간을 증가시킨다.

            int[][] cheeseChecker = new int[r][c]; // 모두 0으로 체크
            boolean[][] visited = new boolean[r][c]; // 방문체크

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            int x = 0;
            int y = 0;

            Deque<Node> queue = new ArrayDeque<>();
            queue.addLast(new Node(x, y));
            visited[x][y] = true;

            while (!queue.isEmpty()) {
                Node node = queue.removeFirst();

                for (int i = 0; i < 4; i++) {
                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];

                    if (nx >= 0 && nx < r && ny >= 0 && ny < c && !visited[nx][ny]) {
                        if(board[nx][ny] == 1){ // 치즈라면 카운트만 증가시킨다.
                            cheeseChecker[nx][ny]++;
                            continue;
                        }

                        visited[nx][ny] = true; //방문처리
                        queue.addLast(new Node(nx, ny));
                    }

                }
            }

            // bfs 가 끝나면 cheeseChecker 를 확인하여 cheese 들을 없애준다.
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if(cheeseChecker[i][j] >= 2){
                        board[i][j] = 0;
                        cheeseCnt--;
                    }
                }
            }

            answer++;
        }
        System.out.println(answer);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}

