import java.util.*;

class Solution {
    public Map<Integer, Integer> map = new HashMap<>();

    public void setMap(int s, int e){
        for(int i=s; i<=e; i++){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
    }
    public int solution(String [][] times) {
        int answer = 0;
        for(int l=0; l<times.length; l++) {

            String start = times[l][0];
            String end = times[l][1];

            int start_h = Integer.parseInt(start.split(":")[0]);
            int start_m = Integer.parseInt(start.split(":")[1]);
            int end_h = Integer.parseInt(end.split(":")[0]);
            int end_m = Integer.parseInt(end.split(":")[1]);

            int startTime = start_h * 60 + start_m;
            int endTime = end_h * 60 + end_m + 9;

            setMap(startTime, endTime);
        }

        for(int i=0; i<60*23+59; i++){
            answer = Math.max(answer, map.getOrDefault(i, 0));
        }


        return answer;
    }

    public static void main(String[] args) {
        new Solution().solution(new String[][] {{"09:10", "10:10"}, {"10:20", "12:20"}});
    }
}