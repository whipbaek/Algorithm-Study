import java.util.*;
import java.io.*;

/**
 * [문제] (https://www.acmicpc.net/problem/6198)
 */

public class Main {

    public static Long n;
    public static List<Long> heights = new ArrayList<>();
    public static Stack<Building> stack = new Stack<>();
    public static Long answer = 0L;

    public class Building {
        public Integer number;
        public Long height;

        public Building(Integer number, Long height) {
            this.number = number;
            this.height = height;
        }
    }

    public void solution() throws Exception {
        inputProcess();

        for (int i = 0; i < heights.size(); i++) {
            Building currentBuilding = new Building(i, heights.get(i));

            // 들어오는 빌딩이 더 높다면 낮은 값이 나올때 까지 또는 빌때까지 빼고 계산한다.
            while(!stack.empty() && stack.peek().height <= currentBuilding.height){
                Building recentBuilding = stack.pop();
                answer += (currentBuilding.number - recentBuilding.number - 1);
            }
            stack.push(currentBuilding);
        }

        while (!stack.empty()) {
            answer += (n - stack.pop().number - 1);
        }

        System.out.println(answer);
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    public void inputProcess() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Long.parseLong(br.readLine());

        for (int i = 0; i < n; i++) {
            heights.add(Long.parseLong(br.readLine()));
        }
    }

}
