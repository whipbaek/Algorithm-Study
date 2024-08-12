import java.util.*;
import java.io.*;

/**
 * [문제] (https://www.acmicpc.net/problem/2002)
 */

public class Main {

    public static Integer numOfCars;
    public static Map<String, Integer> exitHistory = new HashMap<>();
    public static List<String> seqOfCars = new ArrayList<>();
    public static Set<String> carSet = new HashSet<>();

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numOfCars = Integer.parseInt(br.readLine());

        for (int i = 0; i < numOfCars; i++) seqOfCars.add(br.readLine());
        for (int i = 0; i < numOfCars; i++) exitHistory.put(br.readLine(), i);

        for (int i = 0; i < numOfCars-1; i++) {
            String mainCar = seqOfCars.get(i);
            for(int j=i+1; j < numOfCars; j++){
                String subCar = seqOfCars.get(j);

                // subCar 가 나올때 나보다 앞에 있는지를 검사한다.
                if (exitHistory.get(mainCar) > exitHistory.get(subCar)) {
                    carSet.add(subCar);
                }
            }
        }

        System.out.println(carSet.size());
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
