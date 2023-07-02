from sys import stdin
from collections import deque

'''

0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.

원판에 수가 남아있다면, 인접하면서 수가 같은 것을 찾고, 같은 수를 모두 지운다.

'''


n, m, t = map(int, input().split())
circle = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(n) ]
methods = [ list(map(int, stdin.readline().rstrip().split())) for _ in range(t) ]


for method in methods:
    o, direction, rotate = method

    c_list = []
    # 배수 구하기
    for i in range(1, n+1):
        if i%o == 0:
            # 원판의 시작은 1이 아니라 0부터 ~ n-1 설정
            c_list.append(i-1)

    # 배수들 회전시켜준다.
    for c in c_list:
        # 어차피 m번돌면 제자리기에 불필요한 회전 없앰
        rotate = rotate % m
        area = m - rotate
        # 시계방향일경우
        if direction == 0:
                # 뒷부분 복사
                temp = circle[c][area:m]
                # 앞쪽 값들을 뒤로 땡긴다.
                circle[c][m-area:m] = circle[c][0:area]
                # 복사해둔값을 앞으로 땡긴다.
                circle[c][0:rotate] = temp

        # 반시계방향일경우
        else:
            # 앞 부분 복사
            temp = circle[c][0:rotate]
            # 뒷 부분을 앞부분으로 땡겨온다.
            circle[c][0:area] = circle[c][rotate:m]
            # 미리 복사해둔 앞 부분을 뒷부분으로 복사
            circle[c][area:m] = temp

    # print('회전 결과')
    # for c in circle:
    #     print(c)
    # print()

    # 인접한 값들을 모두 검사해본다.
    st = set()
    for i in range(n):
        for j in range(m):
            val = circle[i][j]
            if val == 0:
                continue
            if i == 0:
                if circle[i+1][j] == val:
                    st.add((i+1, j))

            elif i == n-1:
                if circle[i-1][j] == val:
                    st.add((i-1, j))

            else:
                if circle[i+1][j] == val:
                    st.add((i+1, j))

                if circle[i-1][j] == val:
                    st.add((i-1, j))


            # 왼쪽검사는 항상 가능하다. ( list[-1] 지원 )
            if circle[i][j-1] == val:
                st.add((i, j-1))

            # j == m-1 일때는 j[0]을 검사, 그 외에는 j+1을 검사해주면 된다.
            if j == m-1:
                if circle[i][0] == val:
                    st.add((i, 0))
            else:
                if circle[i][j+1] == val:
                    st.add((i, j+1))

    cnt = 0
    t_sum = 0


    # 변한게 하나라도 없었다면 평균 구하고 연산 진행한다. (set에 자료가 하나도 없다면)
    if not st:
        for i in range(n):
            for j in range(m):
                if circle[i][j] != 0:
                    cnt += 1
                    t_sum += circle[i][j]
        if cnt == 0:
            continue
        avg = t_sum/cnt

        # print('인접한 수가 없습니다.')
        # print('평균  :', avg)

        for i in range(n):
            for j in range(m):
                if circle[i][j] != 0:
                    if circle[i][j] > avg:
                        circle[i][j] -= 1
                    elif circle[i][j] < avg :
                        circle[i][j] += 1

    else:
        while st:
            x, y = st.pop()
            circle[x][y] = 0



# print('최종 결과')
# for c in circle:
#     print(c)
# print()

# 모든 프로레스가 끝났다면 합계를 구해준다.
answer = 0
for i in range(n):
    for j in range(m):
        answer += circle[i][j]
print(answer)