import java.util.*;
import java.io.*;

public class Main {

    public static int[] parent;
    public static int totalWeight = 0;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n];

        // parent 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken())-1; // start
            edges[i][1] = Integer.parseInt(st.nextToken())-1; // end
            edges[i][2] = Integer.parseInt(st.nextToken()); // weight
        }

        // 정렬
        Arrays.sort(edges, ((o1, o2) -> {
            return o1[2] - o2[2];
        }));

        // kruskal Algorithm
        for (int i = 0; i < m; i++) {
            int startNode = edges[i][0];
            int endNode = edges[i][1];
            int weight = edges[i][2];

            int startParent = find(startNode);
            int endParent = find(endNode);

            // 부모가 같다면 사이클이 있는것
            if(startParent == endParent) continue;

            if(startParent < endParent){
                parent[startParent] = endParent;
            } else{
                parent[endParent] = startParent;
            }

            totalWeight += weight;
        }

        System.out.println(totalWeight);
    }

    // parent 찾기
    public int find(int x){
        if(parent[x] == x){
            return x;
        }
        else
            return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}
