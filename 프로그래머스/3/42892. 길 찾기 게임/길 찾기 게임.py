import sys
sys.setrecursionlimit(10**6)

class Node:
    def __init__(self, item):
        self.item = item
        self.left = None
        self.right = None


class BinaryTree:
    def __init__(self):
        self.root = None

tree = BinaryTree()

preres = []
postres = []
nodes = []

def preorder(node):
    global preres
    if node is not None:
        preres.append(node.item[2])
        if node.left:
            preorder(node.left)
        if node.right:
            preorder(node.right)

def postorder(node):
    global postres
    if node is not None:
        if node.left:
            postorder(node.left)
        if node.right:
            postorder(node.right)
        postres.append(node.item[2])

# 인덱스,
def setChild(parent, node):
    if parent.item[0] > node[0]:
        if parent.left:
            setChild(parent.left, node)
        else:
            parent.left = Node(node)
    else :
        if parent.right:
            setChild(parent.right, node)
        else:
            parent.right = Node(node)

def solution(nodeinfo):
    global tree, nodes
    answer = []

    # 번호 세팅 ([x, y, num])
    for i in range(len(nodeinfo)):
        nodeinfo[i].append(i + 1)
    # y축 좌표 내림차순으로 정렬
    nodes = sorted(nodeinfo, key=lambda x: (-x[1], x[0]))

    # 최상위 노드 설정 (y좌표가 가장 높은 노드)
    tree.root = Node(nodes[0])
    
    for node in nodes[1:]:
        setChild(tree.root, node)

    preorder(tree.root)
    postorder(tree.root)

    return [preres, postres]
