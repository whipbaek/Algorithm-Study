def solution(dirs):
    
    dic = {'U':(-1, 0), 'D':(1, 0), 'R':(0, 1), 'L':(0, -1)}
    x = y = 0
    s = set()    
    for d in dirs:
        nx = x + dic[d][0]
        ny = y + dic[d][1]
    
        if -5<=nx<=5 and -5<=ny<=5:
            if (x, y, nx, ny) not in s and (nx, ny, x, y) not in s:
                s.add((x, y, nx, ny))
            x = nx
            y = ny
    return len(s)