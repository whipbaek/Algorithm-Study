import java.util.*;

class Solution {
    
    public Set<String> s = new HashSet<>();
    public String[] alphas = {"A", "E", "I", "O", "U"};
    public void recursive(int cnt, String res){
        if(cnt == 5){
            s.add(res);
            return;
        }

        for(String alpha : alphas){
            recursive(cnt+1, res + alpha);
            recursive(cnt+1, res);
        }
    }

    public int solution(String word) {
        int answer = 0;

        recursive(0, "");

        List<String> words = new ArrayList<>(s);

        Collections.sort(words);
        for(String w : words){
            if(w.equals(word)) break;
            answer++;
        }

        return answer;
    }
}