import java.util.*;

class Solution {

    public Set<Integer> set = new HashSet<>();

    public int solution(String numbers) {
        // 모든 경우의 수 생성
        recursive(numbers, "", new boolean[numbers.length()]);

        // 검증
        int answer = 0;
        for (Integer val : set) {
            if(isPrime(val)) {
                // System.out.println("prime Number : " + val);
                answer+=1;
             }
        }

        return answer;
    }

    public void recursive(String numbers, String result, boolean[] visited){

        for (int i = 0; i < numbers.length(); i++) {
            if(!visited[i]){
                visited[i] = true; 
                set.add(Integer.parseInt(result + numbers.charAt(i)));
                recursive(numbers, result + numbers.charAt(i), visited);
                visited[i] = false;
            }
        }
    }

    public boolean isPrime(int val){
        if(val < 2) return false;
        for(int i=2; i<= (int)Math.sqrt(val); i++){
            if(val % i == 0) {
                return false;
            }
        }
        return true; 
    }
}


