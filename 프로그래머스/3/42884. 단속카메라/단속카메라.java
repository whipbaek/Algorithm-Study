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
        
        // 시작점을 기준으로 오름차순을 진행한다. 시작점이 같다면, 끝을 기준으로 오름차순한다.
        list.sort((o1, o2) -> {
            if(o1.start == o2.start){
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });
        
        // Debug Print
        // for(Car car : list){
        //     System.out.println(car.start + ", " + car.end);
        // }
        
        // 초기값
        Car reference = list.get(0);
        answer = 1;
        
        for(int i=1; i<list.size(); i++){
            Car target = list.get(i);
            
            // 설치한 카메라에 포함되는 경우 (target 의 start는 어차피 refernce 의 start 보다 큰 값이다.)
            if(target.start <= reference.end) {
                if(target.end <= reference.end){ // 만약 새로운 자동차의 끝이 reference 의 끝보다 작다면 갱신해준다.
                    reference.end = target.end;
                }
                continue;
            }
            
            answer += 1;
            reference = target;
        }
        
        
        return answer;
    }
}