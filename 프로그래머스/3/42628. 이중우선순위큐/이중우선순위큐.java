import java.util.*;

class Solution {
    
    public int getTarget(PriorityQueue<Integer> pq, Map<Integer, Integer> m){
        while(pq.peek() != null){
            // 값이 있으면서, 유효한 값이라면.
            if(m.get(pq.peek()) == 1){
                return pq.poll();
            } 
            pq.poll();
        }
        return 0;
    }
    
    public int[] solution(String[] operations) {

        PriorityQueue<Integer> pq_max = new PriorityQueue<>((x, y) -> y - x);
        PriorityQueue<Integer> pq_min = new PriorityQueue<>();
        Map<Integer, Integer> m = new HashMap<>();
        
        for(String oper : operations){
            // min pq
            if(oper.equals("D -1")){
                while(true){
                    if(pq_min.peek() == null) break;
                    if(m.get(pq_min.peek()) == 1) { //유효한 값일 경우.
                        m.put(pq_min.peek(), 0);
                        pq_min.poll();
                        break;
                    }
                    pq_min.poll(); //유효하지 않은 경우
                }
                
            // max pq
            } else if(oper.equals("D 1")){
                while(true){
                    if(pq_max.peek() == null) break;
                    if(m.get(pq_max.peek()) == 1) { //유효한 값일 경우.
                        m.put(pq_max.peek(), 0);
                        pq_max.poll();
                        break;
                    }
                    pq_max.poll(); //유효하지 않은 경우
                }
            } else{
                Integer val = Integer.parseInt(oper.split(" ")[1]);
                pq_min.add(val);
                pq_max.add(val);
                m.put(val, 1);
            }
        }

        int max_value = getTarget(pq_max, m);
        int min_value = getTarget(pq_min, m);
        
        int[] answer = {max_value, min_value};
        return answer;
    }
}