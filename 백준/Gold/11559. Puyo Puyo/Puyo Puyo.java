import java.util.*;
import java.io.*;

public class Main {

    public static String[][] board = new String[12][6];
    public static int answer = 0;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Initialize
        for (int i = 0; i < 12; i++) {
            String[] line = br.readLine().split("");
            System.arraycopy(line, 0, board[i], 0, 6);
        }

        while (bfs()) {
            answer++;
            process();

//            for (int i = 0; i < 12; i++) {
//                for (int j = 0; j < 6; j++) {
//                    System.out.print(board[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

        }

        System.out.println(answer);
    }

    public boolean bfs(){
        boolean explosive = false;

        boolean[][] visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                // 빈공간이거나 방문한곳이라면 탐색하지 않는다.
                if(board[i][j].equals(".") || visited[i][j]) continue;

                String type = board[i][j];

                List<Node> list = new ArrayList<>();
                Deque<Node> queue = new ArrayDeque<>();

                list.add(new Node(i, j));
                queue.addLast(new Node(i, j));
                visited[i][j] = true;

                // bfs
                while(!queue.isEmpty()){

                    Node node = queue.removeFirst();
                    int x = node.x;
                    int y = node.y;

                    for (int k = 0; k < 4; k++) {
                        int nx = x+dx[k];
                        int ny = y+dy[k];

                        if(nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !visited[nx][ny]){
                            if(board[nx][ny].equals(type)){
                                queue.addLast(new Node(nx, ny));
                                list.add(new Node(nx, ny));
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }
                
                // bfs 가 끝난후에 뿌요뿌요가 되는지 확인한다.
                if(list.size() < 4) continue;

                explosive = true;
                for (Node node : list) {
                    board[node.x][node.y] = ".";
                }
            }
        }
        return explosive;
    }

    public void process(){
        // 밑 라인에서부터 . 을 검사한다.
        // .인 경우에는 위를 둘러보고 가장 가까운 값을 가져온다.

        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if(board[i][j].equals(".")){
                    // 위를 살펴본다.
                    for(int k=i; k>=0; k--){
                        if(!board[k][j].equals(".")){
                            board[i][j] = board[k][j];
                            board[k][j] = ".";
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
