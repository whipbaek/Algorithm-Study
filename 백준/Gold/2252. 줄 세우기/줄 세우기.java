import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // inDegree[n] => n번 노드로 들어오는 횟수
        int[] inDegree = new int[n + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // input
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            graph.get(pre).add(after); // pre -> after
            inDegree[after]++; // after 의 inDegree 증가
        }

        // indegree 가 0 인 경우에 queue에 넣어준다.
        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> answer = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if(inDegree[i] == 0){ // 가장 앞서는 사람들 세팅
                queue.addLast(i);
            }
        }

        while (!queue.isEmpty()) {
            int student = queue.removeFirst(); // inDegree 가 0인 학생
            answer.add(student);

            for(int target : graph.get(student)){
                inDegree[target]--;
                if(inDegree[target] == 0){
                    queue.addLast(target);
                }
            }
        }

        for (Integer ans : answer) {
            System.out.print(ans + " ");
        }
        System.out.println();
        
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}

