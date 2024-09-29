import java.util.*;
import java.io.*;

public class Main {
    public static Integer[] parent;
    public void solution() throws Exception {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Vertex & Edge
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new Integer[n + 1];

        // Initialize
        for(int i=0; i<n+1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            // 부모가 다르다면 작은값을 부모로 갱신해준다.
            int v1Parent = find(v1);
            int v2Parent = find(v2);

            // 같다면 연결하지 않는다. ( = 연결을 끊는다 -> answer + 1)
            if (v1Parent == v2Parent) {
                answer++;
                continue;
            }

            // 다를 경우 -> 작은값의 부모로 선택한다.
            if (v1Parent <= v2Parent) {
                parent[v2Parent] = parent[v1Parent];
            }

            if (v1Parent > v2Parent) {
                parent[v1Parent] = parent[v2Parent];
            }

        }
        // 최적화 작업
        for(int i=1; i<n+1; i++) find(i);

        Set<Integer> set = new HashSet<>(Arrays.asList(parent));
        // 종류가 한개가 되기 위한 만큼 answer 에 더한다.
        answer += set.size() - 2;
        System.out.println(answer);

        // 모두 끝난다음에, 부모 개수 - 1 만큼 더해서 답을 도출한다. (find 횟수)
    }

    public int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
