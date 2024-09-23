import java.util.*;
import java.io.*;

public class Main {
    public static int[] oppositeValue = {5, 3, 4, 1, 2, 0};
    public void solution() throws Exception {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Integer>> dices = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            List<Integer> temp = new ArrayList<>();
            for(int j=0; j<s.length; j++){
                temp.add(Integer.parseInt(s[j]));
            }
            dices.add(temp);
        }

        // 가장 아래 주사위는 원하는 대로 둘 수 있다.
        for (int i = 0; i < 6; i++) {

            int tempAnswer = getMaxSide(i, dices.get(0)); // 첫번째 주사위

            int floorIndex = i; // 초기 바닥면

            int ceilingIndex = oppositeValue[i]; // 초기 윗면
            int ceilingValue = dices.get(0).get(ceilingIndex);


            for (int j = 1; j < dices.size(); j++) { // 첫번째 주사위부터 끝 주사위까지

                floorIndex = getFloorIndex(ceilingValue, dices.get(j)); // 윗면을 바닥면으로 설정한다.

                ceilingIndex = oppositeValue[floorIndex]; // 윗면을 구한다.
                ceilingValue = dices.get(j).get(ceilingIndex);


                tempAnswer += getMaxSide(floorIndex, dices.get(j)); // floorNumber 밑으로 두었을 때 가장 큰 옆면을 구한다.
            }

            answer = Math.max(answer, tempAnswer);
        }

        System.out.println(answer);
    }

    public int getMaxSide(int floorNumber, List<Integer> dice) {
        if (floorNumber == 0 || floorNumber == 5) {
            return Collections.max(Arrays.asList(dice.get(1), dice.get(2), dice.get(3), dice.get(4)));
        }
        if (floorNumber == 1 || floorNumber == 3) {
            return Collections.max(Arrays.asList(dice.get(0), dice.get(2), dice.get(4), dice.get(5)));
        }
        if (floorNumber == 2 || floorNumber == 4) {
            return Collections.max(Arrays.asList(dice.get(0), dice.get(1), dice.get(3), dice.get(5)));
        }
        return -1;
    }

    public int getFloorIndex(int value, List<Integer> dice) {
        for (int i = 0; i < dice.size(); i++) {
            if(value == dice.get(i)) return i;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
