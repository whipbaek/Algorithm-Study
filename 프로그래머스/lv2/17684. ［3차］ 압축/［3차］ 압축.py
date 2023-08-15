def initDict():
    mydict = {}
    dictIdx = 1
    
    for alpha in range(65, 91):
        mydict[chr(alpha)] = dictIdx
        dictIdx += 1
    
    return mydict, dictIdx

def solution(msg):
    answer = []
    myDict, dictIdx = initDict()
    
    idx = 0
    while True:
        
        if idx >= len(msg): break
        resultStr = ''

        # 현재 문자에서 사전에 등재된 가장 긴 문자열을 찾는다.
        for i in range(1, len(msg)+1-idx):
            value = msg[idx:idx+i]
            if value in myDict:
                resultStr = value    
        
        # 정답 배열에 숫자를 추가해준다.
        answer.append(myDict[resultStr])
        
        if idx+len(resultStr)+1 <= len(msg)-1:
            newStr = msg[idx:idx+len(resultStr)+1]
            myDict[newStr] = dictIdx
            dictIdx += 1
        
        idx += len(resultStr)
        
    return answer