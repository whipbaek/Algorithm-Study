import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {


    public Integer solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        List<Integer> numbers = new ArrayList<>(Arrays.asList(br.readLine().split(" ")))
                .stream()
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        if(numbers.size() < 3) return answer;


        for (int i = 0; i < numbers.size(); i++) {
            int front = 0;
            int back = numbers.size()-1;

            while (true) {

                // 투 포인터를 설정한다.
                if(front == i) front++;
                if(back == i)  back--;

                if(front == back) break;

                int result = numbers.get(front) + numbers.get(back);
                int target = numbers.get(i);

                if(result == target){
                    answer++;
                    break;
                }

                if(result > target) back--;
                if(result < target) front++;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new Main().solution());
    }
}
