import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public class Node{
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<ArrayList<Integer>> iceberg = new ArrayList<>();
    public static int r;
    public static int c;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};


    public void solution() throws Exception {
        int time = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for(int i=0; i<r; i++) {
            iceberg.add(
                    new ArrayList<>(Arrays.stream(br.readLine().split(" "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()))
            );
        }

        int icebergCount;
        while (true) {
            icebergCount = getIcebergCount();
            if(icebergCount > 1 || icebergCount == 0) {
                break;
            }
            melting();
            time++;
        }

        if(icebergCount > 1){
            System.out.println(time);
            return;
        }

        System.out.println(0);

    }

    public void melting() {

        int[][] meltingArray = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(iceberg.get(i).get(j) == 0) continue;
                int meltingCount = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx >= 0 && nx < r && ny >= 0 && ny < c && iceberg.get(nx).get(ny) == 0){
                        meltingCount++;
                    }
                }
                meltingArray[i][j] = meltingCount;
            }
        }

        // 녹여주기
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(iceberg.get(i).get(j) - meltingArray[i][j] < 0){
                    iceberg.get(i).set(j, 0);
                    continue;
                }
                iceberg.get(i).set(j, iceberg.get(i).get(j) - meltingArray[i][j]);
            }
        }
    }

    public int getIcebergCount(){
        int count = 0;
        boolean[][] visited = new boolean[r][c];

        // bfs 를 수행해서, 몇개의 대륙이 있는지 확인한다.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                // 이미 방문했거나, 0 인 경우에는 bfs 를 수행하지 않는다.
                if(visited[i][j] || iceberg.get(i).get(j) == 0) continue;

                Deque<Node> queue = new ArrayDeque<>();
                queue.addLast(new Node(i,j));
                visited[i][j] = true;

                while(!queue.isEmpty()){
                    Node currentNode = queue.removeFirst();
                    int x = currentNode.x;
                    int y = currentNode.y;

                    // 상하좌우 검색한다.
                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        // 범위안에 들며, 방문하지 않았으며, 값이 0이 아닌경우
                        if(nx >= 0 && nx < r && ny >= 0 && ny < c && !visited[nx][ny] && iceberg.get(nx).get(ny) != 0){
                            queue.addLast(new Node(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
