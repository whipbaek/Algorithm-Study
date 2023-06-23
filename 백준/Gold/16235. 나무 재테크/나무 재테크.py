import copy
from sys import stdin
from collections import deque

# Initialize
dx = [ 0, 0, 1, -1, 1,  1, -1, -1]
dy = [-1, 1, 0,  0, 1, -1,  1, -1]

n, m, year = map(int, stdin.readline().rstrip().split())
A = [list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
saved = [[0] * (n + 1) for _ in range(n + 1)]

for i in range(1, n+1):
    for j in range(1, n+1):
        saved[i][j] = [5, deque()]


location = set()
for _ in range(m):
    x, y, age = map(int, stdin.readline().rstrip().split())
    saved[x][y][1].append(age)
    saved[x][y][0] = 5
    location.add((x, y))


# main logic
for _ in range(year): # max year = 1000
    breed = []
    del_list = []

    # 봄 여름
    for k in range(1, n+1):
        for l in range(1, n+1):
            if len(saved[k][l][1]) > 0:
                x,y = k, l
                temp = deque()

                # 나무 리스트를 모두 돌아본다.
                feed = saved[x][y][0]
                trees = saved[x][y][1]
                death_feed = 0
                for i in range(len(trees)):
                    age = trees[i]
                    if feed >= age:
                        feed -= age
                        temp.append(age+1)
                        if (age + 1) % 5 == 0:
                            breed.append([x, y])
                    else:
                        # 만약 더 이상 못먹는 상황이라면 죽는 나무들이다.
                        death_feed += (trees[i]//2)

                    saved[x][y] = [feed + death_feed, temp]

        # 나무가 모~두 죽어버렸다면 나무리스트에서 없애준다.
        if len(saved[x][y][1]) == 0:
            del_list.append([x,y])

    # 가을이 되면 5의 배수인 친구들을 번식시켜준다.
    for val in breed:
        x, y = val
        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            # 번식이 가능하다면 번식 시켜주고, 나무 존재 위치를 갱신한다.
            if 1 <= nx <= n and 1 <= ny <= n:
                saved[nx][ny][1].appendleft(1)
    # 겨울에는 양분 추가해준다.
    for i in range(n):
        for j in range(n):
            saved[i + 1][j + 1][0] += A[i][j]

answer = 0

for i in range(1, n+1):
    for j in range(1, n+1):
        if len(saved[i][j][1]) != 0:
            answer += len(saved[i][j][1])

print(answer)