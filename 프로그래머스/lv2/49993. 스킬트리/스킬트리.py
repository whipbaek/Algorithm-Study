'''
스킬트리를 배우기 위해서는
C => 아무것도 안배워도 됨
B => C를 배워야 함
D => B를 배워야 함

BACDE 에 대한 맵을 하나 만든다.

B를 확인 => 기존맵[B] = C => 나는 C를 배웠는가?
만약 Key로 존재하지 않으면 그냥 OK 시키면 됨
'''

def solution(skill, skill_trees):
    answer = 0
    tech = {}
    for i in range(1, len(skill)):
        tech[skill[i]] = skill[i-1]
        
    for sk in skill_trees: # 스킬셋 목록
        possible = True
        learned = set()
        for s in sk: # 지금 배울려는 스킬
            if s in tech: #만약 테크트리에 존재하는 스킬이라면
                if tech[s] not in learned : #선행스킬을 배우지 않았다면
                    possible = False
                    break
            learned.add(s)
            
        if possible: 
            answer +=1
    return answer