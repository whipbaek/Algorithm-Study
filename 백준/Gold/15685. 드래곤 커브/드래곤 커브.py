import copy

def bfs(x, y, d, g):
    global visited, dragons, dy, dx

    # y는 열 x는 행
    # 오른쪽으로 가는것 -> [][여기] 값이 변하는것 (0, 2)
    # 위로 가는것 -> [여기][] 값이 변하는것  (1, 3)
    dy = [0, -1, 0, 1]
    dx = [1, 0, -1, 0]

    version = dragons[d][g]
    visited[y][x] = 1
    for v in version:
        y = y + dy[v]
        x = x + dx[v]
        visited[y][x] = 1


def rotate(v):
    if v == 3:
        return 0
    return v+1

def makedragon(before):
    temp = copy.deepcopy(before)
    l = len(before)-1
    for i in range(l, -1, -1):
        temp.append(rotate(before[i]))
    return temp

n = int(input())

dragons = [ [] * 11 for _ in range(4) ]

# 초기값 설정
dragons[0].append([0])
dragons[1].append([1])
dragons[2].append([2])
dragons[3].append([3])
answer = 0
# 4방향, 드래곤 커브를 10세대까지 만들어둔다.
for i in range(4):
    for j in range(1, 11):
        dragons[i].append(makedragon(dragons[i][j-1]))


visited = [[0] * 101 for _ in range(101)]
for _ in range(n):
    x, y, d, g = map(int, input().split())
    bfs(x, y, d, g)


for i in range(100):
    for j in range(100):
        if visited[i][j] == 1 and visited[i+1][j+1] == 1 and visited[i+1][j] == 1 and visited[i][j+1] == 1:
            answer += 1
print(answer)