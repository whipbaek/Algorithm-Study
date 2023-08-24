import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int[][] dp = new int[n+1][n+1];
        int MV = Integer.MAX_VALUE;
        int answer = MV;
        
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(i==j) dp[i][j] = 0;
                else dp[i][j] = MV;
            }
        }
        
        for(int i=0; i<fares.length; i++){
            int x = fares[i][0];
            int y = fares[i][1];
            int v = fares[i][2];
            
            dp[x][y] = v;
            dp[y][x] = v;
        }
        
        for(int k=1; k<n+1; k++){
            for(int x= 1; x< n+1; x++){
                for(int y= 1; y< n+1; y++){
                    if(dp[x][k] == MV || dp[k][y] == MV) continue;
                    dp[x][y] = Math.min(dp[x][y], dp[x][k] + dp[k][y]);
                }
            }
        }
        
        for(int i=1; i<n+1; i++){
            if(dp[s][i] == MV || dp[i][a] == MV || dp[s][i] == MV || dp[i][b] == MV) continue;
            answer = Math.min(answer,  dp[s][i] + dp[i][a] +  dp[i][b]);
        }
        
        return answer;
    }
}