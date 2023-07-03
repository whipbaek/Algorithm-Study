from sys import stdin #hello wordl
'''

블록은 다른 블록을 만나거나, 경계를 만나기전까지 이동한다.

특정 행 or 열이 가득차있으면 사라진다.
또한 테트리스처럼 위에 있는값들이 내려온다.

연한색의 칸 => 특별 칸



'''

def chagne(v):
    if v == 0 : return 3
    if v == 1 : return 2
    if v == 2 : return 1
    if v == 3 : return 0

def solution(color, val, area):
    global answer
    t, x, y = val
    if color == 'green':
        x, y = y, x
        x = chagne(x)
        if t == 2:
            x-=1
            t = 3
        elif t == 3:
            t = 2

    isInstall = -1

    for i in range(6):
        # 1 * 1 block
        if t == 1:
            if area[x][i] != 0:
                area[x][i - 1] = 1
                isInstall = i - 1
                break
            # at the end of the range
            if i == 5 and area[x][i] == 0:
                area[x][i] = 1
                isInstall = i
                break

        # 1 * 2 block (width)
        if t == 2:
            if i < 5 and area[x][i + 1] != 0:
                area[x][i] = 1
                area[x][i - 1] = 1
                isInstall = i
                break
            if i == 4 and area[x][i + 1] == 0 and area[x][i] == 0:
                area[x][i] = 1
                area[x][i + 1] = 1
                isInstall = i+1
                break

        # 2 * 1 block (height)
        if t == 3:
            if area[x][i] != 0 or area[x + 1][i] != 0:
                area[x][i - 1] = 1
                area[x + 1][i - 1] = 1
                isInstall = i - 1
                break
            if i == 5 and area[x][i] == 0 and area[x + 1][i] == 0:
                area[x][i] = 1
                area[x + 1][i] = 1
                isInstall = i
                break

    # check and remove Logic
    while True:
        cnt = 0
        for i in range(4):
            if area[i][isInstall] != 0:
                cnt += 1

        # need to remove and pull
        if cnt == 4:
            answer += 1
            for j in range(isInstall, -1, -1):
                for k in range(4):
                    if j == 0:
                        area[k][j] = 0
                    else:
                        area[k][j] = area[k][j - 1]

        # if there's no more line to remove
        else:
            if t == 2 and isInstall != 0:
                isInstall -= 1
                continue
            break

    # ligth block area Logic
    while True:
        move = False
        for j in range(4):
            if area[j][1] != 0:
                move = True
                break

        if not move:
            break

        for j in range(5, -1, -1):
            for k in range(4):
                if j == 0 :
                    area[k][j] = 0
                else:
                    area[k][j] = area[k][j - 1]

    return area



n = int(input())
blocks = []
for _ in range(n):
    blocks.append(list(map(int, stdin.readline().rstrip().split())))

greenArea = [[0] * 6 for _ in range(4)]
blueArea = [[0] * 6 for _ in range(4)]

answer = 0

for block in blocks:
    blueArea = solution('blue', block, blueArea)

    # print('blue')
    # for b in blueArea:
    #     print(b)
    # print()

    greenArea = solution('green', block, greenArea)

    # print('green')
    # for g in greenArea:
    #     print(g)
    # print()

total = 0
for i in range(4):
    for j in range(6):
        if greenArea[i][j] != 0:
            total += 1
        if blueArea[i][j] != 0:
            total += 1

print(answer)
print(total)
