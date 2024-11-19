import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        int nn = Integer.parseInt(st.nextToken()); // 최대수
        int kn = Integer.parseInt(st.nextToken()); // 엘레베이터 자리수
        int pn = Integer.parseInt(st.nextToken()); // 반전 가능 개수
        int xn = Integer.parseInt(st.nextToken()); // 현재 엘레베이터 위치

        Map<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

        int[][] numbers = new int[10][7];
        numbers[0] = new int[]{0, 0, 0, 1, 0, 0, 0};
        numbers[1] = new int[]{1, 1, 0, 1, 1, 0, 1};
        numbers[2] = new int[]{0, 1, 0, 0, 0, 1, 0};
        numbers[3] = new int[]{0, 1, 0, 0, 1, 0, 0};
        numbers[4] = new int[]{1, 0, 0, 0, 1, 0, 1};
        numbers[5] = new int[]{0, 0, 1, 0, 1, 0, 0};
        numbers[6] = new int[]{0, 0, 1, 0, 0, 0, 0};
        numbers[7] = new int[]{0, 1, 0, 1, 1, 0, 1};
        numbers[8] = new int[]{0, 0, 0, 0, 0, 0, 0};
        numbers[9] = new int[]{0, 0, 0, 0, 1, 0, 0};

        for (int i = 0; i < 10; i++) {
            map.put(i, new HashMap<>());
        }

        // 각 숫자에서 다른 숫자로 갈 때 필요한 반전의 개수를 구하자.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int count = 0;
                for (int k = 0; k < 7; k++) {
                    if(numbers[i][k] != numbers[j][k]){
                        count++;
                    }
                }
                // i 에서 j로 갈 때 count 만큼의 반전이 필요하다.
                map.get(i).put(j, count);
            }
        }

        String base = Integer.toString(xn);
        int baseLen = base.length();
        for (int i = 0; i < kn - baseLen; i++) {
            base = "0" + base;
        }

        // 해당 숫자가 1부터 nn 까지 변경이 가능한지 확인한다.
        for (int i = 1; i <= nn; i++) {
            String target = Integer.toString(i);
            int targetLen = target.length();
            for(int j=0; j< kn - targetLen; j++){
                target = "0" + target; // 빈자리는 0으로 채운다.
            }

            // 각 자리에서 target 자리로 변경하는데 필요한 반전 개수의 합을 구한다.
            int count = 0;
            for (int j = 0; j < kn; j++) {
                count += map.get(base.charAt(j) - '0').get(target.charAt(j) - '0');
            }


            if(count <= pn) answer++;
        }

        System.out.println(answer-1);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}


