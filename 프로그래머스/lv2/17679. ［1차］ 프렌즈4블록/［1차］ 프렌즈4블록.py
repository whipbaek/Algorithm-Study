def getPositions(x, y, r, c, ch, board, location):
    temp = [(x, y)]
    for v in location:
        nx = x + v[0]
        ny = y + v[1]
        
        if 0 <= nx < r and 0 <= ny < c and board[nx][ny] == ch:
            temp.append((nx, ny))
    
    if len(temp) == 4 : return temp
    return False


def getRemoveList(x, y, r, c, ch, board):
    
    leftUp = [(0, 1), (1, 1), (1, 0)]
    
    idxList = set()
    temp = getPositions(x, y, r, c, ch, board, leftUp)
    if temp:
        for v in temp: idxList.add(v)
    
    return idxList

def readjustBoard(r, c, board):
    for i in range(c):
        for j in range(r-2, -1, -1):
            
            if board[j][i] == 0: continue
            k = r-1
            while True:
                if k==j : break
                if board[k][i] == 0: break
                k-=1
            
            if k!=j:
                board[k][i] = board[j][i]
                board[j][i] = 0

    return board

def solution(m, n, board):
    r = m
    c = n
    answer = 0
    
    arrBoard = [ [0] * c for _ in range(r) ]
    for i in range(r):
        for j in range(c):
            arrBoard[i][j] = board[i][j]

    while True:
        ansList = set()
        for i in range(r):
            for j in range(c):
                if arrBoard[i][j] != 0:
                    idxList = getRemoveList(i, j, r, c, arrBoard[i][j], arrBoard)
                    if len(idxList) != 0:
                        for v in idxList: ansList.add(v)

        # 만약 ansList가 비어있다면, 반복문을 탈출하고 종료하면 된다.
        if len(ansList) == 0 : break

        answer += len(ansList)
        
        # 그 외에는 0으로 바꿔준다.
        for v in ansList:
            arrBoard[v[0]][v[1]] = 0

        # 모든 칸을 아래로 내려주는 함수
        arrBoard = readjustBoard(r, c, arrBoard)
    
    return answer