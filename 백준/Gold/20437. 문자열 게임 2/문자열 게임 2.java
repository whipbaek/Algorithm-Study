import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t != 0){
            t--;
            String st = br.readLine();
            int k = Integer.parseInt(br.readLine());

            int maxLen = -1;
            int minLen = Integer.MAX_VALUE;

            for (int i = 97; i <= 123; i++) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int j = 0; j < st.length(); j++) {
                    char ch = st.charAt(j);
                    if(ch == i) list.add(j);
                }

                // k 개의 문자가 존재하지 않는경우
                if(list.size() < k) continue;

                // 투 포인터
                int front = 0;
                int back = k-1;

                while (true) {
                    int currentLen = list.get(back) - list.get(front) + 1;
                    maxLen = Math.max(maxLen, currentLen);
                    minLen = Math.min(minLen, currentLen);

                    // 범위 벗어나는 경우
                    if(back + 1 == list.size()) break;

                    front++;
                    back++;
                }
            }

            if(maxLen == -1){
                System.out.println(-1);
                continue;
            }

            System.out.println(minLen + " " + maxLen);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}


