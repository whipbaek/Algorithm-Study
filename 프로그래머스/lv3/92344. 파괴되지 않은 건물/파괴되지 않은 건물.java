import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skills) {
        
        // board와 같은 크기의 2차원 배열 선언
        int answer = 0;
        int r = board.length;
        int c = board[0].length;
        int[][] preSum = new int[r+1][c+1];
        
        for(int[] skill : skills){
            int type = skill[0];
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3]+1;
            int c2 = skill[4]+1;
            int degree = skill[5] * (type == 1 ? -1 : 1);

            preSum[r1][c1] += degree;
            preSum[r1][c2] -= degree;
            preSum[r2][c1] -= degree;
            preSum[r2][c2] += degree;
        }
        
        for(int i=0; i<r; i++){
            for(int j=1; j<c; j++){
                preSum[i][j] += preSum[i][j-1];
            }
        }
        
        for(int i=0; i<c; i++){
            for(int j=1; j<r; j++){
                preSum[j][i] += preSum[j-1][i];
            }
        }
        
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] + preSum[i][j] > 0) answer +=1;
            }
        }
        
        return answer;
    }
}