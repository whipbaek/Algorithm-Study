class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        for(int i=0; i<=t.length()-p.length(); i++){
            String temp = "";
            
            for(int j=i; j< i+p.length(); j++){
                temp += t.charAt(j);
            }
            if(Long.parseLong(temp) <= Long.parseLong(p)) answer +=1;                
        }
        
        return answer;
    }
}