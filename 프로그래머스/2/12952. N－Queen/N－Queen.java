import java.util.*;

class Solution {
    
    public static int[] board;
    public static int answer = 0;
    
    public int solution(int n) {
        board = new int[n];
        process(0, n);
        return answer;
    }
    
    public void process(int row, int n){
        if(row == n){
            answer++;
            return;
        }

        for(int i=0; i<n; i++){
            board[row] = i;  // row번 행에 i 위치에 체스를 둔다.
            if(isPossible(row)) {
                process(row + 1, n);
            }
        }
    }

    public boolean isPossible(int row){
        for(int i=0; i<row; i++){
            if(board[i] == board[row]) return false; // 같은 열에 있는지 확인한다.
            if(Math.abs(row - i) == Math.abs(board[row] - board[i])) return false; // 대각선에 있는지 확인한다.
        }
        return true;
    }
    
}