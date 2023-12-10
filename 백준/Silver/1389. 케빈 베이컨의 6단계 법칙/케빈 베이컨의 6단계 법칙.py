from sys import stdin
import sys
from collections import deque

n, m = map(int, input().split())
friends = [[] for _ in range(n+1)]
count = 0
result = []

def bfs(start):
    global count, result
    visited = [0] * (n+1)
    queue = deque([start])
    visited[start] = count
    count = 1
    while queue:
        v = queue.popleft()
        # print(v, end=' '), print()
        for f in friends[v]:
            if visited[f] == 0 and f != start:
                queue.append(f)
                visited[f] = visited[v]+1
    result.append(sum(visited))

for i in range(m):
    a, b = map(int, input().split())
    friends[a].append(b)
    friends[b].append(a)


for i in range(1, n+1):
    count = 0
    bfs(i)

print(result.index(min(result))+1)


# 케빈 베이컨의 6단계 법칙
'''
문제 아이디어
1. bfs를 활용하여 최단거리를 찾는 문제이다.
2. 모든 숫자에서 시작해보고, 그 나머지 숫자에 달하는 길이를 모두 더한다.
3. 그리고 그 중 가장 짧은 사람을 출력하면 된다.
'''
#

