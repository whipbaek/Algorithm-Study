from sys import stdin
from collections import deque
'''
특정 위치에, 몇 번 발판이 존재하는지 계속 트랙킹 해야한다.

위치상 n인 발판으로 로봇이 이동했다면, 위치상 n+1인 발판으로 옮겨준다.

location[n] = 발판 번호
durable[발판번호] = 남은 내구성, 로봇 여부

robot[i] = 발판 번호 => 순서대로 append


1. 벨트 회전
    -> locatio[n] 값들을 하나씩 이동시켜줌 (deque 활용)
    -> 만약 위치상 n에 로봇이 위치한다면, 다음칸으로 옮겨준다.
    if durable[location[n]][1] == True:
        durable[location[n]+1][1] == True (location[n] 이 2n일때는 1로 변경해줘야함.)
        내구도 -= 1
        durable[location[n]][1] = False

2. 가장 먼저 벨트에 올린 로봇들부터 한칸씩 이동
    -> 가장 먼저 벨트에 올린 로봇은 어떻게 판단? (배열 처음부터 옮긴다.)
    -> durable[robot[i]+1][0] != 0 => 내구도가 있다면
    -> durable[robot[i]+1][1] == False => 다음칸에 로봇이 없다면. 
    durable[robot[i]+1][0] -= 1
    durable[robot[i]+1][1] = True
    durable[robot[i]][1] = False

3. 올리는 위치에 로봇을 올린다.
    durable[location[1]][0] != 0 and durable[location[1]][1] == False:


'''

def getNext(v):
    global n
    if v == n*2-1:
        return 0
    return v+1

n, k = map(int, input().split())

robots = deque()
location = deque()
for i in range(2*n):
    location.append(i)

durable = {}
temp = list(map(int, stdin.readline().rstrip().split()))
for i in range(n*2):
    durable[i] = [temp[i], False]
turn = 0
while True:
    cnt = 0
    for l in location:
        # print(l, durable[l], end=' ')
        if durable[l][0] == 0:
            cnt+=1
    # print()
    if cnt >= k :
        break
    # print()

    # 벨트 회전 한다.
    location.appendleft(location.pop())
    # n번째 위치 발판에 로봇이 있다면 삭제해준다.
    if durable[location[n-1]][1]:
        durable[location[n-1]][1] = False
        robots.popleft()
    # print('robots : ', robots)
    # print('회전 완료 결과')
    # for l in location:
    #     print(l, durable[l], end=' ')
    # print()
    # print()

    # 벨트에 올라온 순서대로 한칸씩 이동시켜준다.
    l = len(robots)
    for i in range(l):
        val = robots.popleft()
        nxt = getNext(val)
        # 다음칸의 내구도가 존재하면서, 로봇이 없을 경우 -> 다음칸으로 이동이 가능하다.
        if durable[nxt][0] != 0 and not durable[nxt][1]:
            #다음칸이 내리는곳이라면

            if nxt == location[n-1]:
                pass
            else:
                durable[nxt][1] = True
                robots.append(nxt)

            durable[val][1] = False
            durable[nxt][0] -= 1
            # val + 1 을 초기화 해줘야 하지 않나? 1로 갈수도 있음!
        # 만약 이동하지 못하면 그대로 둔다.
        else:
            robots.append(val)
    # print('robots : ', robots)
    # print('한칸씩 이동 결과')
    # for l in location:
    #     print(l, durable[l], end=' ')
    # print()
    # print()

    # 올리는 발판에 로봇이 없다면 올려준다.
    if durable[location[0]][0] != 0 :
        robots.append(location[0])
        durable[location[0]][0] -= 1
        durable[location[0]][1] = True
    # print('robots : ', robots)
    # print('로봇올려준 결과')
    # for l in location:
    #     print(l, durable[l], end=' ')
    # print()
    # print()
    # print('스텝 끝')
    # print()
    turn+=1

print(turn)