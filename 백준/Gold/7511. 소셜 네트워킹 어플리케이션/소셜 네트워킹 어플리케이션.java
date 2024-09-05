import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int scenarioCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < scenarioCase; i++) {


            int n = Integer.parseInt(br.readLine());

            // 친구 그룹
            int[] friendGroup = new int[n];
            for (int j = 0; j < n; j++) {
                friendGroup[j] = j;
            }

            int friendCount = Integer.parseInt(br.readLine());

            for (int j = 0; j < friendCount; j++) {
                // 만약 두 friend Group 이 다르다면 (find), union 을 수행한다.
                StringTokenizer st = new StringTokenizer(br.readLine());
                int friendOne = Integer.parseInt(st.nextToken());
                int friendTwo = Integer.parseInt(st.nextToken());

                int friendOneGroup = find(friendGroup, friendOne); // 첫번째 친구가 어느 그룹에 속해있는지
                int friendTwoGroup = find(friendGroup, friendTwo); // 두번째 친구가 어느 그룹에 속해있는지
                
                
                if(friendOneGroup != friendTwoGroup){
                    if(friendOneGroup < friendTwoGroup){
                        friendGroup[friendTwoGroup] = friendOneGroup;
                    } else{
                        friendGroup[friendOneGroup] = friendTwoGroup;
                    }
                }
            }

            int m = Integer.parseInt(br.readLine());
            System.out.println("Scenario " + (i+1) + ":");
            for (int j = 0; j < m; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int friendOne = Integer.parseInt(st.nextToken());
                int friendTwo = Integer.parseInt(st.nextToken());

                System.out.println(find(friendGroup, friendOne) == find(friendGroup, friendTwo) ? 1 : 0);
            }
            System.out.println();
        }
    }

    // target 의 부모를 찾기
    public int find(int[] friendGroup, int target){
        if(friendGroup[target] == target){
            return target;
        }

        return friendGroup[target] = find(friendGroup, friendGroup[target]);

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}
