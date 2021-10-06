package weekly;

public class MutualEvaluation {
	
	//i행은 i번 학생이 평가한 점수들...
    //j열은 j열 학생이 받은 점수들
    public String solution(int[][] scores) {
    
    	//j번째 학생이 받은 총 점수 계산
        //j번째 학생이 자신에게 준 점수를 저장
        //j번째 학생이 받은 점수들 중 최저, 최고를 저장
        //본인에게 최저, 최고 점수를 줬다면 총 점수에서 빼기
        //평균을 저장
        int r = scores.length, c = scores[0].length;
        double[] avgs = new double[r];
        
        for(int j=0; j<c; j++){
            double sum = 0; //j번째가 받은 총 점수
            
            int min = 101, max = -1;
            //자기 자신에게 준 점수를 제외한 최대, 최소 점수를 저장
            for(int i=0; i<r; i++) {
                if(i != j){
                    if(scores[i][j] < min) min = scores[i][j];
                    if(scores[i][j] > max) max = scores[i][j];
                }                
                sum += scores[i][j];
            }
            
            double n = scores.length;
            //본인에게 준 점수를 제외한 최대 최소보다 크거나 작으면
            //중복되지 않은 진짜 최대, 최소 점수이므로 제외함
            if(scores[j][j] < min || scores[j][j] > max){
                sum -= scores[j][j];
                n--;
            }
            avgs[j] = sum / n;
        }
        
        StringBuilder sb = new StringBuilder();
        for(double d : avgs){
            int grade = (int) d / 10;
            switch(grade){
                case 9 : 
                    sb.append('A');    
                    break;
                case 8 : 
                    sb.append('B');    
                    break;
                case 7 : 
                    sb.append('C');    
                    break;
                case 5 : 
                	sb.append('D');
                	break;
                case 6 : 
                	sb.append('D');
                	break;
                default : 
                    sb.append('F');    
                    break;
            }
        }
        
        return sb.toString();
    }
}
