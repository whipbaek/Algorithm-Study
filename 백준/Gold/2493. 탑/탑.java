import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public class TOP{
        public int number;
        public int height;

        public TOP(int number, int height) {
            this.number = number;
            this.height = height;
        }
    }


    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> numbers = new ArrayList<>(Arrays.asList(br.readLine().split(" ")))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int[] answer = new int[n];

        Stack<TOP> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            TOP currentTOP = new TOP(i+1, numbers.get(i));

            while(!st.isEmpty()){
                // 스택 상위 값이 나보다 크거나 같은 경우
                if(st.peek().height >= currentTOP.height) break;

                // 작은 경우는 빼준다.
                st.pop();
            }

            // 만약 비어있다면 더 큰 건물이 없다는 뜻.
            if(st.isEmpty()){
                answer[i] = 0;
                st.push(currentTOP);
                continue;
            }
            answer[i] = st.peek().number;
            st.push(currentTOP);

        }

        for(int i=0; i<n; i++){
            System.out.print(answer[i]+ " ");
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
