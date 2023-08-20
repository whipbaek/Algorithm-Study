def solution(players, callings):
    answer = []
    
    rank_p = {}
    rank_c = {}
    cnt = 1
    for p in players:
        rank_p[p] = cnt
        rank_c[cnt] = p
        cnt += 1
    
    for cal in callings:
        # 불린사람
        chaser = cal # 불린 사람 이름
        chaser_rank = rank_p[chaser] # 불린 사람의 현재 순위
        
        header_rank = chaser_rank - 1 # 앞선 사람의 순위
        header =  rank_c[chaser_rank-1] # 앞선 사람의 이름
        
        del rank_p[chaser]
        del rank_p[header]
        
        del rank_c[chaser_rank]
        del rank_c[header_rank]
        
        # 뒤의 사람은 한 칸 앞으로 간다.
        rank_c[header_rank] = chaser
        rank_p[chaser] = header_rank
        
        # 앞의 사람은 한 칸 뒤로 간다.
        rank_c[chaser_rank] = header
        rank_p[header] = chaser_rank
    for i in range(1, cnt):
        answer.append(rank_c[i])
        
    return answer