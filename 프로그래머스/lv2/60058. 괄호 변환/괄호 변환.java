import java.util.*;

class Solution {

    // 균형 잡힌 문자열 반환
    public String getBalanced(String s){
        int left = 0;
        int right = 0;
        int idx = 0;
        for(int i=0; i<s.length(); i++, idx++){
            if(left == right && left != 0) break;
            if(s.charAt(i) == '(') left++;
            else right++;
        }
        return s.substring(0, idx);
    }

    // 올바른지 검사
    public Boolean valid(String s){
        Stack<Character> st = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(st.empty()){
                st.push(ch);
            } else{
                if(st.peek() == '(' && ch == ')'){
                    st.pop();
                } else{
                    st.push(ch);
                }
            }
        }
        return st.empty();
    }

    public String makeU(String s){

        // 1. 빈 문자열이면
        if(s.equals("")){
            return s;
        }

        // 2. u, v로 변환
        String u = this.getBalanced(s);
        String v = "";
        if(u.length() < s.length()){
            v = s.substring(u.length());
        }
        // 3. u가 올바른지 확인
        if(this.valid(u)) {
            return u + makeU(v);
        }

        // 4. 올바르지 않다면 로직 수행
        StringBuilder temp = new StringBuilder("(");
        temp.append(makeU(v));
        temp.append(")");
        for(int i=1; i<u.length()-1; i++){
            if(u.charAt(i) == '('){
                temp.append(")");
            } else{
                temp.append("(");
            }
        }
        return temp.toString();
    }

    public String solution(String p) {
        String answer = this.makeU(p);
        System.out.println(answer);
        return answer;
    }
}