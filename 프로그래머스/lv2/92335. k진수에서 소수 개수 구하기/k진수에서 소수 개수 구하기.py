import math

def is_prime_number(x):
    for i in range(2, int(math.sqrt(x)) + 1):
        if x % i == 0:
            return False 
    return True

def solution(n, k):
    t_int = 0
    count = 1
    answer = 0

    while n!=0:
        t_int += n%k * count
        count *= 10
        n//=k
    res = str(t_int).split('0')

    for r in res:
        if r != '' and int(r) != 1 :
            if is_prime_number(int(r)) : 
                answer += 1
    return answer