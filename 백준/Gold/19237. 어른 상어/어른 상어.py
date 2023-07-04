import copy
from sys import stdin

def smellMinus():
    for i in range(n):
        for j in range(n):
            if smell[i][j][1] > 1:
                smell[i][j][1] -= 1
            elif smell[i][j][1] == 1:
                smell[i][j] = [0, 0]

def solution():
    global turn
    # 원본을 복사해둔다.
    t_area = copy.deepcopy(area)
    t_smell = copy.deepcopy(smell)

    # 냄새가 1 마이너스 된다.
    smellMinus()

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
                                # 내가 더 작은 상어일경우 -> 큰 상어 삭제 및 정보 갱신
                                if sharkNum+1 < area[nx][ny]:
                                    shark_set.remove(area[nx][ny])
                                    area[nx][ny] = t_area[x][y]
                                    area[x][y] = 0
                                    s_dir[sharkNum] = val
                                    smell[nx][ny] = [sharkNum+1, k]
                                    break

                                # 내가 더 크면 나를 삭제한다.
                                shark_set.remove(t_area[x][y])
                                area[x][y] = 0
                                break

                            # 상어가 없으면 이동해주고 바로 냄새를 뿌려주고, 정보갱신함
                            else:
                                area[nx][ny] = t_area[x][y]
                                area[x][y] = 0
                                smell[nx][ny] = [sharkNum+1, k]
                                s_dir[sharkNum] = val
                                break

                # 빈칸이 없는경우
                if not flag:
                    for val in search_list:
                        nx = x + dx[val]
                        ny = y + dy[val]
                        # 범위안에 들어가면서, 냄새가 내것인경우, 이동해주고 바로 냄새를 퍼트려준다.
                        if 0<=nx<n and 0<=ny<n:
                            if t_smell[nx][ny][0] == sharkNum+1:
                                smell[nx][ny] = [sharkNum+1, k]
                                area[nx][ny] = area[x][y]
                                area[x][y] = 0
                                s_dir[sharkNum] = val
                                break

    turn +=1

# empty, up, down, left, right -> idx는 1부터 시작.
dx = [0, -1, 1, 0, 0]
dy = [0, 0, 0, -1, 1]

n, m, k = map(int, stdin.readline().rstrip().split())
area = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
turn = 0

# s_dir -> 상어가 바라보는 방향을 저장한 배열
s_dir = list(map(int, stdin.readline().rstrip().split()))
# shark_set -> 현재 남아있는 상어들
shark_set = set()
for i in range(1, m+1): shark_set.add(i)

# smell -> 상어의 냄새를 저장
smell = [ [0] * n for _ in range(n)]

for i in range(n):
    for j in range(n):
        if area[i][j] != 0:
            smell[i][j] = [area[i][j], k]
        else:
            smell[i][j] = [0, 0]

# p_dir -> 상어의 우선순위 p_dir[0][1] -> 0번 상어가 아래쪽바라볼때 우선순위
# 단 상어는 1번부터 m번까지 존재, 조회시 -1해서 조회해야한다. (방향은 값 그대로 사용)
p_dir = [[0] * 4 for _ in range(m)]
for i in range(m):
    for j in range(4):
        p_dir[i][j] = list(map(int, stdin.readline().rstrip().split()))

while True:
    solution()
    if len(shark_set) == 1 or turn > 1000:
        break

print(turn) if turn <= 1000 else print(-1)
