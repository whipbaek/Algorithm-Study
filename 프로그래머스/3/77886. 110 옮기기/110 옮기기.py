def solution(ss):
    answer = []
    for s in ss:
        cnt = 0
        
        st = [] # stack
        for val in s:
            # 스택에서 110을 발견한다면
            if len(st) >= 2 and val == "0" and st[-1] == "1" and st[-2] == "1":
                cnt +=1
                st.pop()
                st.pop()
            else:
                st.append(val)
        
        # 뒤부터 돌면서 0이 존재하는지 검사한다.
        zero_idx = -1
        
        for i in range(len(st)):
            if st[i] == "0":
                zero_idx = i
        
        # 0이 없다면 -> 110을 앞에 붙여준다.
        if zero_idx == -1:
            answer.append("110" * cnt + "".join(st))
                          
        # 0이 있다면, 그 중간에 붙여준다.
        else:
            answer.append("".join(st[:zero_idx+1]) + "110" * cnt + "".join(st[zero_idx+1:]))
                
    return answer