import java.util.*;

class Solution {
    
    public long solution(int n) {
        long d[] = new long[2001]; // n번째 칸에 도달하는 방법의 개수
        d[1] = 1;
        d[2] = 2;
        
        for(int i=3; i<2001; i++){
            d[i] = (d[i-1] + d[i-2])%1234567;
        }
        
        return d[n];
    }
}