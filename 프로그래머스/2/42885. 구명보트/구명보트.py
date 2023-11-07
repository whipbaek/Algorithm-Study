def solution(people, limit):
    # 큰 사람부터 탑승
    people.sort(reverse=True)
    # 초기화
    answer = 0
    front = 0
    back = len(people)-1
    
    while front <= back:
        now = people[front]
        front +=1
        
        if front > back:
            answer +=1
            break
        
        if now + people[back] <= limit:
            back -=1
        
        answer +=1
    
    return answer
