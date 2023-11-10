class Solution
{
    public int solution(int n, int a, int b){
        // 현재 번호가 얻는 다음 번호를 예측하고, 그 값이 같아질때의 depth를 return한다.
        int depth = 0;
        while(true){
            if(a%2 == 1) a++;
            if(b%2 == 1) b++;
            
            a/=2;
            b/=2;
            depth++;
            if(a == b) break;
        }
        
        return depth;
    }
}