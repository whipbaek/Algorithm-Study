import java.util.*;

class Solution {
    
    public boolean [] visited = new boolean[8];
    public String [] friends = new String[]{"A", "C", "F", "J", "M", "N", "R", "T"};
    public int answer = 0;

    public void isPossible(String[] conditions, String res){
        for(String condition : conditions){
            boolean flag = false;
            int distance = Math.abs(res.indexOf(condition.charAt(0)) - res.indexOf(condition.charAt(2)));
            String operator = condition.substring(3,4);
            int num = Integer.parseInt(condition.substring(4));
            switch(operator){

                case "=" :
                    if(distance == num+1) flag = true;
                    break;

                case "<" :
                    if(distance <= num ) flag = true;
                    break;

                case ">" : 
                    if(distance >= num+2) flag = true;
                    break;
            }
            if(!flag) {
                return;
            }
        }
        answer += 1;
    }

    public void perm(int cnt, String res, String[] data){
        if(cnt == 8){
            isPossible(data, res);
            return;
        }

        for(int i=0; i<8; i++){
            if(visited[i]) continue;
            visited[i] = true;
            perm(cnt+1, res + friends[i], data);
            visited[i] = false;
        }
    }

    public int solution(int n, String[] data) {
        perm(0, "", data);
        return answer;
    }
}