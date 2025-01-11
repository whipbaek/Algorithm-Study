import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**

 N개 마을 (Node?)
 M개 단방향 도로 (edge?)
 X번 마을

 본인의 마을 -> 파티마을
 파티마을 -> 본인의 마을



 */

public class Main {

    static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

    }

    public void solution() throws Exception {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from][to] = weight;
        }

        int[] answer = new int[n+1];

        // Dijkstra
        for (int i = 1; i <= n; i++) {
            if(i == x) continue;
            int[] temp = dijkstra(i, n, graph);
            answer[i] = temp[x];
        }


        int[] temp = dijkstra(x, n, graph);
        for (int i = 1; i <= n; i++) {
            answer[i] += temp[i];
        }
//
//        for (int i = 1; i <= n; i++) {
//            System.out.print(answer[i] + " ");
//        }
//        System.out.println();

        int idx = -1;
        int val = -1;

        for (int i = 1; i <= n; i++) {
            if(answer[i] > val){
                idx = i;
                val = answer[i];
            }
        }

        System.out.println(val);
    }

    // startPoint 에서 endPoint 까지의 거리를 구한다.
    public int[] dijkstra(int startPoint, int n, int[][] graph){
        int res = 0;
        // 가중치
        int[] weights = new int[n+1];
        boolean[] visited = new boolean[n+1];
        for(int i=0; i<n+1; i++) {
            weights[i] = Integer.MAX_VALUE;
        }
        weights[startPoint] = 0; // 시작점.

        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.weight - o2.weight));
        pq.add(new Node(startPoint, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int num = node.num;
            int weight = node.weight;

            if(visited[num]) continue;
            visited[num] = true;

            // num에서 뻗어나가면서 갱신 처리해준다.
            for(int i=1; i<=n; i++){
                if(graph[num][i] == 0) continue; // 값이 없는 경우 계산 불가.
                if(visited[i]) continue; // 방문한 값이면 패스.

                // 현재 점에서 다른 점으로 이동하는 값이 더 작다면 갱신해준다.

                if(weights[num] + graph[num][i] < weights[i] ){
                    weights[i] =  weights[num] + graph[num][i];
                    pq.add(new Node(i, weights[i]));
                }
            }
        }

//        System.out.println("========== res ==========");
//
//        System.out.println("start[" + startPoint + "] -> end[" + endPoint + "]");
//        for (int i = 1; i <= n; i++) {
//            System.out.print(weights[i] + " ");
//        }
//        System.out.println();
//
//        System.out.println("========== res ==========");
//        System.out.println();

        return weights;
    }



    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}

