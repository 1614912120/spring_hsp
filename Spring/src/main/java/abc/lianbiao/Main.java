package abc.lianbiao;

public class Main {
    //初始化链表
    public static void main(String[] args) {
        ListNode n0 = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(5);
        n0.next = n1;
        n1.next= n2;
        n2.next=n3;
        n3.next = n4;
    }

    void insert(ListNode n0,ListNode p) {
        ListNode n1 = n0.next;
        p.next= n1;
        n0.next = p;
    }

    void delete(ListNode n0) {
        if(n0.next == null) {
            return;
        }
        ListNode p = n0.next;
        ListNode n1 = p.next;
        n0.next = n1;
    }

    //访问节点
    ListNode access(ListNode head, int index) {
        for (int i = 0; i < index; i++) {
            if(head == null) {
                return null;
            }
            head = head.next;
        }
        return head;
    }

    int find(ListNode head,int target) {
        int index= 0;
        while (head!=null) {
            if(head.val==target) {
                return index;
            }
            head = head.next;
            index++;
        }
        return -1;
    }
}
//链表节点类
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}



