import java.util.*;

public class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

class Solution {
    public int solution(int[][] land) {

        int n = land.length;;
        int m = land[0].length;
        int areaNumber = 1;
        int[][] areaArray = new int[n][m]; // 번호를 매길 배열
        boolean[][] visited = new boolean[n][m]; // bfs 방문 체크
        Map<Integer, Integer> areaMap = new HashMap<>();

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j] && land[i][j] == 1){ // 방문하지 않은 경우에만 bfs 를 수행한다.
                    int areaSize = 1;
                    Deque<Node> queue = new ArrayDeque<>();
                    queue.addLast(new Node(i, j));
                    visited[i][j] = true;
                    areaArray[i][j] = areaNumber;

                    while (!queue.isEmpty()) {
                        Node currentNode = queue.removeFirst();
                        int x = currentNode.x;
                        int y = currentNode.y;

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && land[nx][ny] == 1){
                                areaSize++;
                                areaArray[nx][ny] = areaNumber;
                                visited[nx][ny] = true;
                                queue.addLast(new Node(nx, ny));
                            }
                        }
                    }
                    areaMap.put(areaNumber, areaSize);
                    areaNumber++;
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < m; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if(areaArray[j][i] != 0){
                    set.add(areaArray[j][i]);
                }
            }
            int temp = 0;
            for (Integer target : set) {
                temp += areaMap.get(target);
            }
            answer = Math.max(temp, answer);
        }

        
        return answer;
    }
}