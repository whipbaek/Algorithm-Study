import java.util.*;
import java.io.*;

public class Main {

    public static Map<Integer, PriorityQueue<Integer>> melodys = new HashMap<>();
    public static Long answer = 0L;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        for (int i = 0; i < 6; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            melodys.put(i + 1, pq);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int currentMelody = Integer.parseInt(st.nextToken());
            int currentFlat = Integer.parseInt(st.nextToken());

            while (!melodys.get(currentMelody).isEmpty() && melodys.get(currentMelody).peek() > currentFlat) {
                melodys.get(currentMelody).poll();
                answer++;
            }

            if(!melodys.get(currentMelody).isEmpty()){
                if (melodys.get(currentMelody).peek() == currentFlat){
                    continue;
                }
            }

            melodys.get(currentMelody).add(currentFlat);
            answer++;

        }

        System.out.println(answer);
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

//    public void inputProcess() throws Exception {}

}
