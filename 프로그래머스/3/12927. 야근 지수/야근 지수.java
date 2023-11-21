/*
제곱이 되었을 때 -> 가중치가 낮아야 함
일의 개수 -> 20,000 개
제곱 -> 수 하나의 차이가 큰 차이냄
큰 수부터 줄여야 할 듯.
*/

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y-x);
        for(int i=0; i<works.length; i++)
            pq.add(works[i]);
       
        while(pq.peek() != null && n != 0){
            int val = pq.poll();
            val--;
            n--;
            if(val != 0) pq.add(val);
        }
        
        while(pq.peek() != null){
            int val = pq.poll();
            answer += (val * val);
        }
        
        return answer;
    }
}