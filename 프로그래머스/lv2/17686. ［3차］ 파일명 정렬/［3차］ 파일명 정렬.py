def solution(files):

    nums = ['0','1','2','3','4','5','6','7','8','9']
    re_files = []
    for file in files:
        l = len(file)
        i = 0
        while True:
            if '0' <= file[i] <='9':
                break
            i+= 1

        head = [str.lower(file[0:i]), file[0:i]]
        
        j = i
        while j < l:
            if file[j] not in nums:
                break
            j+=1

        padding_count = 10 - (j-i)
        padding = padding_count * '0'

        number = [padding+ file[i:j], file[i:j]]
        body = file[j:l]

        re_files.append([head, number, body])

    re_files = sorted(re_files, key = lambda x : (x[0][0], x[1][0]))
    answer = []
    for i in range(len(re_files)):
        answer.append(re_files[i][0][1] + re_files[i][1][1] + re_files[i][2])

    return answer
