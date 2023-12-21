import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        int[] answer = new int[len];
        Stack<Integer> st = new Stack<>();
        
        for(int i=0; i<len; i++){
            int newVal = numbers[i];
            // 비어있거나, 최상위 값이 새로운 값보다 클 때
            if(st.empty() || numbers[st.peek()] >= newVal){
                st.push(i);
                continue;
            }
            
            // 새로운 값이 더 큰 경우
            while(!st.empty() && numbers[st.peek()] < newVal){
                answer[st.pop()] = newVal;
            }
            st.push(i);
        }
        
        while(!st.empty()){
            answer[st.pop()] = -1;
        }
        
        return answer;
    }
}