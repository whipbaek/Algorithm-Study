import java.sql.Array;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static class Friend implements Comparable<Friend>{
        int cost;
        int num;

        public Friend(int cost, int num) {
            this.cost = cost;
            this.num = num;
        }

        @Override
        public int compareTo(Friend o) {
            return this.cost - o.cost;
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int totalCost = 0;

        List<Integer> moneys = new ArrayList<>(Arrays.asList(br.readLine().split(" ")))
                .stream().map(val -> Integer.parseInt(val))
                .collect(Collectors.toList());


        //  pq 초기화 (값, 친구번호) -> 값이 작은순으로 나오게 됨.
        PriorityQueue<Friend> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(new Friend(moneys.get(i), i));
        }

        // 친구관계 만들어준다.
        Map<Integer, List<Integer>> relations = new HashMap<>();
        for (int i = 0; i < n; i++) {
            relations.put(i, new ArrayList<>());
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int friendOne = Integer.parseInt(st.nextToken())-1;
            int friendTwo = Integer.parseInt(st.nextToken())-1;

            relations.get(friendOne).add(friendTwo);
            relations.get(friendTwo).add(friendOne);
        }

        Set<Integer> set = new HashSet<>();

        while (!pq.isEmpty()) {

            Friend poll = pq.poll();
            int currentCost = poll.cost;
            int friendNumber = poll.num;

            // 해당 친구가 이미 친구라면 패스한다.
            if(set.contains(friendNumber)) continue;

            Deque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[n];
            set.add(friendNumber);
            queue.addLast(friendNumber);
            visited[friendNumber] = true;

            // bfs 를 수행하면서 친구가 한명이라도 있으면 cost 를 추가할 필요가 없다.
            boolean isFriend = false;

            while (!queue.isEmpty()) {
                int currentFriend = queue.removeFirst();

                List<Integer> list = relations.get(currentFriend);
                for (Integer friendsFriend : list) {
                    if(!visited[friendsFriend]){
                        if(set.contains(friendsFriend)) isFriend = true;
                        visited[friendsFriend] = true;
                        queue.addLast(friendsFriend);
                        set.add(friendsFriend);
                    }
                }
            }

            if(!isFriend) totalCost += currentCost;
        }

        if (totalCost > k) {
            System.out.println("Oh no");
            return;
        }

        System.out.println(totalCost);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
