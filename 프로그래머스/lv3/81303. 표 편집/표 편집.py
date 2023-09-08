def solution(n, k, cmd):
    cur = k
    table = { i:[i-1, i+1] for i in range(n) }
    answer = ['O'] * n
    
    table[0] = [None, 1]
    table[n-1] = [n-2, None]
    stack = []
    
    for c in cmd:
        
        # 선태된 행 삭제 후, 아래행 선택
        # 만약 다음 행이 None이라면 앞의 행 선택
        if c == 'C':
            answer[cur] = 'X'
            before, after = table[cur]
            stack.append([before, cur, after])
            
            # 마지막 행이라면
            if after == None: 
                cur = table[cur][0]
            else : 
                cur = table[cur][1]
            
            # Linked List 갱신 과정
            if before == None: # 첫번째 행이 삭제된 경우
                table[after][0] = None
            elif after == None: # 마지막 행이 삭제된 경우
                table[before][1] = None
            else: # 그 외의 경우
                table[before][1] = after
                table[after][0] = before
        
        elif c == 'Z':
            before, now, after = stack.pop();
            answer[now] = 'O'
            
            
            if before == None: #첫번째 행이 복구된 경우
                table[after][0] = now
            elif after == None: # 마지막 행이 복구된 경우
                table[before][1] = now
            else: # 그 외의 경우
                table[before][1] = now
                table[after][0] = now
        
        else:
            d, val = c.split(' ')
            val = int(val)
            
            # 해당 방향만큼 이동.
            if d == 'U':
                for _ in range(val):
                    cur = table[cur][0]
            
            if d == 'D':
                for _ in range(val):
                    cur = table[cur][1]
            
    
    return ''.join(answer)