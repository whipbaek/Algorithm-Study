import java.sql.Array;
import java.util.*;

class Solution {

    public int h;
    public int w;
    public List<Integer> solution(String [] maps) {
        h = maps.length;
        w = maps[0].length();
        List<Integer> result = new ArrayList<>();
        boolean visited[][] = new boolean[h][w];

        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                if(!visited[i][j] && maps[i].charAt(j) != 'X'){
                    result.add(bfs(i, j, maps[i].charAt(j), visited, maps));
                }
            }
        }

        Collections.sort(result);
        if(result.isEmpty()) result.add(-1);
        return result;
    }

    public int bfs(int x, int y, char value, boolean[][] visited, String[] maps){
        int dx[] = {0, -1, 1, 0};
        int dy[] = {1, 0, 0, -1};
        int sum = value - 48; // ASCII CODE
        Deque<Integer[]> deque = new ArrayDeque<>();
        deque.addLast(new Integer[]{x, y});
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            Integer[] integers = deque.removeFirst();

            x = integers[0];
            y = integers[1];

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < h && ny >= 0 && ny < w){
                    if(maps[nx].charAt(ny) != 'X' && !visited[nx][ny]){
                        deque.addLast(new Integer[]{nx, ny});
                        visited[nx][ny] = true;
                        sum += maps[nx].charAt(ny) - 48;
                    }
                }
            }
        }
        return sum;
    }
}