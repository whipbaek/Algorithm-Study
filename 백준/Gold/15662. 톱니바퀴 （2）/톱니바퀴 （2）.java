import java.util.*;
import java.io.*;

public class Main {

    public static  List<LinkedList<String>> gears = new ArrayList<>();

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int answer = 0;
        // 톱니는 Linked List 형태로 관리한다.

        for (int i = 0; i < t; i++) {
            gears.add(new LinkedList<>(Arrays.asList(br.readLine().split(""))));
        }

        int cycleCount = Integer.parseInt(br.readLine());

        // cycleCount 만큼 수행한다.
        for (int i = 0; i < cycleCount; i++) {
            Map<Integer, Integer> map = new HashMap<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int targetGear = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            int frontPointer = targetGear;
            int frontDirection = direction;
            int backPointer = targetGear;
            int backDirection = direction;

            map.put(targetGear, direction);

            // 내 앞쪽의 바퀴가 돌아가야하는지를 검사한다.
            while(frontPointer > 0){

                String currentValue = gears.get(frontPointer).get(6);
                String frontValue = gears.get(frontPointer - 1).get(2);

                // 만약 같다면, 이제 회전시킬일이 없음.
                if(currentValue.equals(frontValue)) {
                    break;
                }

                // 다르다면 변경시켜준다.
                frontDirection = changeDirection(frontDirection);

                // 해당 톱니가 돌아가야할 정보를 저장한다.
                map.put(frontPointer - 1, frontDirection);
                frontPointer--;
            }

            // 내 뒤쪽의 바퀴가 돌아가야하는지를 검사한다.
            while(backPointer < t-1){

                String currentValue = gears.get(backPointer).get(2);
                String frontValue = gears.get(backPointer + 1).get(6);

                // 만약 같다면, 이제 회전시킬일이 없음.
                if(currentValue.equals(frontValue)) {
                    break;
                }

                // 다르다면 변경시켜준다.
                backDirection = changeDirection(backDirection);

                // 해당 톱니가 돌아가야할 정보를 저장한다.
                map.put(backPointer + 1, backDirection);
                backPointer++;
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int gearNumber = entry.getKey();
                int gearDirection = entry.getValue();

                cycle(gearNumber, gearDirection);
            }
        }

        for (LinkedList<String> gear : gears) {
            if(gear.get(0).equals("1")) answer++;
        }

        System.out.println(answer);

    }


    public void cycle(int idx, int direction){
        if(direction == 1){ // 시계 방향
            LinkedList<String> strings = gears.get(idx);
            strings.addFirst(strings.removeLast());
        }

        if(direction == -1){ // 반 시계 방향
            LinkedList<String> strings = gears.get(idx);
            strings.addLast(strings.removeFirst());
        }
    }

    public int changeDirection(int direction) {
        if(direction == -1) return 1;
        return -1;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
