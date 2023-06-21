import copy
import itertools
from sys import stdin
import sys

def search(x, y, d):
    global dx, dy, n, m, temp

    while True:
        nx = x + dx[d]
        ny = y + dy[d]

        # 범위 안에 있으며 벽이 아니라면
        if 0 <= nx < n and 0 <= ny < m and temp[nx][ny] != 6:
            # 빈칸일경우 방문처리 해준다. cctv면 그냥 넘어가면 된다.
            if temp[nx][ny] == 0:
                temp[nx][ny] = 9
            x = nx
            y = ny
        else:
            break


# Initialize
n, m = map(int, input().split())
office = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n)]
cctvs = []
answer = sys.maxsize
for i in range(n):
    for j in range(m):
        if office[i][j] != 0 and office[i][j] != 6:
            cctvs.append([office[i][j], [i, j]])

# cctv 회전의 모든 경우의 수
directions = itertools.product([0, 1, 2, 3], repeat=len(cctvs))

# 우 하 좌 상 순서
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


# cctv_d[cctv번호][경우의 수 방향]
cctv_d = [
    [[]],
    [[0], [1], [2], [3]],
    [[0, 2], [1, 3], [0, 2], [1, 3]],
    [[0, 1], [1, 2], [2, 3], [3, 0]],
    [[3, 0, 1], [0, 1, 2], [1, 2, 3], [2, 3, 0]],
    [[0, 1, 2, 3], [0, 1, 2, 3], [0, 1, 2, 3], [0, 1, 2, 3]]
]

# 특정 그래프를 탐색하려면, 좌표와 방향을 지시해주어야 한다.
for dire in directions:
    temp = copy.deepcopy(office)
    # 해당 사이즈는 cctv 크기와 같다.
    for j in range(len(dire)):
        # 각 cctv와 회전에 맞게 값을 넣어준다.
        for val in cctv_d[cctvs[j][0]][dire[j]]:
            x, y = cctvs[j][1]
            search(x, y, val)

    temp_ans = 0
    for i in range(n):
        for j in range(m):
            if temp[i][j] == 0:
                temp_ans += 1

    # for v in temp:
    #     print(v)
    # print()
    answer = min(answer, temp_ans)



print(answer)

