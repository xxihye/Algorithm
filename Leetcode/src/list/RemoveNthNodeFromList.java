package list;

import list.ListNode;

public class RemoveNthNodeFromList {
	 public ListNode removeNthFromEnd(ListNode head, int n) {
	        int length = 0;
	        ListNode pre = new ListNode(0, head);
	        ListNode temp = head;
	        
	        while(temp != null){
	            length++;
	            temp = temp.next;
	        }
	        
	        if(length == 1) return null;
	        
	        temp = pre;
	        int count = length - n;
	                    
	        while(count > 0){
	            count--;
	            temp = temp.next;
	        }
	        
	        temp.next = n == 1? null : temp.next.next;
	        
	        return pre.next;
	        
	    }
}


 

 