import java.util.*;
import java.util.stream.*;

class Solution {
    
    public int find_parent(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = find_parent(parent, parent[x]);
        }
        return parent[x];
    }
    
    public void union_parent(int[] parent, int a, int b){
        a = find_parent(parent, a);
        b = find_parent(parent, b);
        
        if(a<b){
            parent[b] = a;
        } else{
            parent[a] = b;
        }
    }
    
    public int solution(int n, int[][] computers) {
        
        int[] parent = IntStream.range(0, n).toArray();
        Set<Integer> s = new HashSet<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(computers[i][j] == 1){
                    if(find_parent(parent, i) != find_parent(parent, j)){
                        union_parent(parent, i, j);
                    }
                }        
            }
        }
        
        for(int i=0; i<n; i++) s.add(find_parent(parent, i));
        
        return s.size();
    }
}