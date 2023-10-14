def solution(A, B):
    answer = 0
    l = len(A)
    A.sort()
    B.sort()
    idx = 0
    
    ptrA = 0
    ptrB = 0
    
    while ptrA < l and ptrB < l:
        if A[ptrA] < B[ptrB]:
            answer +=1
            ptrA += 1
            ptrB += 1
        else:
            ptrB += 1
            
                    
    return answer