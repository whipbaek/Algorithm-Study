import copy
from sys import stdin
from collections import deque
import heapq



def dfs(fishPosition, sharkPosition, sd, space, total,depth):
    global answer
    # swap 로직
    while True:
        # 물고기 번호 작은 순대로
        for fish in fishPosition:
            x, y = fishPosition[fish]
            v, d = space[x][y]
            cnt = 0
            while True:
                # 이동할 수 있는 방향이 없다면 나간다.
                if cnt == 8: break
                nx = x + dx[d]
                ny = y + dy[d]
                # 범위내이면서, 상어가 없다면 swap해준다.
                if 0 <= nx < 4 and 0 <= ny < 4:
                    if space[nx][ny][0] != -1:

                        # space Swap -> 값을 변경해야한다.
                        tv, td = space[nx][ny]
                        space[x][y] = [tv, td]
                        space[nx][ny] = [v, d]

                        # 빈칸이 아닐경우에만 값 갱신해준다.
                        if tv != 0:
                            fishPosition[tv] = [x, y]
                            fishPosition[v] = [nx, ny]
                        # 빈칸일경우, 한쪽만 값 갱신해주자.
                        if tv == 0:
                            fishPosition[v] = [nx, ny]
                        break
                    else:
                        d = changeDir(d)
                        cnt+=1
                else:
                    d = changeDir(d)
                    cnt += 1

        ox, oy = sharkPosition
        sx, sy = sharkPosition

        # ox, oy => 현재 depth에서 상어가 있는 곳
        # nx, ny => 목표 물고기가 있는곳
        # sx, sy => nx, ny를 만들기 위하여 더하는 중간값

        # 상어가 물고기를 먹는다.
        while True:
            nx = sx + dx[sd]
            ny = sy + dy[sd]
            # 범위에 들어가 있으며, 빈칸이 아닌경우에 갈 수 있다.
            if 0 <= nx < 4 and 0 <= ny < 4:
                if space[nx][ny][0] != 0:

                    # 먹을 물고기의 크기와 방향
                    tv, td = space[nx][ny]

                    # 상어가 원래 있던곳은 빈칸으로 만들어준다.
                    # 또한 우리가 먹을 물고기의 칸은 상어가 위치하는 칸으로 만들어준다.
                    t_space = copy.deepcopy(space)
                    t_space[ox][oy] = [0, 0]
                    t_space[nx][ny] = [-1, -1]

                    # 먹은 물고기는 제거해줌.
                    t_position = copy.deepcopy(fishPosition)
                    t_position.pop(tv)

                    dfs(t_position, [nx ,ny], td, t_space, total + tv, depth+1)

            # 범위를 벗어나면 값 갱신
            else:
                answer = max(answer, total)
                return
            sx = nx
            sy = ny

def changeDir(d):
    if d == 8:
        return 1
    return d+1

# empty, ↑, ↖, ←, ↙, ↓, ↘, →, ↗
dx = [0, -1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 0, -1, -1, -1, 0, 1, 1, 1]

space = [[0] * 4 for _ in range(4)]
fishPosition = {}

for i in range(4):
    temp = list(map(int ,stdin.readline().rstrip().split()))
    space[i][0] = temp[0:2]
    fishPosition[temp[0]] = [i, 0]

    space[i][1] = temp[2:4]
    fishPosition[temp[2]] = [i, 1]

    space[i][2] = temp[4:6]
    fishPosition[temp[4]] = [i, 2]

    space[i][3] = temp[6:8]
    fishPosition[temp[6]] = [i, 3]

fishPosition = dict(sorted(fishPosition.items()))
sharkPosition = [0, 0]
fishPosition.pop(space[0][0][0])
answer = space[0][0][0]
sd = space[0][0][1]
initVal = space[0][0][0]
space[0][0] = [-1, -1]

# dfs로 가능한 곳 모두 가본다.
# 갈곳 없다면 return 한다.
dfs(fishPosition, sharkPosition, sd, space, initVal, 0)



print(answer)





