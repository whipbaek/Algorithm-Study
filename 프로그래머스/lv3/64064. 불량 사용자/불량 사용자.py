import copy
answer = set()

def dfs(target, i, tempArr, l):
    global answer
    
    if l == len(tempArr):
        tempArr.sort()
        answer.add("".join(tempArr))
        return
    
    if i >= len(target):
        return
    
    for val in target[i]:
        if val not in tempArr:
            tArr = copy.deepcopy(tempArr)
            tArr.append(val)
            dfs(target, i+1, copy.deepcopy(tArr), l)
            
    

def solution(user_id, banned_id):
    global answer
    
    # '*' 에 알파벳 소문자 또는 숫자가 들어갈 수 있다.
    
    target = []
    for banId in banned_id:
        candid = set()        
        for userId in user_id:
            # 매칭되는지 확인한다.
            if len(banId) != len(userId): continue
            
            possible = True
            for i in range(len(userId)):
                if banId[i] != '*' and (userId[i] != banId[i]):
                    possible = False
                    break
            
            if possible:
                candid.add(userId)
        target.append(candid)

    dfs(target, 0, [], len(banned_id))

    
    return len(answer)