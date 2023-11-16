import java.util.*;

class Solution {
    public long solution(int[][] triangle) {
        long answer = 0; 
        int l = triangle.length;
        
        long dp[][] = new long[501][501];
        dp[0][0] = triangle[0][0];
        int height = triangle.length;

        for(int i=1; i<height; i++){
            for(int j=0; j<=i; j++){
                
                int val = triangle[i][j];
                 
                 if(j==0) {
                     dp[i][j] = val + dp[i-1][j];
                 } else{
                     dp[i][j] = Math.max(val+dp[i-1][j], val+dp[i-1][j-1]);
                 }
            }
        }

        for(int i=0; i<dp[l-1].length; i++){
            answer = Math.max(answer, dp[l-1][i]);
        }
        
        return answer;
    }
}