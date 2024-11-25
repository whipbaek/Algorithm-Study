import java.util.*;

class Solution {
    
    public long solution(int k, int d) {
        
        long answer = 0;
        
        long x = 0L;
        long y = 0L;
        
        // x는 k만큼 좌표 이동을 한다.
        for(; x<=d; x+=k){
            long value = (long)((Math.pow(d, 2L) - Math.pow(x, 2L)));
            long cnt = (long)Math.sqrt(value);
            long res = cnt / k;
            
            answer += res + 1;
        }
        
        return answer;
    }
}