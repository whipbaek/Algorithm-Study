import java.util.*;
import java.io.*;

public class Main {

    public static List<ArrayList<Integer>> sudoku = new ArrayList<>();
    public static int zeroCnt = 0;
    public static boolean possible = false;

    public void dfs(){

        if(possible) return;

        // 결과 출력
        if(zeroCnt == 0){
            for (int i = 0; i < 9; i++) {
                for(int j=0; j<9; j++){
                    System.out.print(sudoku.get(i).get(j));
                }
                System.out.println();
            }
            possible = true;
            return;
        }

        // 수평 검사, 수직 검사, area 검사.
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 0이 아니면 continue
                if(sudoku.get(i).get(j) != 0) continue;

                // 해당하는 index 에 들어갈 수 있는 수 확인한다.
                for(int k=1; k<=9; k++){

                    // 안되는 값이 있는지 확인한다.
                    if(!horizontalCheck(i, k)) continue;
                    if(!verticalCheck(j, k)) continue;
                    if(!areaCheck(i, j, k)) continue;

                    // 해당 값(k) 은 되므로 세팅해준다.
                    sudoku.get(i).set(j, k);
                    zeroCnt--;
                    dfs();
                    sudoku.get(i).set(j, 0); // dfs 를 나오면, 0으로 만들어준다.
                    zeroCnt++;
                }
                return;
            }
        }
    }

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Initialize
        for (int i = 0; i < 9; i++) {
            String[] line = br.readLine().split("");
            ArrayList<Integer> temp = new ArrayList<>();
            for (String val : line) {
                temp.add(Integer.parseInt(val));
            }
            sudoku.add(temp);
        }

        // 채워야할 개수
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudoku.get(i).get(j) == 0) zeroCnt++;
            }
        }

        // dfs 수행
        dfs();
    }


    // 수평 검사
    public boolean horizontalCheck(int row, int val){

        // 현재줄에 있는 값들을 검사한다.
        for(int i=0; i<9; i++){
            int target = sudoku.get(row).get(i);
            if(target == val) return false;
        }

        return true;
    }

    public boolean verticalCheck(int col, int val){

        for(int i=0; i<9; i++){
            int target = sudoku.get(i).get(col);
            if(target == val) return false;
        }

        return true;
    }

    public boolean areaCheck(int x, int y, int val){

        if(x<3) x= 0;
        else if (x<6) x = 3;
        else if (x<9) x = 6;

        if(y<3) y= 0;
        else if (y<6) y = 3;
        else if (y<9) y = 6;

        int xt = x+3;
        int yt = y+3;

        for(int i=x; i < xt; i++){
            for(int j=y; j<yt; j++){
                int target = sudoku.get(i).get(j);
                if(target == val) return false;
            }
        }

        return true;
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}


