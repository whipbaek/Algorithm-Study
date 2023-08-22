import java.util.*;

class Solution {
    public class Node implements Comparable<Node>{
        public int val;
        public int idx;

        public Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node target) {
            return this.val <= target.val ? 1 : - 1;
        }
    }

    public int solution(int[] stones, int k) {
        List<Integer> li = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        int fptr = 0;
        int rptr = k-1;

        for(int i=fptr; i<=rptr; i++){
            map.put(i, stones[i]);
            priorityQueue.add(new Node(stones[i], i));
        }
        li.add(priorityQueue.peek().val);
        fptr++;
        rptr++;

        while (rptr < stones.length) {
            map.remove(fptr - 1);
            map.put(rptr, stones[rptr]);
            priorityQueue.add(new Node(stones[rptr], rptr));

            fptr++;
            rptr++;

            while (true) {
                if (map.containsKey(priorityQueue.peek().idx)) {
                    li.add(priorityQueue.peek().val);
                    break;
                }
                priorityQueue.poll();
            }
        }

        int answer = 200000001;

        for(Integer val : li){
            if(answer > val) answer = val;
        }
        return answer;
    }
}