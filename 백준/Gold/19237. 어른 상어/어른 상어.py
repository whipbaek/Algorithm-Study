import copy
from sys import stdin
from collections import deque
import heapq

'''

목표 : 상어들은 다른 상어들을 쫓아낼려 함

- 1의 번호를 가진 상어는 나머지를 모두 쫓아낼 수 있다.

n*n 격자, m개의 상어

맨 처음에는, 모든 상어가 '자신의 위치'에 냄새를 뿌린다.
이후 1초마다 상어들은 상하좌우 인접한 칸으로 이동
이후에 해당칸에 자신의 냄새를 뿌린다.
-> 냄새는 상어가 k번 이동하고 나면 사라진다. (뿌린 시간 기준으로 k번 이동하면 사라진다는건가?)


이동 방법
1. 인접한 칸 중 아무 냄새가 없는곳을 우선시
2. 1번이 없다면 자신의 냄새가 있는 칸으로 방향을 잡는다.
    - 여러개일경우, 특정한 우선순위를 따른다. (해당 사항은 1, 2번 모두에 해당됨)

3. 모든 상어가 이동하고, 한칸에 여러마리의 상어가 남아있으면 가장 작은번호 빼고는 밖으로 쫓겨난다.


'''

# n*n 번 순회하면서 queue에 상어 위치를 넣어준다.
# 미리 복사해둔 2차원 배열을 활용해 빈칸이 있는지 확인한다.
# 우선순위 방향으로 돌면서, 빈칸있는지 확인하고, 확인시 바로 이동한다.
# 없으면 우선순위 방향으로 돌면서, 내 냄새가 있는지 확인한다.
# 해당 방향으로 이동했는데 상어가 위치한다면 크기가 작은놈이 이긴다.
    # 없으면 바로 냄새퍼뜨려준다.
# 만약 굴러들어온 상어가 이겼다면 냄새를 퍼뜨려준다.

# 냄새 배열을 선언해주고, 턴이 끝날때마다 -1해준다.


def solution(area, smell):
    global  shark_set, n, m, turn
    t_area = copy.deepcopy(area)
    t_smell = copy.deepcopy(smell)
    # 2차원 배열을 모두 순회하면서, 상어를 만나면 이동로직을 실행한다.
    # 이동은 무조건 하므로, 이동직전에 모든 smell을 1씩 없애준다. 그리고 1인 경우에는 [0,0] 으로 초기화.
    # 원본배열은 t-area 이다. t_area는 해쳐지면 안된다.
    # 실제로 이동은 area가 한다. 실제 상어의 감지도 area를 활용한다.
    # 탐색은 p_dir[번호-1][s_dir[번호-1] 배열을 순회하면된다.

    # 냄새가 1 마이너스 된다.
    for i in range(n):
        for j in range(n):
            if smell[i][j][1] > 1:
                smell[i][j][1] -= 1
            elif smell[i][j][1] == 1:
                smell[i][j] = [0, 0]

    for i in range(n):
        for j in range(n):
            flag = False
            # 우리가 움직일 상어가 존재한다면
            if t_area[i][j] != 0:
                sharkNum = t_area[i][j] - 1
                x, y = i, j

                # 해당 상어가 바라보는 방향의 이동 우선순위
                search_list = p_dir[sharkNum][s_dir[sharkNum]-1]
                for val in search_list:
                    nx = x + dx[val]
                    ny = y + dy[val]
                    # 범위안에 들어가면서, 빈칸이면서, 냄새도 없는경우
                    if 0<=nx<n and 0<=ny<n:
                        if t_area[nx][ny] == 0 and t_smell[nx][ny][0] == 0:
                            flag = True
                            # 해당위치에 상어가 있을경우
                            if area[nx][ny] != 0:
                                # 내가 더 작은 상어일경우
                                # 큰 상어를 목록에서 삭제, area값 갱신
                                if sharkNum+1 < area[nx][ny]:
                                    shark_set.remove(area[nx][ny])
                                    area[nx][ny] = t_area[x][y]
                                    area[x][y] = 0
                                    s_dir[sharkNum] = val
                                    smell[nx][ny] = [sharkNum+1, k]
                                    break
                                else:
                                    # 내가 더 큰 상황이면 나를 삭제한다.
                                    shark_set.remove(t_area[x][y])
                                    area[x][y] = 0
                                    break
                            # 상어가 없으면 이동해주고 바로 냄새를 뿌려준다.
                            # 방향도 갱신해줘야한다.
                            else:
                                area[nx][ny] = t_area[x][y]
                                area[x][y] = 0
                                smell[nx][ny] = [sharkNum+1, k]
                                s_dir[sharkNum] = val
                                break

                if not flag:
                    # 빈칸이 없는경우
                    for val in search_list:
                        nx = x + dx[val]
                        ny = y + dy[val]
                        # 범위안에 들어가면서, 냄새가 내것인경우
                        # 이동해주고 바로 냄새를 퍼트려준다.
                        if 0<=nx<n and 0<=ny<n:
                            if t_smell[nx][ny][0] == sharkNum+1:
                                smell[nx][ny] = [sharkNum+1, k]
                                area[nx][ny] = area[x][y]
                                area[x][y] = 0
                                s_dir[sharkNum] = val
                                break

    turn +=1
    return area, smell

# empty, up, down, left, right -> idx는 1부터 시작.
dx = [0, -1, 1, 0, 0]
dy = [0, 0, 0, -1, 1]
# s_dir -> 상어가 바라보는 방향을 저장한 배열
n, m, k = map(int, stdin.readline().rstrip().split())
area = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
s_dir = list(map(int, stdin.readline().rstrip().split()))
shark_set = set()
turn = 0
smell = [ [0] * n for _ in range(n)]
for i in range(n):
    for j in range(n):
        if area[i][j] != 0:
            smell[i][j] = [area[i][j], k]
        else:
            smell[i][j] = [0, 0]

for i in range(1, m+1): shark_set.add(i)
# p_dir -> 상어의 우선순위 p_dir[0][1] -> 0번 상어가 아래쪽바라볼때 우선순위
# 단 상어는 1번부터 m번까지 존재, 조회시 -1해서 조회해야한다. (방향은 값 그대로 사용)
p_dir = [[0] * 4 for _ in range(m)]
for i in range(m):
    for j in range(4):
        p_dir[i][j] = list(map(int, stdin.readline().rstrip().split()))

while True:
    area, smell = solution(area, smell)
    if len(shark_set) == 1:
        break
    if turn > 1000:
        break
print(turn) if turn <= 1000 else print(-1)
