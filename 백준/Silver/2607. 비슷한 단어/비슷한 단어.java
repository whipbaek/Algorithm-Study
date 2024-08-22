import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer answer = 0;
        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> mainMap = new HashMap<>();
        String[] mainArray = br.readLine().split("");

        for (int i = 0; i < mainArray.length; i++) {
            String token = mainArray[i];
            mainMap.put(token, mainMap.getOrDefault(token, 0) + 1);
        }


        for (int i = 0; i < n-1; i++) {
            String[] targetArray = br.readLine().split("");

            int mainLen = mainArray.length;
            int targetLen = targetArray.length;
            Map<String, Integer> targetMap = new HashMap<>();


            for (int j = 0; j < targetArray.length; j++) {
                String token = targetArray[j];
                targetMap.put(token, targetMap.getOrDefault(token, 0) + 1);
            }

            int count = 0;
            for (Map.Entry<String, Integer> stringIntegerEntry : targetMap.entrySet()) {
                String key = stringIntegerEntry.getKey();
                Integer num = stringIntegerEntry.getValue();

                // 일치하는 수를 카운트 해야한다.
                if(mainMap.containsKey(key)){

                    if (num != mainMap.get(key)) {
                        if(num > mainMap.get(key)) count += mainMap.get(key);
                        if(num < mainMap.get(key)) count += num;
                    }

                    if(num == mainMap.get(key)) count += num;
                }
            }

            // 짧은 경우에는 모든 값이 들어맞아야 한다.
            if(mainLen == targetLen + 1 && count == targetLen) answer++;

            // 긴 경우에는 하나만 달라야 한다.
            if(mainLen + 1 == targetLen && count == targetLen - 1) answer++;

            // 같은 경우에는 하나를 빼거나, 같은 경우를 포함한다.
            if(mainLen == targetLen && (count == targetLen - 1 || count == targetLen)) answer++;

        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

//    public void inputProcess() throws Exception {}

}
