from sys import stdin

def search(x, y):
    global ladder, n, m
    target = y
    # 세로로 탐색이 끝날때 까지
    while x <= m:
        # 오른쪽으로 가는 사다리가 있다면
        if ladder[x][y] == 1:
            x += 1
            y +=1
        elif ladder[x][y-1] == 1:
            x +=1
            y -=1
        else:
            x +=1

    if y == target:
        return True
    return False

def dfs(cnt, limit):
    global ladder, answer
    if cnt == limit:
        for i in range(1, n+1):
            if not search(1, i):
                return 
        print(cnt)
        exit(0)

    for i in range(1, m+1):
        for j in range(1, n):
            if ladder[i][j] == 0:
                if ladder[i][j+1] == 1:
                    continue
                ladder[i][j] = 1
                dfs(cnt+1, limit)
                ladder[i][j] = 0

# Initialize
n, h, m = map(int, stdin.readline().rstrip().split())
ladder = [ [0] * (n+1) for _ in range(m+1)]
for _ in range(h):
    x, y = map(int, stdin.readline().rstrip().split())
    ladder[x][y] = 1

for i in range(4):
    dfs(0, i)

print(-1)