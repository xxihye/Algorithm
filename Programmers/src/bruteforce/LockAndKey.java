package bruteforce;

import java.util.Arrays;

public class LockAndKey {
	
	public static void main(String[] args) {
		LockAndKey lk = new LockAndKey();
		int[][] key = {{0, 0, 0},{1, 0, 0},{0, 1, 1}};
		int[][] lock = {{1,1,1},{1,1,0},{1,0,1}};
		
		System.out.println(lk.solution(key, lock));
	}
	
	  int[][] arr; //lock을 가운데에 고정시킬 큰 배열
	    int kl, ll;
	    public boolean solution(int[][] key, int[][] lock) {
	        kl = key.length;
	        ll = lock.length;
	        
	        arr = new int[(2*kl)+ll-2][(2*kl)+ll-2];
	        
	        //lock을 가운데에 고정
	        int cnt = 0;
	        for(int i=kl-1; i<=kl+ll-2; i++) 
	            for(int j=kl-1; j<=kl+ll-2; j++)    
	                arr[i][j] = lock[i-kl+1][j-kl+1];
	        
	        // for 1 : 4번동안 90도씩 돌리기
	        // for 2 : key가 존재할 수 있는 위치에서 lock과 맞아떨어지는지 확인
	        for(int r=0; r<4; r++){
	            key = rotate(key);
	        
	            int[][] tempTable = new int[arr.length][arr.length];    
	            for(int i=0; i<kl+ll-1; i++){
	                for(int j=0; j<kl+ll-1; j++){
	                    for(int k=0; k<arr.length; k++) tempTable[k] = arr[k].clone();
	                    if(isMatch(i,j,key, tempTable)) return true;
	                }
	            }
	        }
	        return false;
	    }
	    
	    //(x, y 위치에서 시작)
	    public boolean isMatch(int x, int y, int[][] key, int[][] tempTable){
	        for(int i=0; i<key.length; i++){
	            for(int j=0; j<key.length; j++){
	                tempTable[x+i][y+j] += key[i][j];
	                if(tempTable[x+i][y+j] == 2) return false;
	            }
	        }
	        
	        for(int i=kl-1; i<=kl+ll-2; i++) 
	            for(int j=kl-1; j<=kl+ll-2; j++)    
	                if(tempTable[i][j] == 0) return false;
	            
	        
	        return true;
	    }
	    
	    
	    public int[][] rotate(int[][] key){
	        int kl = key.length;
	        int[][] newArr = new int[kl][kl];
	        
	        for(int i=0; i<kl; i++)
	            for(int j=0; j<kl; j++)
	                newArr[j][kl-i-1] = key[i][j];
	        
	        return newArr;
	    }
}