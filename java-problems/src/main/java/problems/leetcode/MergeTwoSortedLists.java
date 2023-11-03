package problems.leetcode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 You are given the heads of two sorted linked lists list1 and list2.
 <p>
 Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 <p>
 Return the head of the merged linked list.
 <p>
 Example 1: <p>
 Input: list1 = [1,2,4], list2 = [1,3,4] <p>
 Output: [1,1,2,3,4,4]
 <p>
 Example 2:
 <p>
 Input: list1 = [], list2 = [] <p>
 Output: []
 <p>
 Example 3:
 <p>
 Input: list1 = [], list2 = [0] <p>
 Output: [0]
 <p>
 Constraints:
 <p>
 The number of nodes in both lists is in the range [0, 50]. <p>
 -100 <= Node.val <= 100 <p>
 Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {

        MergeTwoSortedLists test = new MergeTwoSortedLists();

        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(-10, l1);

        ListNode listNode = test.mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }


    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-100);
        ListNode cur = prehead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    private ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                list1.next = mergeTwoListsRecursive(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoListsRecursive(list1, list2.next);
                return list2;
            }
        }

        if (list1 == null) {
            return list2;
        }
        return list1;
    }
}

// Definition for singly-linked list.
@NoArgsConstructor
@AllArgsConstructor
class ListNode {

    int val;

    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

