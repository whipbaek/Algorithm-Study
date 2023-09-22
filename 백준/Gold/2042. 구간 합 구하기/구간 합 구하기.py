def merge(left, right):
    return left + right

def build(node, left, right):
    if left == right:
        stree[node] = nums[left]
        return stree[node]

    mid = left + (right - left)//2
    left_val = build(2 * node, left, mid)
    right_val = build(2 * node + 1, mid + 1, right)
    stree[node] = merge(left_val, right_val)
    return stree[node]

def update(idx, val, node, left, right):
    # 나의 담당구역 left~right 사이에 target이 없는 경우 => 기존 값을 그대로 반환(변경 필요 x)
    if idx < left or idx > right:
        return stree[node]

    # leaf node: 바꿀 idx 값 발견 => 값 변경
    if left == right:
        stree[node] = val
        return stree[node]

    mid = left + (right - left) // 2
    left_val = update(idx, val, 2*node, left, mid)
    right_val = update(idx, val, 2*node + 1, mid + 1, right)
    stree[node] = merge(left_val, right_val)
    return stree[node]


def query(start, end, node, left, right):
    # 나의 담당구역 left~right 사이에 start~end 가 아예 포함되지 않는 경우 => 가지 치기
    if end < left or start > right:
        return 0

    # 나의 담당구역 left~right 전체가 start~end 에 포함되는 경우 => 내 정보를 바로 리턴
    if start <= left and right <= end:
        return stree[node]

    mid = left + (right - left) // 2
    left_val = query(start, end, 2 * node, left, mid)
    right_val = query(start, end, 2 * node + 1, mid + 1, right)
    return merge(left_val, right_val)


n, m, k = map(int, input().split())
nums = []
for _ in range(n):
    v = int(input())
    nums.append(v)

stree = [0 for x in range(4 * len(nums))]
build(1, 0, n-1)


for _ in range(m+k):
    a, b, c = map(int, input().split())

    # 1이라면 b번째 수를 c로 바꾼다.
    if a == 1:
        update(b-1, c, 1, 0, n-1)
    # 2라면 구간합 구하기
    else:
        print(query(b-1, c-1, 1, 0, n-1))