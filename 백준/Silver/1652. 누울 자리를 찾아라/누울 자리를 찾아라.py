from sys import stdin

n = int(input())

condo = [list(map(str, stdin.readline().rstrip())) for _ in range(n) ]

w = 0
h = 0
for i in range(n):
    count = 0
    for j in range(n):
        if condo[i][j] == '.':
            count +=1
        else:
            if count >=2:
                w+=1
            count = 0
    if count >=2:
        w+=1
for i in range(n):
    count = 0
    for j in range(n):
        if condo[j][i] == '.':
            count += 1
        else:
            if count >= 2:
                h += 1
            count = 0
    if count >=2:
        h +=1
print(w, h)
