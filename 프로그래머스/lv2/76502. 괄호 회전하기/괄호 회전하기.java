import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Map<Character, Character> m = new HashMap<>();
        m.put('[', ']');
        m.put('{', '}');
        m.put('(', ')');
        
        int len = s.length();
        Deque<Character> dq = new ArrayDeque<>();
        for(int i=0; i<len; i++) dq.addLast(s.charAt(i));
        
        for(int i=0; i<len; i++){
            if(i!=0) dq.addLast(dq.removeFirst());
            Stack<Character> st = new Stack<>();
            Deque<Character> dq_t = new ArrayDeque<>(dq);
            
            for(int j=0; j<len; j++) {
                
                Character ch = dq_t.removeFirst();
                if(st.empty()){
                    st.push(ch);
                } else{
                    // 짝이 맞다면
                    if(m.get(st.peek()) == ch){
                        st.pop();
                    } else{
                        st.push(ch);
                    }
                }
            }
            if(st.empty()) answer++;
        }
        return answer;
    }
}