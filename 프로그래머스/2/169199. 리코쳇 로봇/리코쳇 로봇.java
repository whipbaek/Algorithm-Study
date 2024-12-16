import java.util.*;

class Solution {
    
    static class Node {
        public int x;
        public int y;
        public int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] array)  {

        // Initialize
        List<ArrayList<String>> board = new ArrayList<>();
        int r = array.length;
        int c = 0;
        for (int i = 0; i < r; i++) {
            String[] split = array[i].split("");
            c = split.length;
            board.add(new ArrayList<>(Arrays.asList(split)));
        }

        int[][] visited = new int[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }


        Node startPoint = null;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(board.get(i).get(j).equals("R")){
                    startPoint = new Node(i, j, 0);
                }
            }
        }

        // 시작지점에서 bfs 를 한다.
        // bfs는 항상 4방향 모두 취한다.
        // 단, 다음 방문점은 그 방향의 끝이다.
        // 새로운 방문점에서도 4방향 모두 검사하고 이동한다.

        Deque<Node> queue = new ArrayDeque<>();
        queue.add(startPoint);

        while(!queue.isEmpty()){
            Node currentNode = queue.removeFirst();
            int x = currentNode.x;
            int y = currentNode.y;
            int cnt = currentNode.cnt;

            // 이 지점을 더 많은 횟수로 왔다면 pass 한다.
            if(visited[x][y] <= cnt) continue;

            visited[x][y] = cnt; // 도착점에 내가 몇번만의 이동에 왔는지 체크한다.

            // 4방향 모두 queue 에 넣는다.
            for (int i = 0; i < 4; i++) {
                // 이미 그 방향으로 한 전적이 있다면 돌지 않는다.
                // 이곳에 내가 방문했을때 최소 횟수로 왔으면 더 탐색이 필요하다.
                // 이곳에 내가 왔을때 최소 횟수가 아니라면 탐색이 필요 없다.


                int nx = x;
                int ny = y;

                // 해당 방향 끝까지 이동한다.
                while(true){
                    nx += dx[i];
                    ny += dy[i];

                    // 범위를 벗어나거나 장애물을 만나면 탐색을 멈춰야 한다. (도착점)
                    if(nx < 0 || nx >= r || ny < 0 || ny >= c || board.get(nx).get(ny).equals("D")){

                        // 한 칸 뒤로 무른다.
                        nx -= dx[i];
                        ny -= dy[i];

                        // 만약 여기가 골 지점이었다면? 탐색을 그만한다.
                        if(board.get(nx).get(ny).equals("G")){
                            visited[nx][ny] = Math.min(visited[nx][ny], cnt+1);
                            break;
                        }
                        queue.addLast(new Node(nx, ny, cnt+1));
                        break;
                    }
                }
            }

        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(board.get(i).get(j).equals("G")){
                    return visited[i][j] == Integer.MAX_VALUE ? -1 : visited[i][j];
                }
            }
        }
        
        return -1;
    }
}