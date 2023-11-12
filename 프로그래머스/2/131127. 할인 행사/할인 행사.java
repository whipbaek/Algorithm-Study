import java.util.*;

class Solution {
    
    public boolean isPossible(Map<String, Integer> wish, Map<String, Integer> now){
        for(String k : wish.keySet()){
            // (1) wish 맵 key 값이 now에 존재하는가?
            if(now.containsKey(k) == false) return false;
            // (2) 존재한다면, wish 의 value 값과 같거나 큰 값을 now가 가지고 있는가?
            if(now.get(k) < wish.get(k)) return false;
        }
        return true;
    }
    
    public int solution(String[] want, int[] number, String[] discount) {
        
        int answer = 0;
        
        // 내가 원하는 값 초기화
        Map<String, Integer> wish = new HashMap<>();
        for(int i=0; i<want.length; i++){
            wish.put(want[i], number[i]);
        }

        Map<String, Integer> now = new HashMap<>();
        int count = 0;
        int front = 0;
        for(int back=0; back<discount.length; back++){
            
            // 10개가 차기 전에는 우선 다 넣어준다.
            if(count < 10){
                String k = discount[back];
                if(now.containsKey(k)){
                    now.put(k, now.get(k)+1);
                } else{
                    now.put(k, 1);
                }
                
                if(this.isPossible(wish, now)) answer++;
                count++;
                continue;
            }
            
            // 가장 앞의 값 삭제
            String fk = discount[front];
            if(now.get(fk) == 1){
                now.remove(fk);
            } else{
                now.put(fk, now.get(fk)-1);
            }
            
            // 새로운 값 추가
            String bk = discount[back];
            if(now.containsKey(bk)){
                now.put(bk, now.get(bk)+1);
            } else{
                now.put(bk, 1);
            }

            // 비교하기
            if(this.isPossible(wish, now)) answer++;
            front++;
            
        }
        
        return answer;
    }
}