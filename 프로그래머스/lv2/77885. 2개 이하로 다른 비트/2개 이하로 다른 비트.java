import java.util.*;

class Solution {
    
    public long func(long value){
        String res = "";
        while(value != 0){
            res += Long.toString(value%2);
            value/=2;
        }
        
        res += "0";
        int targetIdx = res.indexOf("0");
        res = res.substring(0, targetIdx-1) + "01" + res.substring(targetIdx+1);
        
        return this.changeBinary(res);        
    }
    
    public long changeBinary(String val){
        
        long weight = 1;
        long res = 0;
        for(int i=0; i<val.length(); i++){
            res += (val.charAt(i)-'0') * weight;
            weight*=2;
        }
        return res;
    }
    
    public List<Long> solution(long[] numbers) {
        List<Long> answer = new ArrayList<>();

        for(int i=0; i<numbers.length; i++){
            if(numbers[i]%2 == 0) answer.add(numbers[i]+1);            
            else answer.add(this.func(numbers[i]));
        }
        
        return answer;
    }
}