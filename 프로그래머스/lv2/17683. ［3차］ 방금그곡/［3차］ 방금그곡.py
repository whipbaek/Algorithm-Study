from datetime import datetime
import time

def parsing_M(m):
    m_arr = []
    i = 0
    while True:
        if i>= len(m): break

        if i+1 < len(m) and m[i+1] == '#':
            m_arr.append(m[i] + m[i+1])
            i += 2
            continue
        m_arr.append(m[i])
        i+=1
    return m_arr

def get_minutes(td):
    return (td.seconds//3600 * 60) + ((td.seconds//60)%60)

def solution(m, musicinfos):
    targetSong = '(None)'
    targetTime = -1
    
    for music in musicinfos:
        start, end, name, note = music.split(',')
        completeNote = ''
        runningTime = int(get_minutes(datetime.strptime(end, '%H:%M') - 
                                      datetime.strptime(start, '%H:%M')))
        
        m_arr = parsing_M(m)
        idx = 0
        for _ in range(runningTime):
            if idx + 1 < len(note) and note[idx+1] == '#':
                completeNote += (note[idx] + note[idx+1])
                idx += 2
            else:
                completeNote += note[idx]
                idx += 1
            if idx >= len(note): idx = 0
        
        print('completeNote:',completeNote)
        
        c_arr = parsing_M(completeNote)
        print('m_arr:',m_arr)
        print('c_arr:',c_arr)
        
        for i in range(len(c_arr)):
            if c_arr[i] == m_arr[0]:
                possible = True
                for j in range(len(m_arr)):
                    if i+j >= len(c_arr): 
                        possible = False
                        break
                    if c_arr[i+j] != m_arr[j]: 
                        possible = False
                        break
                
                if possible and runningTime > targetTime:
                    targetTime = runningTime
                    targetSong = name
                    
    return targetSong