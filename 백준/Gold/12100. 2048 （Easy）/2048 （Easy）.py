import copy
import itertools
from sys import stdin

def rotate(arr, dir):

    # 시계 방향
    if dir == 0:
        return list(map(list, zip(*arr[::-1])))

    # 반시계 방향
    if dir == 1:
        return list(map(list, zip(*arr)))[::-1]

def move(temp):
    # 좌측으로 이동
    global n
    i = 0
    arr = copy.deepcopy(temp)
    # 값을 가장 왼쪽으로 붙인다.
    while i <= n:
        # 값을 왼쪽으로 땡겨온다.
        for j in range(i, n):
            # 유효한 값이라면
            if arr[j] != 0 :
                arr[i] = arr[j]
                if i != j:
                    arr[j] = 0
                break

        # 같은 값이 있는지 체킹
        for j in range(i+1, n):
            if arr[j] == 0: continue

            if arr[i] == arr[j]:
                arr[i] *= 2
                arr[j] = 0
                break

            if arr[i] != arr[j]:
                arr[i+1] = arr[j]
                if i+1 != j:
                    arr[j] = 0
                break
        i += 1

    return arr

# # initialize
n = int(input())
origin_board = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
move_list = list(itertools.product([0, 1, 2, 3], repeat=5))

answer = 0
for m in move_list:
    board = copy.deepcopy(origin_board)
    for val in m:

        # 위 아래일경우 배열을 회전시켜준다.
        if val == 1 or val == 3:
            board = rotate(board, 1)

        if val == 0 or val == 1:
            for i in range(n):
                board[i] = move(board[i])

        # 오른쪽으로 간다면 역방향
        if val == 2 or val == 3:
            for i in range(n):
                board[i] = move(list(reversed(board[i])))

    answer = max(answer, max(map(max, board)))

print(answer)