import java.util.*;

class Solution {

    public ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    public int answer = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {

        // init
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }

        // node 는 1부터 n 까지 존재.
        for (int i = 0; i < wires.length; i++) {
            arr.get(wires[i][0]).add(wires[i][1]);
            arr.get(wires[i][1]).add(wires[i][0]);
        }

        for (int i = 0; i < wires.length; i++) {
            arr.get(wires[i][0]).remove((Integer) wires[i][1]);
            arr.get(wires[i][1]).remove((Integer) wires[i][0]);

            int n1 = bfs(new boolean[n + 1], wires[i][0], new ArrayDeque<>());
            int n2 = bfs(new boolean[n + 1], wires[i][1], new ArrayDeque<>());
            answer = Math.min(answer, Math.abs(n1 - n2));
            arr.get(wires[i][0]).add(wires[i][1]);
            arr.get(wires[i][1]).add(wires[i][0]);

        }
        return answer;
    }


    public int bfs(boolean[] visited, int node, Deque<Integer> deque) {
        deque.addLast(node);
        visited[node] = true;
        int cnt = 1;

        while (!deque.isEmpty()) {
            int value = deque.removeFirst();
            for (int val : arr.get(value)) {
                if (!visited[val]) {
                    visited[val] = true;
                    cnt++;
                    deque.addLast(val);
                }
            }
        }
        return cnt;
    }

}