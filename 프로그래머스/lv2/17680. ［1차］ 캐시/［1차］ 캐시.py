class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class LinkedList:
    def __init__(self):
        self.head = None

    def insertFirst(self, data):
        new_node = Node(data)
        new_node.next = self.head
        self.head = new_node

    def isExist(self, data, cacheSize):
        cur = self.head
        before = self.head
        idx = 1
        while cur and idx <= cacheSize:
            # 데이터를 찾았다면 해당 노드를 가장 앞으로 이동시켜준다.
            if cur.data == data:
                if idx == 1 : return True
                before.next = cur.next
                cur.next = self.head
                self.head = cur
                return True
            # 계속 탐색
            else:
                before = cur
                cur = cur.next
                idx += 1

        # 만약에 cacheSize를 넘어가거나, 리스트에 없다면 앞에 추가해준다.
        self.insertFirst(data)
        return False

def solution(cacheSize, cities):
    
    # LRU => 캐시 용량이 다 찼을때, 가장 오랫동안 사용하지 않은 친구를 교체한다. 
    lss = LinkedList()
    answer = 0
    
    for city in cities:
        # 모두 소문자로 바꿔준다.
        if lss.isExist(city.lower(), cacheSize):
            answer += 1
        else:
            answer += 5

    return answer