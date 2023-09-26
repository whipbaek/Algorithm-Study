def solution(n, left, right):
    answer = []
    sr = left//n
    er = right//n
    
    sc = (left%n) 
    ec = (right%n)
    
    ei = n*(er-sr) + ec
    
    for i in range(sr+1, er+2):
        j = i
        for k in range(1, n+1):
            if k>i:
                j += 1
                answer.append(j)
            else:
                answer.append(j)
    return answer[sc:ei+1]