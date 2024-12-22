import java.util.*;

class Solution {
    
    static class Car {
        public int start;
        public int end;
        
        public Car(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    
    public int solution(int[][] routes) {
        int answer = 0;
        
        List<Car> list = new ArrayList<>();
        for(int i=0; i<routes.length; i++){
            list.add(new Car(routes[i][0], routes[i][1]));
        }
        
        // 끝을 기준으로 오름차순 한다.
        list.sort((o1, o2) -> o1.end - o2.end);
        
        // 초기값
        Car reference = list.get(0);
        answer = 1;
        
        for(int i=1; i<list.size(); i++){
            Car target = list.get(i);
            
            if(target.start <= reference.end) {
                continue;
            }
            answer += 1;
            reference = target;
        }
        
        return answer;
    }
}