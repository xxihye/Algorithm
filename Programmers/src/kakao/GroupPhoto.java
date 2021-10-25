package kakao;

public class GroupPhoto {
    static String[] conditions;
    static int res;
    static boolean[] select = new boolean[8];
    static char[] line = new char[8];
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'}; 
    
    public int solution(int num, String[] data) {
        conditions = data;
        res = 0;
        permutation(0);
        return res;
    }
    
    public void permutation(int col){
        if(col == 8){
            if(checkCondition()) res++;
            return;
        }
        
        for(int i=0; i<8; i++){
            if(select[i]) continue;
            
            select[i] = true;
            line[col] = friends[i];
            permutation(col+1);
            select[i] = false;
        }
    }
    
    public boolean checkCondition(){
        char f1, f2, op;
        int gap = 0, f1Idx = 0, f2Idx = 0;
        
        for(String s : conditions){
            f1 = s.charAt(0);
            f2 = s.charAt(2);
            op = s.charAt(3);
            gap = s.charAt(4) - '0';
            
            for(int j=0; j<line.length; j++){
                if(line[j] == f1) f1Idx = j;
                else if(line[j] == f2) f2Idx = j;
            }
            
            switch(op){
                case '=' :
                    if(Math.abs(f1Idx - f2Idx)-1 != gap) return false;
                    break;
                case '>' :
                    if(Math.abs(f1Idx - f2Idx)-1 <= gap) return false;
                    break;
                case '<' :
                    if(Math.abs(f1Idx - f2Idx)-1 >= gap) return false;
                 break;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
		GroupPhoto gp = new GroupPhoto();
		int num = 2;
		String[] data = {"N~F=0", "R~T>2"};
		System.out.println(gp.solution(num, data));
	}
}
