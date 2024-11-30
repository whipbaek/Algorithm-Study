import java.util.*;
import java.io.*;

public class Main {

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Map<Integer, ArrayDeque<Node>> map = new HashMap<>();
        for(int i=1; i<=k; i++) map.put(i, new ArrayDeque<>());

        // Initialize
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                int keyVal = Integer.parseInt(line[j]);
                board[i][j] = keyVal;
                if(keyVal != 0 ) {
                    map.get(Integer.parseInt(line[j])).addLast(new Node(i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int rx = Integer.parseInt(st.nextToken());
        int ry = Integer.parseInt(st.nextToken());


        // 처음 이후, 퍼진 값들의 위치를 어떻게 기억할것인가?
        // 1번부터 k번까지 각각의 queue 를 가지고 있고, 돌면서 사용한다.

        // k초 동안 전염을 수행한다.
        while(s != 0){

            for (int i = 1; i <= k; i++) {
                // i 번 바이러스부터 전염 수행한다.
                ArrayDeque<Node> queue = map.get(i);
                ArrayDeque<Node> newQueue = new ArrayDeque<>();

                while(!queue.isEmpty()){
                    Node node = queue.removeFirst();
                    int x = node.x;
                    int y = node.y;

                    // 4방향을 검사한다.
                    for(int j=0; j<4; j++){
                        int nx = x + dx[j];
                        int ny = y + dy[j];

                        if(nx>= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0){
                            board[nx][ny] = i; // 바이러스 전염
                            newQueue.addLast(new Node(nx, ny));
                        }
                    }
                }

                // 새로운 queue 로 바꿔준다.
                map.put(i, newQueue);
            }
            s--;
        }

        System.out.println(board[rx-1][ry-1]);

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
