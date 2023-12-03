import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        
        int[] answer = new int[prices.length];
        
        // stack에 idx
        Stack<Integer> st = new Stack<>();
        
        // stack에는 항상 커지거나 같은 순서 값들만 존재함.
        for(int i=0; i<prices.length; i++){
            int val = prices[i];
            // 현재 값이 peek보다 작다면 
            // stack이 비어있지 않다면
            while(!st.empty() && val < prices[st.peek()]){
                int idx = st.pop();
                answer[idx] = i - idx;
            }
            st.push(i);
        }
        
        while(!st.empty()){
            int idx = st.pop();
            answer[idx] = prices.length-1 - idx;
        }
        return answer;
    }
}