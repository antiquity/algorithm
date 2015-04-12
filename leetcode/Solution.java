import java.util.*;

public class Solution {
    public boolean isPalindrome(String s) {
        int n=s.length();
        int a=0, b=s.length()-1;
        s=s.toLowerCase();
        while(a<b){
            while(a<b && (!Character.isLetterOrDigit(s.charAt(a)))) a++;
            while(b>a && (!Character.isLetterOrDigit(s.charAt(b)))) b--;
            if(s.charAt(a)-s.charAt(b)!=0) return false;
            a++;
            b--;
        }
        return true;
    }
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<String>();
        char[] str = new char [2*n];
        int o=0, c = 0;
        int i = 0;
        while(i>=0){
            switch (str[i]) {
                case '(':
                    o--;
                    if(c<o){
                        str[i]=')'; c++; i++;
                    }else{
                        str[i]=0; i--;
                    }
                    break;
                case ')':
                    str[i]=0;
                    c--;
                    i--;
                    break;
                default:
                    if(o<n){
                        str[i]='('; o++; i++;
                    }else{
                        str[i]=')'; c++; i++;
                    }
                    break;
            }
            if(i>=2*n){
                ret.add(new String(str));
                i--;
            }
        }
        return ret;
    }
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
        public String toString(){
            return this.val+"->"+this.next;
        }
    }
    public ListNode rotateRight(ListNode head, int n) {
        ListNode curr = head;
        if(head==null) return null;
        int count=1;
        while(curr.next!=null){
            count++;
            curr=curr.next;
        }
        ListNode tail = curr, temp;
        curr = head;
        n=n%count;
        if(n==0) return head;
        for(int i=1; i<count-n; i++)
            curr=curr.next;
        temp=curr.next;
        curr.next=null;
        tail.next=head;
        return temp;
    }
    public ListNode deleteDuplicates(ListNode head) {
        Set set = new HashSet();
        if(head==null) return null;
        set.add(head.val);
        ListNode curr = head.next, pre=head;
        while(curr!=null){
            //System.out.println(set);
            if(set.contains(curr.val)){
                pre.next=curr.next;
                curr = curr.next;
            }else{
                set.add(curr.val);
                curr = curr.next;
                pre = pre.next;
            }
        }
        return head;
    }
    public boolean subIsScramble(String s1, String s2, boolean top) {
        int n = s1.length();
        //System.out.println("get ("+s1+", "+s2+")");
        if(n!=s2.length()) return false;
        //if(top && s1.equals(s2)) return false;
        if(n==1) return s1.equals(s2);
        boolean t1,t2;
        char a;
        int temp;
        for(int i=0; i<n-1; i++){
            t1=true; t2=true;
            for(int j=0; j<=i; j++){
                a=s1.charAt(j);
                temp=s2.indexOf(a,n-1-i);
                if(temp==-1) t1=false;
                temp=s2.indexOf(a);
                if(temp==-1 || temp>i) t2=false;
            }
            for(int j=i+1; j<n; j++){
                a=s1.charAt(j);
                temp=s2.indexOf(a);
                if(temp==-1 || temp>=n-i-1) t1=false;
                temp=s2.indexOf(a,i+1);
                if(temp==-1) t2=false;
            }
            if(t1 && subIsScramble(s1.substring(0,i+1),s2.substring(n-1-i,n),false) && subIsScramble(s1.substring(i+1,n),s2.substring(0,n-i-1),false))
                return true;
            if(t2 && subIsScramble(s1.substring(0,i+1),s2.substring(0,i+1),false) && subIsScramble(s1.substring(i+1,n),s2.substring(i+1,n),false))
                return true;
        }
        return false;
    }
    public boolean isScramble(String s1, String s2) {
        return subIsScramble(s1,s2,true);
    }
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int start=newInterval.start, end=newInterval.end;
        int i=0;
        for(i=0; i<intervals.size(); i++)
            if(intervals.get(i).start>newInterval.start){
                intervals.add(i,newInterval);
                break;
            }
        if(i==intervals.size()) intervals.add(newInterval);
        for(i=0; i<intervals.size()-1;){
            if(intervals.get(i).end>=intervals.get(i+1).start){
                intervals.get(i).end=Math.max(intervals.get(i).end,intervals.get(i+1).end);
                intervals.remove(i+1);
            }else
                i++;
        }
        return intervals;
    }
    public boolean isNumber(String s) {
        boolean good=true;
        try{
            s=s.trim();
            if(Character.isLetter(s.toLowerCase().charAt(s.length()-1))) return false;
        }catch(StringIndexOutOfBoundsException e){
            System.err.println(s);
        }
        try{
            long l = Long.parseLong(s);
            System.err.println("long: "+l);
        }catch(NumberFormatException e){ good=false; }
        if(good) return true;
        good=true;
        try{
           double d= Double.parseDouble(s);
            System.err.println("double: "+d);
        }catch(NumberFormatException e){ good=false; }
        if(good) return true;
        return false;
    }
    public String reverseWords(String s) {
        s=s.trim();
        int i=0,n=s.length(),j;
        String ret="";
        while(i<n){
            j=s.indexOf(' ',i);
            if(j!=-1){
                if(j==i){ i++; continue; }
                ret= s.substring(i,j)+" "+ret;
                i=j+1;
            }else{
                ret= s.substring(i,n)+" "+ret;
                i=n;
            }
        }
        return ret.trim();
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        int r=matrix.length,c=0;
        if(r>0) c=matrix[0].length;
        List<Integer> ret= new ArrayList<Integer>();
        int j=0,k=0,d=0;
        for(int i=0; i<r*c; i++){
            ret.add(matrix[j][k]);
            switch (d){
                case 0:
                    if(k<c-j-1) k++;
                    else{ j++; d++; }
                    break;
                case 1:
                    if(j<r-(c-k)) j++;
                    else{ k--; d++; }
                    break;
                case 2:
                    if(k>r-j-1) k--;
                    else{ j--; d++; }
                    break;
                case 3:
                    if(j>k+1) j--;
                    else{ k++; d=0; }
                    break;
            }

        }
        return ret;
    }

    // Definition for a point.
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
    class Rate {
        int x;
        int y;
        Rate(int a, int b) {
            x=a; y=b;
        }
        @Override
        public boolean equals(Object r){
            //System.out.println("equals called");
            if (r == null || r.getClass() != getClass()) { // << this is important
                return false;
            }
            return this.x*((Rate)r).y==this.y*((Rate)r).x;
        }
        @Override
        public int hashCode(){
            return y==0 ? (x==0 ? 1 : Integer.MAX_VALUE) : x/y;
        }
        public String toString(){
            return "("+this.x+","+this.y+")";
        }
    }
    public int maxPoints(Point[] points) {
        Map<Rate,Integer> map = new HashMap<Rate,Integer>();
        Rate key;
        if(points.length<=2) return points.length;
        int ret=2;
        int overlap=1;
        for(int i=0; i<points.length-1; i++){
            map.clear();
            overlap = 1;
            for(int j=i+1; j<points.length; j++){
                key = new Rate(points[i].x-points[j].x,points[i].y-points[j].y);
                if(key.x==0 && key.y==0){
                    overlap++;
                }else{
                    if(map.containsKey(key)){
                        map.put(key,map.get(key)+1);
                    }else
                        map.put(key,1);
                }
            }
            //System.out.println("i="+i+": "+map);
            if(map.size()>0)
                ret=Math.max(Collections.max(map.values())+overlap,ret);
            else
                ret=Math.max(ret,overlap);
        }
        return ret;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        int mid =0;
        while(inorder[mid]!=preorder[0]) mid++;
        root.left=buildTree(Arrays.copyOfRange(preorder,1,mid+1),Arrays.copyOfRange(inorder,0,mid));
        root.right=buildTree(Arrays.copyOfRange(preorder,mid+1,preorder.length),Arrays.copyOfRange(inorder,mid+1,inorder.length));
        return root;
    }
    public void rotate(int[] nums, int k) {
        int j=0, temp=nums[0],p,n=nums.length;
        k=k%n;
        int m=0;
        for(int i=0; i<nums.length; i++, m=p){
            p=(m+n-k)%n;
            if(p==j){
                nums[m]=temp;
                p=(m+1)%n;
                temp=nums[p];
                j=p;
            }else{
                nums[m]=nums[p];
            }
        }

    }
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return head;
        ListNode curr=head;
        boolean same=false;
        while(head.next!=null){
            if(head.val==head.next.val){
                same=true;
                head=head.next;
            }else if(same){
                same=false;
                head=head.next;
            }else break;
        }
        if(same) return null;
        if(head.next==null) return head;
        ListNode pre=head;
        curr=head.next;
        while(curr.next!=null){
            if(curr.val==curr.next.val){
                curr.next=curr.next.next;
                same=true;
            }else if(same){
                pre.next=curr.next;
                curr=curr.next;
                same=false;
            }else{
                pre=curr;
                curr=curr.next;
            }
        }
        if(same) pre.next=null;
        return head;
    }
    public static void main(String[] args){
        Solution test = new Solution();
        int[][] rpoints ={{-435,-347},{-435,-347},{609,613},{-348,-267},{-174,-107},{87,133},{-87,-27},{-609,-507},{435,453},{-870,-747},{-783,-667},{0,53},{-174,-107},{783,773},{-261,-187},{-609,-507},{-261,-187},{-87,-27},{87,133},{783,773},{-783,-667},{-609,-507},{-435,-347},{783,773},{-870,-747},{87,133},{87,133},{870,853},{696,693},{0,53},{174,213},{-783,-667},{-609,-507},{261,293},{435,453},{261,293},{435,453}};
        Point[] points= new Point[rpoints.length];
        for(int i=0; i<points.length; i++) points[i]=test.new Point(rpoints[i][0],rpoints[i][1]);
        System.out.println(test.maxPoints(points));
        System.out.println(test.maxPoints(new Point[]{test.new Point(0,0),test.new Point(2,2),test.new Point(0,0)}));
        System.out.println(test.spiralOrder(new int[][]{}));
        System.out.println(test.spiralOrder(new int[][]{{1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18},{19,20,21,22,23,24}}));
        System.out.println(test.reverseWords("  I am a good   man.")+"|");
        System.out.println(test.isNumber("  3.  "));
        System.out.println(test.isNumber("84656e656D"));
        System.out.println(test.isScramble("rgtae","great"));
        ListNode head = test.new ListNode(5);
        head.next = test.new ListNode(5);
        System.out.println(test.deleteDuplicates(head));
        System.out.println(test.rotateRight(test.new ListNode(1),0));
        System.out.println(test.generateParenthesis(4));
        System.out.println(test.isPalindrome("aA"));
    }
}
