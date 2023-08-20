import java.util.*;

class Solution {
    public List<Integer> solution(String[] name, int[] yearning, String[][] photo) {
        List<Integer> answer = new ArrayList<>();
        
        HashMap<String, Integer>map = new HashMap<>();
        for(int i=0; i<name.length; i++){
            map.put(name[i], yearning[i]);
        }
        
        for(String[] ph : photo){
            Integer sum = 0;
            for(String p : ph){
                if(map.get(p) != null){
                    sum += map.get(p);
                }
            }
            answer.add(sum);
        }
        
        
        return answer;
    }
}