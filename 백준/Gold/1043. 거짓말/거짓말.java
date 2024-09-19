import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {
        int answer = 0;
        // 파티에 참가하되, 그 내부에 진실을 아는 사람이 있는지를 판단해야한다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n : 사람의 수, m : 파티의 수
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 진실을 아는 사람은 Set에 저장한다.
        st = new StringTokenizer(br.readLine());
        st.nextToken();

        Set<Integer> truth = new HashSet<>();
        while (st.hasMoreTokens()) {
            truth.add(Integer.parseInt(st.nextToken()));
        }

        // 파티를 List로 입력 받는다.
        List<ArrayList<Integer>> partys = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            ArrayList<Integer> party = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            while (st.hasMoreTokens()) {
                party.add(Integer.parseInt(st.nextToken()));
            }
            partys.add(party);
        }


        // 파티 구성원을 그래프 형태로 세팅한다.
        Map<Integer, HashSet<Integer>> relationship = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            relationship.put(i, new HashSet<>());
        }

        // O(n) = 250,000
        for (ArrayList<Integer> party : partys) {
            int len = party.size();
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    relationship.get(party.get(i)).add(party.get(j));
                    relationship.get(party.get(j)).add(party.get(i));
                }
            }
        }


        // 파티 구성원을 모두 순회하면서 BFS 를 수행하다가 진실을 아는 사람을 만날 경우에는
        for (ArrayList<Integer> party : partys) {

            boolean possible = true;

            // 배열을 돌면서 모두 queue에 넣어주고, 방문 처리를 해준다.
            Deque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[n + 1];

            for (Integer i : party) {
                queue.addLast(i);
                visited[i] = true;
            }

            while (!queue.isEmpty()) {
                int target = queue.removeFirst();

                // 만약 진실을 아는 사람이 포함되어 있다면
                if(truth.contains(target)){
                    possible = false;
                    break;
                }

                // target과 연관되어 있는 사람들
                HashSet<Integer> knownPeople = relationship.get(target);

                // 해당 사람이 방문되지 않은 사람이라면, bfs를 수행한다.
                for (Integer knownPerson : knownPeople) {
                    if(!visited[knownPerson]){
                        queue.addLast(knownPerson);
                        visited[knownPerson] = true;
                    }
                }

            }

            if(possible) answer++;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
