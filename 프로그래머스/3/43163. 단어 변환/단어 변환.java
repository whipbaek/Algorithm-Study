import java.util.*;

class Solution {
    
    public boolean[] visited;
    public int answer = Integer.MAX_VALUE;

    public boolean diff(String a, String b){
        int cnt = 0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) cnt+=1;
        }
        
        if(cnt == 1) return true;
        
        return false;
    }
    
    public void recursive(int cnt, String now, String target, String[] words){
        if(cnt >= words.length) return;
        
        if(now.equals(target)){
            answer = Math.min(cnt,answer);
            return;
        }
        
        for(int i=0; i<words.length; i++){
            // 방문하지 않은 문자열이면서, 문자열 차이가 1개라면.
            if(visited[i] == false && diff(now, words[i])){
                visited[i] = true;
                recursive(cnt+1, words[i], target, words);
                visited[i] = false;
            }
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        
        
        visited = new boolean[words.length];
        
        recursive(0, begin, target, words);
        
        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
}