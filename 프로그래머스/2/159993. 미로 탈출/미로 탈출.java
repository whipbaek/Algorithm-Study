import java.util.*;

class Solution {

    public int h;
    public int w;
    public int solution(String [] maps) {

        // s부터 레버 까지 최소칸
        // 레버부터 e 까지 최소칸
        // 중간에 하나라도 끝에 도달하지 못한다면 -1 return
        // 도달한다면 그 칸에서 검색

        h = maps.length;
        w = maps[0].length();

        int sTol = 0;
        int lToe = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(maps[i].charAt(j) == 'S') {
                    sTol = bfs(i, j, new int[h][w], maps, 'L');
                }

                if(maps[i].charAt(j) == 'L') {
                    lToe = bfs(i, j, new int[h][w], maps, 'E');
                }
            }
        }

        return (sTol != -1 && lToe != -1) ? sTol+lToe-2 : -1;
    }

    public int bfs(int x, int y, int[][] visited, String[] maps, char target){
        int dx[] = {0, -1, 1, 0};
        int dy[] = {1, 0, 0, -1};
        Deque<Integer[]> deque = new ArrayDeque<>();
        deque.addLast(new Integer[]{x, y});
        visited[x][y] = 1;

        while (!deque.isEmpty()) {
            Integer[] integers = deque.removeFirst();

            x = integers[0];
            y = integers[1];

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < h && ny >= 0 && ny < w){
                    if(maps[nx].charAt(ny) != 'X' && visited[nx][ny] == 0){
                        deque.addLast(new Integer[]{nx, ny});
                        visited[nx][ny] = visited[x][y] + 1;
                        if(target == maps[nx].charAt(ny)) return visited[nx][ny];
                    }
                }
            }
        }
        return -1;
    }
}