import java.util.*;

class Solution {

    static class Node {
        public String val;
        public boolean visit;
        
        public Node(String val, boolean visit){
            this.val = val;
            this.visit = visit;
        }
    }
    
    static List<String> answer = new ArrayList<>();
    
    public void dfs(Map<String, List<Node>> map, String key, int now, int limit, List<String> ans){
        
        /*
            case 1. 모든 티켓을 다 사용했을 경우 -> 정답을 도출한다.
                    모든 티켓을 다 사용한지.. 어떻게 알지??
        */
        if(now >= limit){
            if(answer.size() == 0){
                for(String str : ans) answer.add(str);
            }
            return;
        }
        
        /*
            case 2. 다 사용하지 않았고, 티켓을 더 사용할 수 있다면?
                    티켓 사용 처리를 한 다음 진행한다.
        */                 
        
        if(!map.containsKey(key)) return;
        
        for(Node node : map.get(key)){
            if(!node.visit){
                node.visit = true; // 티켓 사용 처리
                List<String> temp = new ArrayList<>(ans);
                temp.add(node.val); // 정답에 추가
                
                dfs(map, node.val, now+1, limit, temp);
                
                node.visit = false;
            }
        }

        /*
            case 3. 현재 상황에서 더 이상 방문할 수 없으면서 다 방문하지 않았다면?
                    return 해서 이전으로 되돌아 간다.
        */
    }
    
    
    public List<String> solution(String[][] tickets) {
        
        // Initialize
        Map<String, List<Node>> map = new HashMap<>();
        
        for(int i=0; i<tickets.length; i++){
            String from = tickets[i][0];
            String to = tickets[i][1];
            
            if(!map.containsKey(from)){
                List<Node> list = new ArrayList<>();
                list.add(new Node(to, false));
                map.put(from, list);
            } else{
                map.get(from).add(new Node(to, false));
            }
        }
        
        // list 값들을 알파벳 오름차순으로 정렬
        for(String key : map.keySet()){
            map.get(key).sort((o1, o2) -> o1.val.compareTo(o2.val));
        }
        
        List<String> ans = new ArrayList<>();
        ans.add("ICN");
        
        // map, 시작점, 티켓 사용량, 티켓 최대, 정답 리스트
        dfs(map, "ICN", 0, tickets.length, ans);
        
        return answer;
    }
}