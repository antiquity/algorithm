import java.util.*;
public class LRUCache {
    class Node{
        int key,val;
        int idx;
        Node next,prev;
        Node(Object prev){
            val=-1;
            this.prev=(Node)prev; next=null; idx=-1;
        }
        Node(int key, int val){
            this.key=key; this.val=val; idx=-1;
            next=null; prev=null;
        }
        public String toString(){
            return String.format(" (%d,%d) ",key,val);
        }
    }
    Node head, tail;
    List<Node>[] keys;
    public LRUCache(int capacity) {
        keys=new List[capacity];
        keys[0]=new ArrayList<Node>();
        head=new Node(0,-1);
        keys[0].add(head); head.idx=0;
        for(int i=1; i<capacity; i++){
            keys[i]=new ArrayList<Node>();
            keys[i].add(new Node(keys[i-1].get(0)));
            keys[i-1].get(0).next=keys[i].get(0);
            keys[i].get(0).idx=i;
        }
        tail=keys[capacity-1].get(0);
    }
    public String toString(){
        String ret="";
        Node temp=head;
        while(temp!=null){
            ret+=String.format(" (%d,%d) ",temp.key,temp.val);
            temp=temp.next;
        }
        ret+="$$";
        for(List<Node> tt:keys) ret+=String.format("%s",tt);
        ret+="$$";
        ret+=head;
        ret+=tail;
        return ret;
    }
    void enhead(Node i){
        if(i!=head){
            if(i==tail) tail=i.prev;
            else i.next.prev=i.prev;
            i.prev.next=i.next;
            i.next=head; i.prev=null;
            head.prev=i; head=i;
        }
    }
    public int get(int key) {
        int idx=(int)((long)key*(key+3)%keys.length);
        for(Node i:keys[idx]) if(i.key==key && i.val>=0){
            enhead(i);
            return i.val;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        int idx=(int)((long)key*(key+3)%keys.length);
        for(Node i:keys[idx]) if(i.key==key && i.val>=0){
            i.val=value;
            enhead(i);
            return;
        }
        Node i=tail;
        keys[i.idx].remove(i);
        keys[idx].add(i);
        i.key=key; i.val=value; i.idx=idx;
        enhead(i);
    }
    public static void main(String[] args){
        LRUCache test = new LRUCache(2);
        System.out.println(test);
        test.set(2,1);
        System.out.println(test);
        test.set(1,1);
        System.out.println(test);
        System.out.println(test.get(2));
        System.out.println(test);
        test.set(4,1);
        System.out.println(test);
        System.out.println(test.get(1));
        System.out.println(test);
        System.out.println(test.get(2));
        System.out.println(test);
    }
}
