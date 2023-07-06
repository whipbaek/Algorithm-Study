from sys import stdin

def changeDir(d):
    if d == 3: return 0
    return d+1

def spreadSand(nx, ny, total, arr):
    global answer, sand
    leftSand = total
    for val in arr:
        tx = nx + val[0]
        ty = ny + val[1]
        percent = val[2]

        # 범위 안에 들어가면
        if 0 <= tx < n and 0 <= ty < n:
            # alpha 파트가 아니라면
            if percent != -1:
                sand[tx][ty] += (total * percent // 100)
                leftSand -= (total * percent // 100)
            # alpha 파트일경우
            else:
                sand[tx][ty] += leftSand

        # 범위 벗어나면 답에 더해주고, 총 값은 빼준다.
        else:
            if percent != -1:
                answer += (total * percent // 100)
                leftSand -= (total * percent // 100)
            else:
                answer += leftSand


def cycle(cnt, limit, x, y, d):
    global dx, dy, sand, toTheUp, toTheDown, toTheLeft, toTheRight, answer
    while True:
        if cnt == limit:
            return x, y
        nx = x + dx[d]
        ny = y + dy[d]
        total = sand[nx][ny]
        if d == 0: spreadSand(nx, ny, total, toTheLeft)
        elif d == 1: spreadSand(nx, ny, total, toTheDown)
        elif d == 2: spreadSand(nx, ny, total, toTheRight)
        elif d == 3: spreadSand(nx, ny, total, toTheUp)
        sand[nx][ny] = 0

        x = nx
        y = ny
        cnt += 1

        # for a in sand:
        #     print(a)
        # print()

toTheLeft = [(1, 1, 1), (-1, 1, 1), (-1, 0, 7), (1, 0, 7), (2, 0, 2), (-2, 0, 2), (-1, -1, 10), (1, -1, 10), (0, -2, 5), (0, -1, -1)]
toTheDown = [(-1, 1, 1), (-1, -1, 1), (0, 1, 7), (0, -1, 7), (0, 2, 2), (0, -2, 2), (1, 1, 10), (1, -1, 10), (2, 0, 5), (1, 0, -1)]
toTheRight = [(-1, -1, 1), (1, -1, 1), (1, 0, 7), (-1, 0, 7), (2, 0, 2), (-2, 0, 2), (1, 1, 10), (-1, 1, 10), (0, 2, 5), (0, 1, -1)]
toTheUp = [(1, 1, 1), (1, -1, 1), (0, 1, 7), (0, -1, 7), (0, 2, 2), (0, -2, 2), (-1, 1, 10), (-1, -1, 10), (-2, 0, 5), (-1, 0, -1)]

# # 좌 하 우 상
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
answer = 0
# # x, y는 시작점
n = int(input())
x = y = (n//2)
d = 0
sand = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]

limit = 1

while True:
    if limit == n-1:
        for _ in range(3):
            x, y = cycle(0, limit, x, y, d)
            d = changeDir(d)
        limit += 1
    elif limit < n-1:
        for _ in range(2):
            x, y = cycle(0, limit, x, y, d)
            d = changeDir(d)
        limit +=1
    else:
        break

print(answer)

