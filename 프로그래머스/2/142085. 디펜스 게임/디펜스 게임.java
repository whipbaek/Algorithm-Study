import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int round = 0;
        int eLen = enemy.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 모든 라운드를 막는 경우
        if(eLen <= k) return eLen;

        // Init
        for (int i = 0; i < k; i++) {
            pq.add(enemy[i]);
            round++;
        }

        // check
        for (int i = k; i < eLen; i++) {
            pq.add(enemy[i]); // add enemy
            int enemyNum = pq.peek();
            if(n - enemyNum < 0){
                return round;
            }
            n -= enemyNum;
            pq.poll();
            round++;
        }

        return round;
    }
}