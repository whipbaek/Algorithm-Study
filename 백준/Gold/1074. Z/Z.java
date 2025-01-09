import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static long cnt = 0L;
    public static int r;
    public static int c;
    public static long answer = 0L;
    public void solution() throws Exception {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = (int)Math.pow(2.0, Double.parseDouble(st.nextToken()));
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        recursive(n/2, 0, 0);
        System.out.println(answer-1);
    }

    // 보드 크기, x좌표 y좌표
    public void recursive(int n, int x, int y){
        if(r< x || r >= x+n*2 || c < y || c >= y+n*2){
            cnt += (n* 2L) * (n* 2L);
            return;
        }

        if(n == 1){
            for (int i = x; i < x + 2; i++) {
                for (int j = y; j < y + 2; j++) {
                    cnt++;
                    if(i == r && j == c) {
                        answer = cnt;
                    }
                }
            }
            return;
        }

        recursive(n/2, x, y);
        recursive(n/2, x, y+n);
        recursive(n/2, x+n, y);
        recursive(n/2, x+n, y+n);
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}

