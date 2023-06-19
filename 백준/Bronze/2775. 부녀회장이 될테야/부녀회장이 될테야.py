t = int(input())

d = [[0] * 15 for _ in range(15)]

for j in range(15):
    d[0][j] = j

for i in range(1, 15):
    for j in range(1, 15):
        d[i][j] = d[i-1][j] + d[i][j-1]

for _ in range(t):
    k = int(input())
    n = int(input())
    print(d[k][n])