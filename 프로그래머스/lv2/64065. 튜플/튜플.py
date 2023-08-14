def solution(s):    
    strs = []
    i = 1
    
    while True:
        
        if s[i] == '}' : break
        if s[i] == ',' :
            i+=1
            continue
        
        j = 0
        temp = ""
        while True:
            if s[i + j] == '}': 
                j+=1
                break
            
            if s[i + j] == '{' : 
                j+=1
                continue
                
            temp += s[i+j]
            j+=1
        strs.append(temp)
        i += j
    
    strs.sort(key=len)
    
    answerSet = set()
    answer = []
    for s in strs:
        vals = s.split(',')
        for v in vals:
            if int(v) not in answerSet:
                answerSet.add(int(v))
                answer.append(int(v))
                    
    return answer