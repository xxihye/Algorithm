package implement;

public class ZigZagConversion {
	 public String convert(String str, int numRows) {
	        if(numRows == 1) return str;        
			 StringBuffer result = new StringBuffer();
			 char[] s = str.toCharArray();

			 int gap = 0;
			 for(int i = 0; i<numRows; i++) {
				 boolean isClose = false;
				 int sum = 2 * numRows - 2;
				 for(int j = i; j <str.length(); j+= gap) {
					 if(2*i == 0 || 2*i == sum) gap = sum;
					 else {
						 gap = isClose ? 2*i : (sum - 2*i);
						 isClose = !isClose;
					 }
					 result.append(s[j]);
				 }
			 }
			 return result.toString();
	    }
}
