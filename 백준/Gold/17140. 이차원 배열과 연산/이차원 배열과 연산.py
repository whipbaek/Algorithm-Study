import heapq
import copy

def operate_R():

    global r_size, c_size, arr

    for i in range(r_size):
        mid = []
        for j in range(c_size):
            heapq.heappush(mid, arr[i][j])
        l = 0
        res = []
        while mid:
            val = heapq.heappop(mid)
            if val == 0: continue
            l += 1
            count = 1
            while mid:
                if val != mid[0]:
                    break
                count += 1
                heapq.heappop(mid)
            res.append([val, count])
        l *= 2

        c_size = max(l, c_size)

        res = sorted(res, key=lambda x: (x[1], x[0]))
        j = 0
        k = 0

        while True:
            if j >= l: break
            arr[i][j] = res[k][0]
            arr[i][j + 1] = res[k][1]

            j += 2
            k += 1

        for m in range(l, c_size):
            arr[i][m] = 0


def operate_C():

    global r_size, c_size, arr

    for i in range(c_size):
        mid = []
        for j in range(r_size):
            heapq.heappush(mid, arr[j][i])
        l = 0
        res = []
        while mid:
            val = heapq.heappop(mid)
            if val == 0: continue
            l += 1
            count = 1
            while mid:
                if val != mid[0]:
                    break
                count +=1
                heapq.heappop(mid)
            res.append([val, count])
        l *= 2
        r_size = max(l, r_size)

        res = sorted(res, key=lambda x: (x[1], x[0]))
        j = 0
        k = 0

        while True:
            if j >= l : break
            arr[j][i] = res[k][0]
            arr[j+1][i] = res[k][1]

            j+=2
            k+=1
        for m in range(l, r_size):
            arr[m][i] = 0


    return


# Initialize
r, c, k = map(int, input().split())
arr = [[0] * 100 for _ in range(100)]
r_size = 3
c_size = 3
answer = 0

for i in range(3):
    v1, v2, v3 = map(int, input().split())
    arr[i][0] = v1
    arr[i][1] = v2
    arr[i][2] = v3

while True:
    if arr[r - 1][c - 1] == k or answer >= 101:
        break

    if r_size >= c_size:
        operate_R()
    else:
        operate_C()

    answer += 1

print(answer) if answer <= 100 else print(-1)