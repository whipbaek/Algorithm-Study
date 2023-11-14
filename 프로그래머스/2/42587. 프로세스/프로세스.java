import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {

        // 항상 빼야 할 숫자는 가장 큰 숫자.
        // 회차마다 가장 큰 숫자를 구한다
        // 그리고 내가 그 숫자라면 체킹해주고 빠졌다고 판단한다. (set에 idx 넣어준다.)
        // 만약 내가 찾던 위치의 친구라면 리턴해주고 끝.
        Set<Integer> s = new HashSet<>();

        int idx = 0;
        int cnt = 1;
        
        while(true){ // O(N)
            
            
            int maxVal = -1;
            for(int i=0; i<priorities.length; i++){ // O(N)
                if(!s.contains(i)){
                    maxVal = Math.max(maxVal, priorities[i]);
                }
            }
            
            // 만약 지금 빼어야 할 숫자면서 체킹되지 않았다면.
            if(maxVal == priorities[idx] && !s.contains(idx)){ 
                // 내가 찾던 위치라면
                if(idx == location){
                    return cnt;
                }
                // 아니라면
                s.add(idx);
                cnt++;
            }

            if(idx + 1 == priorities.length){
                idx = 0;
            } else{
                idx++;
            }
        }
    }
}