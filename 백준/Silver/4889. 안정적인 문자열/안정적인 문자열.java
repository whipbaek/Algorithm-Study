import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer printCount = 1;
        while (true) {
            Long operationCount = 0L;
            Stack<String> stack = new Stack<>();
            String inputString = br.readLine();

            if(inputString.indexOf('-') != -1) break;

            String[] strings = inputString.split("");

            for (String token : strings) {
                // stack 이 빈 경우나, 여는 괄호라면
                if(stack.isEmpty() || token.equals("{")){
                    if(token.equals("}")){
                        operationCount++;
                    }

                    stack.push("{");
                    continue;
                }

                // 닫는 괄호라면 -> 빼준다.
                stack.pop();
            }

            while (!stack.empty()) {
                operationCount++;
                stack.pop();
                stack.pop();
            }

            System.out.println(printCount + ". " + operationCount);
            printCount++;
        }

    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
