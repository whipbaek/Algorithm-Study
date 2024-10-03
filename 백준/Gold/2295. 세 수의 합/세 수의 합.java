import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Long> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(Long.parseLong(br.readLine()));
        }

        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                set.add(numbers.get(i) + numbers.get(j));
            }
        }
        Long max = -1L;
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if(set.contains(numbers.get(i) - numbers.get(j))){
                    max = Math.max(max, numbers.get(i));
                }
            }
        }

        System.out.println(max);


    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
