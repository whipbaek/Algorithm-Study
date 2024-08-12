import java.util.*;
import java.io.*;

/**
 * [문제] (https://www.acmicpc.net/problem/14719)
 */

public class Main {

    public static Integer h;
    public static Integer w;
    public static Integer maxXPos = -1;
    public static Integer maxBlockValue = -1;
    public static Integer totalArea = 0;
    public static List<Integer> blocks = new ArrayList<>();

    public void solution() throws Exception {
        inputProcess();

        int[][] world = new int[h][w];

        for (int i = 0; i < w; i++) {

            if(maxBlockValue < blocks.get(i)){
                maxBlockValue = blocks.get(i);
                maxXPos = i;
            }

            for(int j= h-1; h-1 - blocks.get(i) < j; j--){
                world[j][i] = 1;
            }
        }

        for (int i = h-1; i >= 0; i--) {

            // Left Side
            int spaceCount = 0;
            for (int j = maxXPos; j >= 0; j--) {
                if(world[i][j] == 1){
                    totalArea += spaceCount;
                    spaceCount = 0;
                    continue;
                }
                spaceCount++;
            }

            spaceCount = 0;

            // Right Side
            for (int j = maxXPos; j < w; j++) {
                if(world[i][j] == 1){
                    totalArea += spaceCount;
                    spaceCount = 0;
                    continue;
                }
                spaceCount++;
            }
        }
        System.out.println(totalArea);
    }

    public void inputProcess() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st1.nextToken());
        w = Integer.parseInt(st1.nextToken());

        while (st2.hasMoreTokens()) {
            blocks.add(Integer.parseInt(st2.nextToken()));
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
