import java.util.*;

class Solution {
    
    public int getIdx(int val, int limit){
        if(val >= limit){
            return val-limit;
        }
        return val;
    }
    
    public int solution(int[] elements) {
        Set<Integer> s = new HashSet<>();

        int len = elements.length;
        
        List<Integer> origin = new ArrayList<>();
        for(int i=0; i<len; i++){
            s.add(elements[i]);
            origin.add(elements[i]);
        }
        
        for(int i=1; i<len; i++){
            List<Integer> newest = new ArrayList<>();
            for(int j=0; j<len; j++){
                int idx = getIdx(i+j, len);
                int value = origin.get(j) + elements[idx];
                s.add(value);
                newest.add(value);
            }
            origin = new ArrayList<>(newest);
        }
        return s.size();
    }
}