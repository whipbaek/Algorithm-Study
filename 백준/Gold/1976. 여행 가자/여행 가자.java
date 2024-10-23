import java.util.*;
import java.io.*;

public class Main {

    public static int[] parent;
    public static int cityCount;
    public static int visitCount;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cityCount = Integer.parseInt(br.readLine()); // 총 도시 개수
        visitCount = Integer.parseInt(br.readLine()); // 방문 도시 개수


        // city 연결정보
        parent = new int[cityCount];
        for (int i = 0; i < cityCount; i++) {
            parent[i] = i;
        }

        for (int currentCity = 0; currentCity < cityCount; currentCity++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int targetCity = 0; targetCity < cityCount; targetCity++) {
                if (Integer.parseInt(st.nextToken()) == 1) { // 만약 연결되어 있다면,

                    int currentParent = find(currentCity);
                    int targetParent = find(targetCity);

                    // 두 도시가 연결되어 있는지 확인한다.
                    if (currentParent != targetParent) {
                        if (currentParent <= targetParent) {
                            parent[targetParent] = currentParent;
                        } else {
                            parent[currentParent] = targetParent;
                        }
                    }
                }
            }
        }

        // 자식들 초기화
        for (int i = 0; i < cityCount; i++) {
            find(i);
        }

        String[] cities = br.readLine().split(" ");

        for (int i = 1; i < visitCount; i++) {
            int currentCity = Integer.parseInt(cities[i - 1]) - 1;
            int nextCity = Integer.parseInt(cities[i]) - 1;

            if (parent[currentCity] != parent[nextCity]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

    }

    public int find(int x) {
        // 최상위 부모를 찾으면 리턴한다.
        if (parent[x] == x) {
            return x;
        }

        // 최상위 부모를 최종적으로 찾으면 대입해준다.
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws Exception {

        new Main().solution();
    }
}
