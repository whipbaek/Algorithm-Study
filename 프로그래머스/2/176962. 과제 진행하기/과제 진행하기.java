import java.util.*;

class Report {
    public String name;
    public Integer time;
    public Integer use;
    
    public Report(String name, Integer time, Integer use){
        this.name = name;
        this.time = time;
        this.use = use;
    }
    
    @Override
    public String toString(){
        return name + " " + time;
    }
}

class Solution {
    public List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        
        int len = plans.length;
        List<Report> reports = new ArrayList<>();
        for(int i=0; i<len; i++){
            
            String[] stimes = plans[i][1].split(":");
            Integer time = Integer.parseInt(stimes[0] + stimes[1]);
                
            reports.add(new Report(
                plans[i][0],
                time,
                Integer.parseInt(plans[i][2])
            ));
        }
        
        // sort by time
        Collections.sort(reports, (o1 ,o2) -> {
            return o1.time - o2.time;
        });
        
        Stack<Report> st = new Stack<>();
        int targetIdx = 0;
        while(true){
            
            // size check
            if(targetIdx >= len) break;
            
            // get next report
            Report report = reports.get(targetIdx);
            
            int currentTime = report.time;
            boolean flag = false;
            
            while(targetIdx < len){
                
                // process report    
                report.use--;
                currentTime = calc(currentTime);
                
                // last report 
                if(targetIdx + 1 == len){
                    answer.add(report.name);
                    break;
                }
                
                if(report.use == 0){
                    answer.add(report.name);
                    flag = true;
                    break;
                }
                
                // check new report time
                if(currentTime == reports.get(targetIdx+1).time){
                    st.push(report);
                    break;
                }

            }
            targetIdx++;
            if(!flag || st.isEmpty()) continue;
            
            
            
            while(!st.isEmpty()){
                // check stack
                boolean sFlag = false;
                Report sReport = st.pop();  
                
                while(true){
                    if(currentTime == reports.get(targetIdx).time){
                        st.push(sReport);
                        sFlag = true;
                        break;
                    }
                    
                    sReport.use--;
                    currentTime = calc(currentTime);
                    
                    if(sReport.use == 0){
                        answer.add(sReport.name);
                        break;
                    }
                }
                
                // meet next report
                if(sFlag) break;
            }
        }
        
        while(!st.isEmpty()){
            answer.add(st.pop().name);
        }
            
        return answer;
    }
    
    public int calc(int time){
        if(time%100 == 59){
            return time+41;
        }
        
        return time+1;
    }
}