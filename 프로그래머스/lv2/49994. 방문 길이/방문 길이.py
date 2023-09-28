from collections import deque

x = 0
y = 0

dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

s = set()

def func(idx):
    global x, y, dx, dy, s
    nx = x + dx[idx]
    ny = y + dy[idx]
    
    if -5<=nx<=5 and -5<=ny<=5:
        if (x, y, nx, ny) not in s and (nx, ny, x, y) not in s:
            s.add((x, y, nx, ny))
        x = nx
        y = ny

def solution(dirs):

    for d in dirs:
        if d == 'U': func(0)
        if d == 'D': func(1)
        if d == 'R': func(2)
        if d == 'L': func(3)
    return len(s)