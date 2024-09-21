import java.util.*;
import java.io.*;

public class Main {
    public static int[] parent;

    public static class Node {
        int s;
        int e;
        int w;

        public Node(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

    }

    public void solution() throws Exception {
        int answer = 0;

        // Minimum Spanning Tree
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<Node> nodes = new ArrayList<>();
        parent = new int[v + 1];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            nodes.add(new Node(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    )
            );
        }

        nodes.sort(((o1, o2) -> o1.w - o2.w));

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (Node node : nodes) {

            int p1 = find(node.s);
            int p2 = find(node.e);

            // 같을 경우
            if (p1 == p2) continue;

            // 작은 값을 부모로 한다.
            if (p1 < p2) parent[p2] = p1;
            if (p2 < p1) parent[p2] = p1;
            answer += node.w;
        }

        System.out.println(answer);
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
