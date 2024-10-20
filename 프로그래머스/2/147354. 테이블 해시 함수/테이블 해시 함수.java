import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
        row_begin--;
        row_end--;
        
        // Sort
        Arrays.sort(data, (o1, o2) -> {
            if(o1[col-1] == o2[col-1]){
                return Integer.compare(o2[0], o1[0]);
            }
            return Integer.compare(o1[col-1], o2[col-1]);
        });
        
        List<Integer> list = new ArrayList<>();
        
        for(int i = row_begin; i<= row_end; i++){
            int tempSum = 0;
            for(int j=0; j<data[i].length; j++){
                tempSum += (data[i][j]%(i+1));
            }
            
            list.add(tempSum);
        }
        
        int answer = list.get(0);
        for(int i=1; i<list.size(); i++){
            answer = answer ^ list.get(i);
        }   
        
        return answer;
    }
}