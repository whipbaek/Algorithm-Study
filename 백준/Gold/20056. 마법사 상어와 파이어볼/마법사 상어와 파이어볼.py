import copy
from sys import stdin

'''

n*n 격자에 파이어볼 m개 발사.
파이어볼 위치 (r,c) -> [질량 m , 방향 d , 속력 s]


1. 각 파이어볼의 이동이 일어난다.

2. 이동이 끝난뒤, 같은 칸에 2개이상의 파이어볼이 있다면
    합치고, 4개로 다시 나눠준다.
    질량은  총 질량//5
    속력은  총 속력//합쳐진 파이어볼 개수
    
    => 방향은 합쳐지는 파이어볼의 방향이 모두 홀수이거나, 모두 짝수면 0,2,4,6
       아니라면 1,3,5,7이 된다.
   질량이 0이면 파이어볼은 소멸된다.

'''

'''

결과 배열을 하나 만든다.

이후 원본 배열을 돌면서 각 요소의 리스트를 검사하면서 이동을 시작한다.
(이동 결과는 결과배열에 저장해야한다.)

결과배열에서 순회하면서, 리스트의 길이가 2 이상일경우 파이어볼의 합치는 로직을 실행해준다.

'''

def solution():
    # 결과배열을 선언한다.
    global board
    res_board = [[[] for _ in range(1) for _ in range(n)] for _ in range(n)]
    ans_board = [[[] for _ in range(1) for _ in range(n)] for _ in range(n)]

    # 배열을 순회하면서 파이어볼을 이동해준다.
    for i in range(n):
        for j in range(n):
            if len(board[i][j]) > 0:
                for val in board[i][j]:
                    m, s, d = val

                    nx = i+dx[d]*s
                    ny = j+dy[d]*s
                    if 0<=nx<n and 0<=ny<n :
                        res_board[nx][ny].append([m, s, d])
                    else:
                        '''
                        nx나 ny가 n을 넘는경우
                        if n이 5인데, 5인경우 -> 0
                        if nx >= n : nx%n
                        
                        nx나 ny가 음수인경우
                        if n이 5일때 -1인 경우 -> 4로 이동
                        n + n % (- -음수) 
                        
                        만약 n이 5인데, nx가 -5라면?
                        0, 1, 2, 3, 4
                        다시 0으로 돌아와야한다.
                        5%5 => 0
                        '''
                        tx = nx
                        ty = ny
                        if nx >= n: nx = tx%n
                        elif nx < 0:
                            nx = n - ((-tx)%n)
                            if nx == n : nx = 0

                        if ny >= n: ny = ty%n
                        elif ny < 0 :
                            ny = n - ((-ty)%n)
                            if ny == n : ny = 0

                        res_board[nx][ny].append([m, s, d])
    # print('이동 완료')
    # for res in res_board:
    #     print(res)
    # print()

    # 결과 배열에서 파이어볼이 2개이상 있는 칸의 처리를 시작한다.
    # 알아야 하는 값들 <총 질량, 총 속력, 개수, 모두 홀수인지, 짝수인지>

    for i in range(n):
        for j in range(n):
            if len(res_board[i][j]) >= 2:

                isOdd = False
                isEven = False
                totalM = 0
                totalS = 0
                totalNum = len(res_board[i][j])

                for val in res_board[i][j]:
                    m, s, d = val
                    totalM += m
                    totalS += s
                    if d%2 == 1:
                        isOdd = True
                    else:
                        isEven = True

                resM = totalM//5
                resS = totalS//totalNum

                evenDir = [0, 2, 4, 6]
                oddDir = [1, 3, 5, 7]

                # 합한 질량이 0보다 큰 경우
                if resM > 0:
                    for idx in range(4):
                        # 홀짝 섞여있는 경우
                        if isOdd and isEven:
                            ans_board[i][j].append([resM, resS, oddDir[idx]])
                        else:
                            ans_board[i][j].append([resM, resS, evenDir[idx]])
            else:
                ans_board[i][j] = res_board[i][j]
    # print('파이어볼 조정 완료')
    # for an in ans_board:
    #     print(an)
    # print()

    board = copy.deepcopy(ans_board)


dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]
n, m, k = map(int, input().split())
board = [ [[] for _ in range(1) for _ in range(n)]  for _ in range(n) ]

for _ in range(m):
    r, c, m, s, d = map(int, stdin.readline().rstrip().split())
    # 질량, 속도, 방향
    board[r-1][c-1].append([m, s, d])

# print('초기')
# for b in board:
#     print(b)
# print()

for _ in range(k):
    solution()


answer = 0
for i in range(n):
    for j in range(n):
        for val in board[i][j]:
            answer += val[0]
print(answer)