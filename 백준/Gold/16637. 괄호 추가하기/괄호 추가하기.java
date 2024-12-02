import java.util.*;
import java.io.*;

public class Main {

    public static int answer = Integer.MIN_VALUE;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String origin = br.readLine();
        recursive(origin, "", 0);
//        System.out.println();
        System.out.println(answer);
    }

    public void recursive(String origin, String res, int idx) {
        if (idx >= origin.length() - 1) {
            if (idx == origin.length() - 1) {
                calc(res + origin.substring(idx));
                return;
            }
            calc(res);
            return;
        }

        if (idx + 3 == origin.length()) {
            recursive(origin, res + "(" + origin.substring(idx, idx + 3) + ")", idx + 4);
        } else {
            recursive(origin, res + "(" + origin.substring(idx, idx + 3) + ")" + origin.charAt(idx + 3), idx + 4);
        }
        // 선택하지 않거나
        recursive(origin, res + origin.substring(idx, idx + 2), idx + 2);
    }

    public void calc(String expression){

//        System.out.println("expression = " + expression);

        StringBuilder middleExpression = new StringBuilder();
        // 괄호 안의 값을 먼저 계산한다.
        for (int i = 0; i < expression.length(); i++) {
            // 열리는 괄호라면 안에 값들을 계산해서 결과를
            if(expression.charAt(i) == '('){
                middleExpression.append(calcVal(expression.substring(i + 1, i+2), expression.substring(i + 2, i + 3), expression.substring(i + 3, i + 4)));
                i+=4;
                continue;
            }
            // 만약 연산자라면 그냥 추가해준다.
            middleExpression.append(expression.charAt(i));

        }

//        System.out.println("middleExpression = " + middleExpression);


        int idx = 0;
        int first = 0;
        if(middleExpression.charAt(idx) == '-'){
            int temp = getIdx(middleExpression.toString(), idx+1);
            first = Integer.parseInt(middleExpression.substring(idx+1, temp)) * -1;
            idx = temp;
        } else{
            int temp = getIdx(middleExpression.toString(), idx);
            first = Integer.parseInt(middleExpression.substring(idx, temp));
            idx = temp;
        }

        //operator, second 만 구해서 반복적으로 연산한다.
        for (int i = idx; i < middleExpression.length(); i++) {
            // 연산자
            String operator = middleExpression.substring(i, i+1);
            i+=1;

            int second = 0;
            if(middleExpression.charAt(i) == '-'){ // 음수일경우

                // 만약 operator 도 마이너스였다면, 플러스로 연산을 바꿔준다.
                if(operator.equals("-")){
                    operator = "+"; // + 로 바꿔준다.
                    int temp = getIdx(middleExpression.toString(), i+1);
                    second = Integer.parseInt(middleExpression.substring(i+1, temp));
                    i = temp-1;
                }
                else{
                    int temp = getIdx(middleExpression.toString(), i+1);
                    second = Integer.parseInt(middleExpression.substring(i+1, temp)) * -1;
                    i = temp-1;
                }
            } else{ // 음수가 아닐 경우
                int temp = getIdx(middleExpression.toString(), i);
                second = Integer.parseInt(middleExpression.substring(i, temp));
                i = temp-1;
            }
//            System.out.println("first = " + first + ", seoncd = " + second);
            first = Integer.parseInt(calcVal(Integer.toString(first), operator, Integer.toString(second)));
        }
//        System.out.println("first = " + first);
//        System.out.println();

        answer = Math.max(answer, first);
    }


    public int getIdx(String str, int idx){
        while (idx != str.length()) {
            if (Character.isDigit(str.charAt(idx))) {
                idx++;
                continue;
            }
            break;
        }
        return idx;
    }

    public String calcVal(String n1, String operator, String n2){
        if(operator.equals("+")) return Integer.toString(Integer.parseInt(n1) + Integer.parseInt(n2));
        if(operator.equals("-")) return Integer.toString(Integer.parseInt(n1) - Integer.parseInt(n2));
        if(operator.equals("*")) return Integer.toString(Integer.parseInt(n1) * Integer.parseInt(n2));
        return null;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
