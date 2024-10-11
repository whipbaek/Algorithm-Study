import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        int low = 1;
        int high = 100000;
        
        
        while(low <= high){
            
            int mid = (low + high) / 2;
            
            boolean result = process(diffs, times, limit, mid);

            if(result){
                high = mid-1;
                answer = Math.min(mid, answer);
            }
            
            if(!result){
                low = mid+1;
            }
        }
        
        
        return answer;
    }
    
    public boolean process(int [] diffs, int[]times, 
                           long limit, int level){
        long sum = 0L;
        for(int i=0; i<diffs.length; i++){
            
            if(level >= diffs[i]) {
                sum += times[i];
                continue;
            }
            
            sum += (diffs[i] - level) * (times[i] + times[i-1]);
            sum += times[i];
        }
        if(sum > limit) return false;
        return true;
    }
    
}