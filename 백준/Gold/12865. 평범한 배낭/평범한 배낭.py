n, k = map(int, input().split())

board = [ [0] * (k+1) for _ in range(n+1) ]
bags = [(0, 0)]
for _ in range(n):
    w, v = map(int, input().split())
    bags.append((w, v))

for i in range(1, n+1):
    for j in range(1, k+1):
        w = bags[i][0]
        v = bags[i][1]

        # 무게가 넘는다면
        if j < w:
            board[i][j] = board[i-1][j]
        # 그렇지 않다면
        else:
            board[i][j] = max(board[i-1][j], board[i-1][j - w] + v)

print(board[n][k])