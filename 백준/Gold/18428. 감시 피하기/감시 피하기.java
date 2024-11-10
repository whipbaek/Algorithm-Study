import java.util.*;
import java.io.*;


public class Main {

    public static List<ArrayList<String>> board = new ArrayList<>();
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};
    public static int n;
    public static boolean possible = false;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            board.add(new ArrayList<>(Arrays.asList(br.readLine().split(" "))));
        }

        setObstacle(0);
        if(possible){ // 감시를 피할 수 있는 경우
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }

    public void setObstacle(int count){

        if(count == 3){
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board.get(i).get(j).equals("X")){
                    board.get(i).set(j, "O");
                    setObstacle(count+1);
                    board.get(i).set(j, "X");
                }
            }
        }
    }

    public void bfs() {

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // 선생님이 아닐 경우
                if(!board.get(i).get(j).equals("T")) continue;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    while (true) {
                        // 범위 벗어나면 탐색 멈춤
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;

                        // 다른 선생님이나 구조물을 만나면 탐색 그만둔다.
                        if(board.get(nx).get(ny).equals("T") || board.get(nx).get(ny).equals("O")) break;

                        // 학생 발견하면 학생 수 카운트한다.
                        if(board.get(nx).get(ny).equals("S")) count++;

                        nx += dx[k];
                        ny += dy[k];
                    }
                }


            }
        }

        if(count == 0) {
            possible = true;
        }
    }



    public static void main(String[] args) throws Exception {

        new Main().solution();
    }
}