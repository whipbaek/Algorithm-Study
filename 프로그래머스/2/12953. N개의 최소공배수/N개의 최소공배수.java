import java.util.*;

// lcm(a,b,c)=lcm(lcm(a,b),c)
class Solution {
    
    // (a > b)
    public int lcm(int a, int b){
        
        int absVal = Math.abs(a) * Math.abs(b);
        
        if(b > a){
            int temp = a;
            a = b;
            b = temp;
        }
        
        while(b != 0){
            int r = a%b;
            a = b;
            b = r;
        }
        
        return absVal/a;
    }
    
    public int solution(int[] arr) {
        
        int first = arr[0];
        
        if(arr.length == 1){
            return arr[0];
        }
        
        for(int i=1; i<arr.length; i++){
            int second = arr[i];
            first = lcm(first, second);
        }
        
        return first;
    }
}