//주어진 N(2<= N <=100)개의 수를 순서대로 Linked List에 넣은 후, 2개의 간격으로 하나씩 데이터를 뺄 때 마지막에 남아 있는 데이터를 출력하시오.
//Ex) 1 2 3 4 5 -> 2 3 4 5 -> 2 3 5 -> 2 5 -> 2

//입력
//2 //테스트 케이스 수
//5 //입력 수
//1 2 3 4 5 //입력 데이터
//6
//1 2 3 4 5 6

//출력
//#1 2
//#2 5





import java.util.Scanner;
 
 
class ListNode {
    int data;
    ListNode prev;
    ListNode next;
     
    public ListNode()
    {
        data = 0;
        prev = this;
        next = this;
    }
 
    public static ListNode appendListNode(ListNode head, int data)
    {
        ListNode node = new ListNode();//마지막으로 추가되는 노드
        node.data = data;
        if (head == null)
        {
            head = node;
        }
        else
        {
            ListNode last = head.prev;//가장 끝 노드를 찾는 것
                                       //원형이니까 head의 prev면 가장 끝 노드
                                       
            last.next = node;//원래 last.next가 head노드를 가리키고있는데 새로 들어온 가장 마지막노드를가리키게함
            
            head.prev = node;//head.prev가 원래 last 노드를 가리키고있는데 이제 새로 //들어오는 마지막 노드를 가리켜야 하므로
                             //마지막 노드를 가리키게 하는 것
                             
            node.prev = last;//밑에 두줄로 원형을 완성시킴
            node.next = head;
        }
        return head;
    }
     
    public static ListNode removeListNode(ListNode head, ListNode node)
    {
        if (head == head.next)
        {
            return null;
        }
        else
        {
            ListNode prevNode = node.prev;
            ListNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            return (head == node) ? nextNode : head;
        }
         
    }
}
     
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            ListNode head = null;
            int N = sc.nextInt();
            for (int i = 0; i < N; i++)
            {
                int data = sc.nextInt();
                head = ListNode.appendListNode(head, data);
            }
            ListNode node = head;
            while(head != head.next)
            {
                ListNode nextNode = node.next;
                head = ListNode.removeListNode(head, node);
                node = nextNode.next.next;
            }
            System.out.printf("#%d %d\n", test_case, head.data);
        }
        sc.close();
    }
}
