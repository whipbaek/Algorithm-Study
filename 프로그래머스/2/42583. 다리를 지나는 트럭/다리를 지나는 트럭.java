import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int now_weight = 0;
        
        Deque<Integer> deq = new ArrayDeque<>();
        
        // bridge 길이만큼 우선 채운다.
        for(int i=0; i<bridge_length; i++){
            deq.addLast(-1);
        }
        
        int cnt = 0;
        while(cnt < truck_weights.length){
            
            int res = deq.removeFirst();
            if(res != -1){
                now_weight -= res;
            } 
            
            int nextTarget = truck_weights[cnt];
            
            // 만약 트럭이 들어갈 수 있다면
            if(now_weight + nextTarget <= weight){
                deq.addLast(nextTarget); // 트럭을 넣어준다.
                now_weight += nextTarget; // 트럭의 무게를 더해준다.
                cnt++;
            } else{
                deq.addLast(-1); 
            }
            
            time++;
        }
        
        while(!deq.isEmpty()){
            deq.removeFirst();
            time++;
        }

        return time;
    }
}