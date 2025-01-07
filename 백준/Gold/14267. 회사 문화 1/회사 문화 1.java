import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 단방향 그래프
        // 칭찬시, bfs 로 뻗어가면서 칭찬 점수를 스코어링한다.
        Map<Integer, List<Integer>> organization = new HashMap<>();
        Map<Integer, Integer> score = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] list = br.readLine().split(" ");

        for(int i=1; i<=n; i++) {
            organization.put(i, new ArrayList<>());
            score.put(i, 0);
        }

        for(int i=0; i<n; i++){
            if(i == 0) continue; // 사장은 상사가 없음.

            int senior = Integer.parseInt(list[i]);
            int junior = i + 1;

            organization.get(senior).add(junior);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int junior = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            score.put(junior, score.get(junior) + value);
        }

        // 사장부터 bfs 를 수행한다.

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        while(!queue.isEmpty()){
            int senior = queue.removeFirst();

            for (int junior : organization.get(senior)) {
                score.put(junior, score.get(junior) + score.get(senior));
                queue.addLast(junior);
            }
        }

        for(int i=1; i<=n; i++){
            System.out.print(score.get(i) + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}

