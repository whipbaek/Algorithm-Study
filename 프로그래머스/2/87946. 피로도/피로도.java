import java.util.*;

class Solution {
    
    public List<ArrayList<Integer>> ways = new ArrayList<>();
    
    /*
    @cnt: 내가 뽑은 값의 개수
    @ l : 뽑아야할 limit 
    @visited : 방문한 요소 체크
    @arr : 내가 뽑아둔 배열
    */
    public void permutation(int cnt, int l, boolean[] visited, ArrayList<Integer> arr){
        if(cnt >= l){
            ways.add(new ArrayList<Integer>(arr));
            return;
        }
        
        for(int i=0; i<l; i++){
            if(!visited[i]){
                arr.add(i);
                visited[i] = true;
                permutation(cnt+1, l, visited, arr);
                arr.remove(new Integer(i));
                visited[i] = false;
            }
        }
    }
                            
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        int l = dungeons.length;
        boolean visited[] = new boolean[l];

        permutation(0, l, visited, new ArrayList<Integer>());
        
        for(int i=0; i<ways.size(); i++){
            int have = k;
            int temp = 0;
            
            for(Integer target : ways.get(i)){
               if(have >= dungeons[target][0]){
                    have -= dungeons[target][1];
                    temp++;
                } 
            }
     
            answer = Math.max(answer, temp);
        }
        
        return answer;
    }
}