import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Key, List => Key 건물을 짓기 위해 필요한 건물들
        int n = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> techTree = new HashMap<>();
        Map<Integer, Integer> buildTime = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>(); // 진입차수

        for (int i = 1; i <= n; i++) {
            techTree.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        // 건물들의 정보를 입력받는다.
        for (int i = 0; i < n; i++) {
            int target = i + 1;

            String[] numbers = br.readLine().split(" ");
            buildTime.put(target, Integer.parseInt(numbers[0])); // 해당 건물을 짓는데 걸리는 시간

            // 해당 건물을 짓기 위해 선행되어야 하는 건물들
            for (int j = 1; j < numbers.length - 1; j++) {
                int preBuilding = Integer.parseInt(numbers[j]);
                techTree.get(preBuilding).add(target);
                inDegree.put(target, inDegree.get(target) + 1); // 진입차수 증가.
            }
        }

        int[] answer = new int[n+1];
        for (int i = 1; i <= n; i++) {
            answer[i] = buildTime.get(i);
        }

        // 위상정렬 수행
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if(inDegree.get(i) == 0){ // 진입차수가 0인 값들을 넣어준다.
                queue.addLast(i);
            }
        }

        while (!queue.isEmpty()) {
            int target = queue.removeFirst();

            for (int val : techTree.get(target)) {
                inDegree.put(val, inDegree.get(val) - 1); // 진입차수 줄여준다.
                answer[val] = Math.max(answer[val], answer[target] + buildTime.get(val)); // 같은 회차인 경우에는 max 값으로 처리한다.
                if(inDegree.get(val) == 0){
                    queue.addLast(val);
                }
            }


        }

        for(int i=1; i<=n; i++){
            System.out.println(answer[i]);
        }

    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}

