class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int r = land.length;
        int c = 4;
        
        // initialize
        int d[][] = new int[r][c];
        for(int i=0; i<4; i++) d[0][i] = land[0][i];
        
        for(int i=1; i<r; i++){
            for(int j=0; j<4; j++){
                if(j==0) d[i][j] = land[i][j] + Math.max(Math.max(d[i-1][1], d[i-1][2]),d[i-1][3]);
                if(j==1) d[i][j] = land[i][j] + Math.max(Math.max(d[i-1][0], d[i-1][2]),d[i-1][3]);
                if(j==2) d[i][j] = land[i][j] + Math.max(Math.max(d[i-1][1], d[i-1][0]),d[i-1][3]);                
                if(j==3) d[i][j] = land[i][j] + Math.max(Math.max(d[i-1][1], d[i-1][2]),d[i-1][0]);      
            }
        }
        
        for(int i=0; i<4; i++) answer = Math.max(answer, d[r-1][i]);

        return answer;
    }
}