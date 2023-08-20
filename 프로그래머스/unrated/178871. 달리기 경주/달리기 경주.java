import java.util.*;

class Solution {
    public List<String> solution(String[] players, String[] callings) {
        List<String> answer = new ArrayList<>();
        
        HashMap<String, Integer> rank_p = new HashMap<>();
        HashMap<Integer, String> rank_c = new HashMap<>();
        int cnt = 1;
        for(String player : players){
            rank_p.put(player, cnt);
            rank_c.put(cnt, player);
            cnt++;
        }
        
        
        
        for(String caller : callings){
            String chaser = caller;
            Integer chaserRank = rank_p.get(chaser);
            
            Integer headerRank = chaserRank - 1;
            String header = rank_c.get(headerRank);
            
            rank_p.remove(chaser);
            rank_p.remove(header);
            
            rank_c.remove(chaserRank);
            rank_c.remove(headerRank);
            
            rank_c.put(headerRank, chaser);
            rank_p.put(chaser, headerRank);
            
            rank_c.put(chaserRank, header);
            rank_p.put(header, chaserRank);
            
        }
        
        for(int i=1; i<cnt; i++) 
            answer.add(rank_c.get(i));
        
        return answer;
    }
}