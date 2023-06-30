from sys import stdin

'''

가장 위쪽 (1, 1)

1. 좋아하는 학생이 가장 많은곳으로 이동

2. 1이 만다면 인접한 칸 중 빈칸이 가장 많은 곳

3. 2도 많다면 행이 가장 작은 칸, 그것도 많으면 열이 가장 작은 칸

n*n을 돌면서 하나씩 점검

'''

dx = [0, 0, -1 ,1]
dy = [1, -1, 0, 0]

n = int(input())

friends = []

classRoom = [[0] * n for _ in range(n)]
posistion = {}
for i in range(n*n):
    s, f1, f2, f3, f4 = map(int, stdin.readline().rstrip().split())
    friends.append([s, f1, f2, f3, f4])
    posistion[s] = i

for friend in friends:

    fx = fy = 0
    numOfFriend = 0
    empty = 0
    for x in range(n-1, -1, -1):
        for y in range(n-1, -1, -1):

            if classRoom[x][y] != 0:
                continue

            t_numOfFriend = 0
            t_empty = 0
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]

                if 0<=nx<n and 0<=ny<n:
                    if classRoom[nx][ny] in friend:
                        t_numOfFriend += 1
                    elif classRoom[nx][ny] == 0:
                        t_empty += 1

            # 갱신 작업
            if t_numOfFriend > numOfFriend:
                numOfFriend = t_numOfFriend
                empty = t_empty
                fx = x
                fy = y


            elif t_numOfFriend == numOfFriend:
                if t_empty > empty:
                    empty = t_empty
                    fx = x
                    fy = y
                elif t_empty == empty:
                    fx = x
                    fy = y
    classRoom[fx][fy] = friend[0]

answer = 0

for x in range(n):
    for y in range(n):
        val = classRoom[x][y]
        cnt = 0
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0<=nx<n and 0<=ny<n:
                if classRoom[nx][ny] in friends[posistion[val]]:
                    cnt += 1

        if cnt == 1: answer += 1
        if cnt == 2: answer += 10
        if cnt == 3: answer += 100
        if cnt == 4: answer += 1000

print(answer)


