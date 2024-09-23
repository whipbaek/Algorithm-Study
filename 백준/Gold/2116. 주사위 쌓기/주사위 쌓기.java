import java.util.*;
import java.io.*;

// 1번 주사위는 어떤식으로든 둘 수 있음.

// 이후 주사위들은 아래 주사위의 윗면을 아랫면과 맞추어야 한다.

/**
 * 1번 주사위를 6가지 방법으로 둘 수 있다.
 * 이후 주사위는 모두 그에 맞춰서 배치한다.
 * 주사위를 쌓아올릴때, 아랫면을 기준으로 맞추고, 4개의 옆면중 가장 큰 값을 구한다.
 * 윗면을 아랫면으로 갱신해준다음 주사위가 끝날때까지 반복한다.
 */


public class Main {

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
            int floorValue = dices.get(0).get(i);

            int ceilingIndex = getUpSideIndex(i); // 초기 윗면
            int ceilingValue = dices.get(0).get(ceilingIndex);

//            System.out.println("diceNumber : " + i);
//            System.out.println();
//            System.out.println("floorIndex : " + floorIndex);
//            System.out.println("ceilingIndex = " + ceilingIndex);

            for (int j = 1; j < dices.size(); j++) { // 첫번째 주사위부터 끝 주사위까지

                // floorNumber 는 ceilingValue 를 가지고 있는 현재 다이스의 인덱스이다.
                // ceilingIndex 는 floorNumber 의 반대값을 가지고 있어야한다.

                floorIndex = getFloorIndex(ceilingValue, dices.get(j)); // 윗면을 바닥면으로 설정한다.
                floorValue = dices.get(j).get(floorIndex);

                ceilingIndex = getUpSideIndex(floorIndex); // 윗면을 구한다.
                ceilingValue = dices.get(j).get(ceilingIndex);

//                System.out.println();
//                System.out.println("floorIndex : " + floorIndex);
//                System.out.println("ceilingIndex = " + ceilingIndex);

                tempAnswer += getMaxSide(floorIndex, dices.get(j)); // floorNumber 밑으로 두었을 때 가장 큰 옆면을 구한다.
            }
//            System.out.println();
//            System.out.println();

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

    public int getUpSideIndex(int floorNumber) {
        if(floorNumber == 0) return 5;
        if(floorNumber == 5) return 0;
        if(floorNumber == 1) return 3;
        if(floorNumber == 3) return 1;
        if(floorNumber == 2) return 4;
        if(floorNumber == 4) return 2;

        return -1;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
