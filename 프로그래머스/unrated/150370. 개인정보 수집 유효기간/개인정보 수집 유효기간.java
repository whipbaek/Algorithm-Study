import java.util.*;

class Solution {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
       
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String[] res = today.split("\\.");
        Integer t_year = Integer.parseInt(res[0]);
        Integer t_month = Integer.parseInt(res[1]);
        Integer t_day = Integer.parseInt(res[2]);
        
        for(String term : terms){
            map.put(term.split(" ")[0], Integer.parseInt(term.split(" ")[1]));
        }
        
        Integer cnt = 0;
        
        for(String privacy : privacies){
            cnt++;
            
            String temp = privacy.split(" ")[0];
            String type = privacy.split(" ")[1];
            
            Integer year = Integer.parseInt(temp.split("\\.")[0]);
            Integer month = Integer.parseInt(temp.split("\\.")[1]);
            Integer day = Integer.parseInt(temp.split("\\.")[2]);
            
            month += map.get(type);
            
            while(month > 12){
                year += 1;
                month -=12;
            }
            
            // 하루 깎아줘야함.
            if(day == 1){
                if(month == 1){
                    year -= 1;
                    month = 12;
                    day = 28;
                } else{
                    month -= 1;
                    day = 28;
                }
            } else{
                day -= 1;
            }
            
            // System.out.println(year + ", " + month + ", " + day);
                        
            if(t_year > year){
                answer.add(cnt);
            } else if(t_year < year){ 
                continue;
            } else{                 
                if(t_month > month){
                    answer.add(cnt);
                } else if(t_month < month){
                    continue;
                } else{                  
                    if(t_day > day){
                         answer.add(cnt);
                    } 
                }
            }
        }
    
        return answer;
    }
}