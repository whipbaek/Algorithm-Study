import java.util.*;

public class Solution {
    
    public int solution(int n) {
        int cnt = 0;
        
        while(n != 0){
            if(n%2==1){
                n-=1;
                cnt++;
            } else{
                n/=2;
            }
        }
        
        return cnt; 
    }
}