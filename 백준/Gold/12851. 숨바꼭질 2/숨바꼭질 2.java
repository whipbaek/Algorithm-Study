import java.util.*;
import java.io.*;

public class Main {

    public class Move {
        public int count;
        public int location;

        public Move(int count, int location) {
            this.count = count;
            this.location = location;
        }
    }



    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int sister = Integer.parseInt(st.nextToken());

        // 최소 도달횟수를 알기위한 배열
        int[] visitCount = new int[100001];
        Arrays.fill(visitCount, Integer.MAX_VALUE);
        visitCount[subin] = 0;

        // n에서 m으로 이동.
        // bfs 를 수행해야하나?

        Deque<Move> deque = new ArrayDeque<>();
        deque.addLast(new Move(0, subin));

        int answerCount = Integer.MAX_VALUE;
        int answerWay = 0;

        while (!deque.isEmpty()) {
            Move move = deque.removeFirst();
            int currentCount = move.count;
            int currentLocation = move.location;

            // [case] answerCount 가 갱신된 이후로, currentCount 가 큰 값들은 의미가 없기에 탐색을 그만한다.
            if (currentCount > answerCount) {
                continue;
            }

            // [case] sister 가 있는 위치에 도달했다면.
            if (currentLocation == sister){
                if(currentCount == answerCount){ // count 가 같은경우에는 answerCount 르 늘려준다.
                    answerWay++;
                }

                if(currentCount < answerCount){ // 최소횟수로 왔다면 최소횟수를 답으로 산정하고, 해당 방법을 초기화한다.
                    answerCount = currentCount;
                    answerWay = 1;
                }
                continue;
            }

            // [case] count값이 갱신되지도 않았으며, sister 가 있는 위치에도 도달하지 못했다면 queue 에 넣어준다.

            // 전 칸으로 이동 ( 전칸이 범위안에 들고, 최소 횟수로 방문했을때만 방문 )
            if(currentLocation - 1 >= 0 && currentCount < visitCount[currentLocation - 1]){
                visitCount[currentLocation - 1] = currentCount + 1;
                deque.addLast(new Move(currentCount + 1, currentLocation - 1));
            }

            // 앞 칸으로 이동
            if(currentLocation + 1 <= 100000 && currentCount < visitCount[currentLocation + 1]){
                visitCount[currentLocation + 1] = currentCount + 1;
                deque.addLast(new Move(currentCount + 1, currentLocation + 1));
            }

            // 순간이동
            if(currentLocation * 2 <= 100000 && currentCount < visitCount[currentLocation * 2]){
                visitCount[currentLocation * 2] = currentCount + 1;
                deque.addLast(new Move(currentCount + 1, currentLocation * 2));
            }
        }

        System.out.println(answerCount);
        System.out.println(answerWay);
    }



    public static void main(String[] args) throws Exception {

        new Main().solution();
    }
}
