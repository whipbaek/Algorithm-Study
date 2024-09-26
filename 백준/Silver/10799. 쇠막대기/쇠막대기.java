import java.util.*;
import java.io.*;

public class Main {


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> bar = new ArrayList<>(Arrays.asList(br.readLine().split("")));
        Stack<String> st = new Stack<>();
        int answer = 0;


        for (int i=0; i<bar.size(); i++) {
            String val = bar.get(i);

            if(st.isEmpty() || val.equals("(")){
                st.push(val);
                continue;
            }

            // 앞에 값도 닫는 값이면 1만 더한다.
            if(bar.get(i-1).equals(")")){
                answer++;
                st.pop();
                continue;
            }

            st.pop();
            answer += st.size();
        }

        System.out.println(answer);


    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
