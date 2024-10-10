import java.util.*;

class Solution {
    
    public class Point{
        public int x;
        public int y;
        
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(x, y);
        }
        
        @Override
        public boolean equals(Object o){
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int robotCount = routes.length;
        
        // Initialize Point Map
        Map<Integer, Point> pointMap = new HashMap<>();
        // key : time, point / value : count
        Map<Point, HashMap<Integer, Integer>> mapping = new HashMap<>();
        for(int i=0; i<101; i++){
            for(int j=0; j<101; j++){
                mapping.put(new Point(i, j), new HashMap<>());
            }
        }
        
        for(int i=0; i<points.length; i++){
            pointMap.put(i+1, new Point(points[i][0], points[i][1]));
        }
        
        // simulation
        for(int i=0; i<routes.length; i++){
            
            int ox = 0;
            int oy = 0;
            Integer second = 0;
                
            for(int j=0; j<routes[i].length; j++){
                Point targetPoint = pointMap.get(routes[i][j]);
                int tx = targetPoint.x;
                int ty = targetPoint.y; 
                
                // set starting point
                if(j==0) {
                    ox = tx;
                    oy = ty;
                    
                    HashMap<Integer, Integer> targetMap = 
                            mapping.get(new Point(ox, oy));
                        
                        targetMap.put(second,
                        targetMap.getOrDefault(second, 0) + 1);
                    continue;
                }
                
                System.out.println();
                
                while(true){
                    
                    //System.out.println("ox, oy : [" + second + "] : " + ox + ", " + oy);
                    
                    if(ox == tx && oy == ty) break;
                    
                    if(ox < tx){
                        ox++; 
                        second++;
                        
                        HashMap<Integer, Integer> targetMap = 
                            mapping.get(new Point(ox, oy));
                        
                        targetMap.put(second,
                        targetMap.getOrDefault(second, 0) + 1);
                        continue;
                    }
                    
                    if(ox > tx){
                        ox--;
                        second++;
                        
                        HashMap<Integer, Integer> targetMap = 
                            mapping.get(new Point(ox, oy));
                        
                        targetMap.put(second,
                        targetMap.getOrDefault(second, 0) + 1);
                        continue;
                    }
                    
                    if(oy < ty){
                        oy++;
                        second++;
                        HashMap<Integer, Integer> targetMap = 
                            mapping.get(new Point(ox, oy));
                        
                        targetMap.put(second,
                        targetMap.getOrDefault(second, 0) + 1);
                        
                        continue;
                    }
                    
                    if(oy > ty){
                        oy--;
                        second++;
                        // [x, y] => <Integer, Integer>
                        HashMap<Integer, Integer> targetMap = 
                            mapping.get(new Point(ox, oy));
                        
                        targetMap.put(second,
                        targetMap.getOrDefault(second, 0) + 1); 
                        
                        continue;
                    }
                }
            }
        }
        
        for(Point point : mapping.keySet()){
            for(Integer count : mapping.get(point).values()){
                if(count >= 2) answer++;
            }
        }
        
        return answer;
    }
}