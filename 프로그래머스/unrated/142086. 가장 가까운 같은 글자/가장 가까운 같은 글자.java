import java.util.*;

class Solution {
    public List<Integer> solution(String s) {
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i<s.length(); i++){
            if(i == 0) {
                ans.add(-1);
                continue;
            }

            char ch = s.charAt(i);

            int j = i-1;
            boolean flag = false;
            while(j>=0){
                if(s.charAt(j) == ch){
                    ans.add(i-j);
                    flag = true;
                    break;
                }
                j--;
            }

            if(!flag){
                ans.add(-1);
            }
        }
        return ans;
    }
}