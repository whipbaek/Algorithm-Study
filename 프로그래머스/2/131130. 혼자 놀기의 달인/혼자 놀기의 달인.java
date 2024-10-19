import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        int len = cards.length;
        
        // set Index type
        for(int i=0; i<len; i++){
            cards[i] = cards[i] - 1;
        }
        
        
        // i means StartingPoint ! 
        for(int i=0; i<len; i++){
            
            Set<Integer> openBox = new HashSet<>();
            int target = cards[i];
            openBox.add(i);
            
            // open the box until ~
            while(true){
                // if find opend box -> break
                if(openBox.contains(target)) break;
                openBox.add(target);
                target = cards[target];
            }
            
            //System.out.println("spoint : " + i);
            //System.out.println("openbox size : " + openBox.size());
            //System.out.println();
            
            // there's no B group (all Boxes open)
            if(openBox.size() == len) continue;
            
            // j means StartingPoint of B Group
            for(int j=0; j<len; j++){
                // if opend box already : it cant be starting point
                if(openBox.contains(j)) continue;
                
                // data migration from openBox
                Set<Integer> subOpenBox = new HashSet<>(openBox);
                int subTarget = cards[j];
                subOpenBox.add(j);
                
                while(true){
                    if(subOpenBox.contains(subTarget)) break;
                    subOpenBox.add(subTarget);
                    subTarget = cards[subTarget];
                }
                
                //System.out.println("ssPoint : " + j);
                //System.out.println("SubBox size : " + subOpenBox.size());
                //System.out.println("-----------");
                //System.out.println();
                
                
                answer = Math.max(
                    answer,
                    openBox.size() * (subOpenBox.size() - openBox.size())
                );
            }
        }
        
        return answer;
    }
}