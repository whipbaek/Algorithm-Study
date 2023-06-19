d = [1] * 31

d[0] = 1
d[1] = 1
for i in range(2, 31):
    d[i] = d[i-1] * i

t = int(input())
for _ in range(t):
    n, m = map(int, input().split())
    print(d[m] // (d[n] * d[(m-n)]))