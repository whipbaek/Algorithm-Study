import java.util.*;
import java.io.*;

public class Main {

    public static class Node {
        public int x;
        public int y;
        public int t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public static String[][] map;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0 ,0};
    public static Node startNode;
    public static Node endNode;
    public static int r;
    public static int c;

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new String[r][c];

        for(int i=0; i<r; i++){
            String[] temp = br.readLine().split("");
            for(int j=0; j<c; j++){
                map[i][j] = temp[j];
            }
        }


        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j].equals("S")) startNode = new Node(i, j, 0);
                if(map[i][j].equals("D")) endNode = new Node(i, j, 0);
            }
        }

        // 고슴도치가 bfs 를 수행한다.
        int result = process();
        if(result== -1){
            System.out.println("KAKTUS");
            return;
        }

        System.out.println(result);

    }


    public int process(){
        Deque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[r][c];
        queue.addLast(startNode);
        visited[startNode.x][startNode.y] = true;

        while (!queue.isEmpty()) {
            water(); // 물을 채운다.

            Node currentNode = queue.removeFirst();
            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(currentNode);

            // 같은 시각대 애들을 모두 넣는다.
            while(true){
                if(queue.isEmpty()) break;
                Node tempNode = queue.peekFirst();
                if(tempNode.t == currentNode.t){
                    nodes.add(queue.removeFirst());
                    continue;
                }
                break;
            }

            for (Node node : nodes) {
                int x = node.x;
                int y = node.y;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx >= 0 && nx < r && ny >= 0 && ny < c && !visited[nx][ny] && (map[nx][ny].equals(".") || map[nx][ny].equals("D"))){
                        if(map[nx][ny].equals("D")) {
                            return node.t + 1;
                        }
                        queue.addLast(new Node(nx, ny, node.t + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }

    public void water(){
        boolean[][] tempArray = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                // 물이 아니라면 넘어간다.
                if(!map[i][j].equals("*")) continue;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny].equals(".")){
                        tempArray[nx][ny] = true;
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(tempArray[i][j]){
                    map[i][j] = "*";
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
