import java.util.*;
import java.io.*;

/**
 * [문제] (https://www.acmicpc.net/problem/13335)
 */

public class Main {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int time = 0;

        int n = Integer.parseInt(st1.nextToken());
        int bridgeLength = Integer.parseInt(st1.nextToken());
        int L = Integer.parseInt(st1.nextToken());
        List<Integer> weights = new ArrayList<>();

        while (st2.hasMoreTokens()) {
            weights.add(Integer.parseInt(st2.nextToken()));
        }


        // 0으로 다리를 채워준다.
        Deque<Integer> bridge = new ArrayDeque<>(bridgeLength);
        for(int i=0; i<bridgeLength; i++){
            bridge.offerLast(0);
        }

        int truckIdx = 0;
        int currentWeight = 0;

        while (truckIdx < n) {
            int truck = weights.get(truckIdx);
            int frontTruck = bridge.peekFirst();

            int finalWeight = currentWeight - frontTruck + truck;

            // 트럭이 더 이상 올라갈 수 없다면
            if(finalWeight > L){
                currentWeight -= bridge.removeFirst();
                bridge.addLast(0); // 무의미한 값을 넣어준다.
                time++;
                continue;
            }

            // 트럭이 올라갈 수 있는 경우
            currentWeight += truck;
            currentWeight -= bridge.removeFirst();
            bridge.addLast(truck);
            time++;
            truckIdx++;
        }

        while(!bridge.isEmpty()){
            bridge.removeFirst();
            time++;
        }

        sb.append(time);
        System.out.println(time);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}
