import java.util.*;

// n개의 원소들 각각의 합으로 s를 만들어야 한다.

class Solution {
    public int[] solution(int n, int s) {
        
        if(n > s) return new int[]{-1};
        
        int[] answer = new int[n];
        Arrays.fill(answer, s/n);
        int mod = s%n;
        int idx = n-1;
        while(mod!=0){
            answer[idx--]++;
            mod--;
        }
        
        
        
        return answer;
    }
}