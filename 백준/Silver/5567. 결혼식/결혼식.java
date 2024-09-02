import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Set<Integer> answer = new HashSet<>();
        Map<Integer, HashSet<Integer>> map = new HashMap<>();

        for(int i=0; i<n; i++){
            map.put(i+1, new HashSet<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            HashSet<Integer> firstSet = map.get(first);
            firstSet.add(second);
            map.put(first, firstSet);

            HashSet<Integer> secondSet = map.get(second);
            secondSet.add(first);
            map.put(second, secondSet);
        }

        ArrayList<Integer> friends = new ArrayList<>();
        HashSet<Integer> firstFriend = map.get(1);

        for (Integer i : firstFriend) {
            friends.add(i);
            answer.add(i);
        }

        for (Integer friend : friends) {
            answer.addAll(map.get(friend));
        }

        if(answer.contains(1)){
            System.out.println(answer.size()-1);
            return;
        }

        System.out.println(answer.size());

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}
