def solution(enroll, referral, seller, amount):
    
    recommender = {} # 추천인
    income = {} # 판매원들 수익 
    
    # 추천인 및 수익 초기화
    for i in range(len(enroll)):
        recommender[enroll[i]] = referral[i]
        income[enroll[i]] = 0
    
    # 수익 계산
    for i in range(len(seller)):
        employee = seller[i]
        money = amount[i] * 100
        
        # 부모까지 찾아 올라가며 수익 처리
        while True:
            ten_percent = int(money * 0.1)
            
            # 10프로 이하라면 상위로 올려줄 필요 없다.
            if ten_percent < 1:
                income[employee] += money
                break
            
            income[employee] += (money - ten_percent)
            
            # 부모가 민호라면 break
            if recommender[employee] == "-":
                break
            
            employee = recommender[employee]
            money = ten_percent

    return list(income.values())