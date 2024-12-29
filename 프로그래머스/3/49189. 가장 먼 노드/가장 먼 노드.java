import java.util.*;

class Solution {
    
    static class Node{
        public int num;
        public int val;
        
        // Constructor
        public Node (int num, int val){
            this.num = num;
            this.val = val;
        }
    }
    
    public int solution(int n, int[][] edge) {
        
        int answer = 0;
        
        // 1. pq
        PriorityQueue<Node> pq = 
            new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        
        // 2. edge list
        Map<Integer, List<Integer>> distance = new HashMap<>();
        
        // 3. visited
        boolean[] visited = new boolean[n+1];
        
        // 4. dis list
        int[] dis = new int[n+1];
        dis[1] = 0;
        for(int i=2; i<=n; i++){
            dis[i] = Integer.MAX_VALUE;
        }
        
        // initialize
        pq.add(new Node(1, 0)); //start Point
        
        for(int i=1; i<=n; i++){
            distance.put(i, new ArrayList<>());
        }
        
        for(int i=0; i<edge.length; i++){
            distance.get(edge[i][0]).add(edge[i][1]);
            distance.get(edge[i][1]).add(edge[i][0]);
        }
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int num = node.num;
            int val = node.val;
            // System.out.println("poll num : " + num);
            
            if(visited[num]) continue;
            visited[num] = true;
            
            List<Integer> targetList = distance.get(num);
            
            for(Integer target : targetList){
                // update edge value
                dis[target] = Math.min(dis[target], val + 1);
                pq.add(new Node(target, dis[target]));
            }
        }
        
//        System.out.println();
//        for(int i=1; i<=n; i++){
//            System.out.println(i + ": " + dis[i]);
//        }
        
        int maxVal = -1;
        for(int i=1; i<=n; i++){
            maxVal = Math.max(maxVal, dis[i]);
        }
        
        for(int i=1; i<=n; i++){
            if(dis[i] == maxVal) answer++;
        }
        
        
        return answer;
    }
}