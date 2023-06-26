from sys import stdin

n = int(input())
a = list(map(int, stdin.readline().rstrip().split()))
b, c = map(int, input().split())

# 초기화
d = [-1] * 1000001
person = 1

# 총 감독관 한명으로 커버 가능할때까지
while True:
    if person <= b:
        d[person] = 1
    else:
        break
    person +=1

# person ~ person+b 명까지는 부감독이 1명 추가로 필요하다.
while True:
    val = d[person - 1]
    if person+c > 1000000:
        for i in range(person, 1000001):
            d[i] = val+1
        break
    for i in range(person, person+c):
        d[i] = val+1
    person += c

answer = 0
for val in a:
    answer += d[val]
print(answer)