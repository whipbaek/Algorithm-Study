import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        String[] nums = String.valueOf(storey).split("");
        boolean flag = false;
        for(int i=nums.length-1; i>=0; i--){
            
            int targetNumber = Integer.parseInt(nums[i]);
            
            // 아랫자리에서 올림이 있었더라면
            if(flag){
                if(targetNumber == 9) continue;
                else targetNumber +=1;
            }
            
            if(targetNumber < 5){
                answer += targetNumber;
                flag = false;
            }
            
            if(targetNumber > 5){
                answer += (10-targetNumber);
                flag = true;
            }
            
            if(targetNumber == 5){
                if(i == 0) answer += targetNumber;
                else{
                    if(Integer.parseInt(nums[i-1]) >= 5 ){
                        flag = true;
                    } else{
                        flag = false;
                    }
                    
                    answer += targetNumber;
                }
            }
            
        }
        
        if(flag) answer +=1;
        
        return answer;
    }
    
}