

def transform(time_str):
    h, m = time_str.split(':')
    return int(h)*60 + int(m)

def restore(time_int):
    h = str(time_int//60)
    m = str(time_int%60)
    
    if len(h) == 1: h = '0' + h
    if len(m) == 1: m = '0' + m

    return h+':'+m
    
def solution(n, t, m, timetable):
    answer = ''
    
    busList = []
    # 9시부터 t분 간격으로 n개의 셔틀이 도착하며, m명을 태울 수 있다.
    for i in range(n):
        val = transform("09:00") + (t*i)
        if val <= transform("23:59"):
            busList.append(val)
    
    transCrew = []
    for crew in timetable: transCrew.append(transform(crew))
    transCrew.sort()
    busList.sort()

    crewIdx = 0
    # 버스를 하나씩 돌면서 사람을 채운다.
    for i in range(len(busList)):
        temp = []
        cnt = 0
        while True:
            if crewIdx >= len(transCrew): break # 사람이 모두 탄 경우
            if cnt == m: break #버스에 정원이 모두 찼다면
            
            # 해당 버스보다 빨리 왔다면 태운다.
            if transCrew[crewIdx] <= busList[i]:
                temp.append(transCrew[crewIdx])
                cnt +=1
                crewIdx +=1
                
            # 해당 버스보다 늦게 왔다면 다음 버스로 넘어간다.
            else:
                break
            
        # 이게 마지막 버스였다면
        if i == len(busList)-1:
            # 그 버스가 만차라면, 그 버스를 마지막에 탄 사람 -1분에 오면 된다.
            if len(temp) == m: 
                answer = restore(temp[-1]-1)
            # 만약 한자리라도 비어있다면 버스가 도착하는 시간에 오면 된다.
            else: 
                answer = restore(busList[-1])
    return answer