import java.util.*;
import java.io.*;


// 각 영상 마다 연관동영상 리스트를 만듬
// 영상 최대 개수 : 5000개
// 질문 최대 개수 : 5000개
// n-1 개의 동영상 쌍을 골라서, 정점으로 표현한다.
// q 라는 영상을 보고 있을때 이 영상과의 usado 가 k

public class Main {

    public static class Node{
        public int target;
        public int weight;

        public Node(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static int n;
    public static int q;
    public static List<ArrayList<Node>> network = new ArrayList<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            network.add(new ArrayList<>());
        }

        // Initialize
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            // 양방향으로 그래프를 연결해준다.
            network.get(n1).add(new Node(n2, weight));
            network.get(n2).add(new Node(n1, weight));
        }

        // Process
        for (int i = 0; i < q; i++) {
            int answer = 0;
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken()) - 1;

            boolean[] visited = new boolean[n];
            Deque<Node> queue = new ArrayDeque<>();

            visited[q] = true;
            queue.add(new Node(q, 0));

            while (!queue.isEmpty()) {
                Node currentNode = queue.removeFirst();
                int currentTarget = currentNode.target;
                int currentWeight = currentNode.weight;

                for (Node node : network.get(currentTarget)) {
                    int target = node.target;
                    int weight = node.weight;

                    // 방문을 했거나, k 보다 작은 가중치를 가지면 더이상 나아가지 않는다.
                    if(visited[target] || weight < k) continue;

                    visited[target] = true;
                    queue.add(node);
                    answer++;
                }
            }
            System.out.println(answer);
        }

    }

    public static void main(String[] args) throws Exception {

        new Main().solution();
    }
}
