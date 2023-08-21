import java.util.*;

class Solution {
    public int[] solution(String[] gems) {

        Map<String,Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(gems));

        int fptr = 0;
        int rptr = 0;
        int dis = 9999999;
        int start = 0;
        int end = 0;

        while (fptr < gems.length && rptr < gems.length) {
            if (map.containsKey(gems[rptr])) {
                Integer val = map.get(gems[rptr]) + 1;
                map.put(gems[rptr], val);

            } else {
                map.put(gems[rptr], 1);
            }
            rptr++;
            
            while (map.size() == set.size()) {
                if (dis > rptr - fptr) {
                    dis = rptr - fptr;
                    start = fptr;
                    end = rptr;
                }
                if (map.get(gems[fptr]) > 1) {
                    map.put(gems[fptr], map.get(gems[fptr]) - 1);
                } else {
                    map.remove(gems[fptr]);
                }
                fptr++;
            }
        }
        return new int[]{start+1, end};
    }

}