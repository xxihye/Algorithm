package list;

public class MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 != null) return l2;
		else if (l2 == null && l1 != null) return l1;
		else if (l1 == null & l2 == null) return null;

		ListNode sl = l1.val <= l2.val ? l1 : l2;
		if (sl == l1)
			sl.next = l1.next != null ? mergeTwoLists(l1.next, l2) : mergeTwoLists(null, l2);
		else
			sl.next = l2.next != null ? mergeTwoLists(l1, l2.next) : mergeTwoLists(l1, null);

		return sl;
	}
}
