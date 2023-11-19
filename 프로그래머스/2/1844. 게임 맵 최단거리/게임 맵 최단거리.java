import java.util.*;

class Solution {
    
    public int[] dx = {0, 0, 1, -1};
    public int[] dy = {1, -1, 0 ,0};
    
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;
        int[][] distance = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        Queue<ArrayList<Integer>> q = new LinkedList<>();
        q.add(new ArrayList<Integer>(Arrays.asList(0, 0)));
        distance[0][0] = 1;
        visited[0][0] = true;
        
        while(q.peek() != null){
            int x = q.peek().get(0);
            int y = q.peek().get(1);
            q.poll();
            
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < m 
                   && visited[nx][ny] == false && maps[nx][ny] == 1){
                    q.add(new ArrayList<Integer>(Arrays.asList(nx, ny)));
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[x][y] + 1;
                }
            }
        }
                
        if(visited[n-1][m-1] == false) return -1;
        
        return distance[n-1][m-1];
    }
}