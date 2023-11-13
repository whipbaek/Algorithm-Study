import java.util.*;

class Solution {

    public int solution(int[] citations) {
        int answer = 0;
        
        // d[n] => n번 이상 사용된 논문의 개수
        int d[] = new int[10001];
        for(int i=0; i<citations.length; i++){
            int value = citations[i];
            for(int j=0; j<=value; j++){
                d[j] += 1;
            }
        }
        
        for(int i=0; i<10001; i++){
            if(d[i] >= i){
                answer = i;
            }
        }
        
        return answer;
    }
}