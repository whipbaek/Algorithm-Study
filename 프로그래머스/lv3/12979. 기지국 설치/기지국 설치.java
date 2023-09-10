import java.util.*;

class Solution {
    
    public int answer = 0;
    public void search(int start, int end, int w){

        if(start > end) return;
        int target = start + w;
        answer++;
        search(target+w+1, end, w);
    }

    public int solution(int n, int[] stations, int w) {

        search(1, stations[0]-w-1, w);
        for(int i=1; i<stations.length; i++){
            search(stations[i-1]+w+1, stations[i]-w-1, w);
        }
        search(stations[stations.length-1]+w+1, n, w);
        return answer;
    }
}