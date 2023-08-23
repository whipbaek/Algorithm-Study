class Solution {
    public int[] dx = {0, 0, 1, -1};
    public int[] dy = {1, -1, 0, 0};
    public int[][] mem;

    public int answer = Integer.MAX_VALUE;

    public void dfs(int x, int y, int[][] board, boolean[][] visited, int n, int from, int cost){
        if(x == n-1 && y == n-1){
            this.answer = Math.min(this.answer, cost);
            return;
        }
        
        if(cost > answer) return;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 안에 들어왔으며, 벽이 아니며, 방문하지 않은 칸인경우.
            if(nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0 && !visited[nx][ny]){
                visited[nx][ny] = true;
                if(from == i || from == -1){ //같은 방향일경우
                        mem[nx][ny] = cost+100;
                        dfs(nx, ny, board, visited, n, i, cost + 100);
                } else{ // 다른 방향일경우
                    if(mem[nx][ny] >= cost+600){
                        mem[nx][ny] = cost + 600;
                        dfs(nx, ny, board, visited, n, i, cost + 600);
                    }
                }
                visited[nx][ny] = false;
            }
        }
    }

    public void memInit(int l){
        mem = new int[l][l];
        for(int i=0; i<l; i++){
            for(int j=0; j<l; j++){
                mem[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public int solution(int[][] board) {
        int l = board.length;
        this.memInit(l);
        boolean[][] visited = new boolean[l][l];
        visited[0][0] = true;
        dfs(0, 0, board, visited, l, -1, 0);

        return this.answer;
    }
}