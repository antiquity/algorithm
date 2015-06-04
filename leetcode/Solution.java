import java.util.*;
import java.io.*;

public class Solution {
    PrintStream out=System.out;
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

    }
    public int findKthLargest(int[] nums, int k) {
        return findKthLargestSub(nums, k, new Random(), 0, nums.length);
    }
    private int findKthLargestSub(int[] nums, int k, Random rand, int l, int r){
        int s = nums[rand.nextInt(r-l)+l];
        int i=l,kk=l,j=r,temp;
        while(kk<j){
            if(nums[kk]<s){
                j--; temp=nums[kk]; nums[kk]=nums[j]; nums[j]=temp;
            }else if(nums[kk]>s){
                if(i==kk){ i++; kk++;}
                else{
                    temp=nums[kk]; nums[kk]=nums[i]; nums[i]=temp; i++;
                }
            }else kk++;
        }
        System.out.format("%5d: %s\n",s,Arrays.toString(Arrays.copyOfRange(nums,l,r)));
        if(i>=k) return findKthLargestSub(nums,k,rand,l,i);
        else if(j<k) return findKthLargestSub(nums,k,rand,j,r);
        else return s;
    }
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> ret = new ArrayList<String>();
        if(dict.isEmpty()) return  ret;
        dict = new HashSet(dict);
        int min=Integer.MAX_VALUE,max=0;
        for(String str : dict){
            min=Math.min(min,str.length());
            max=Math.max(max,str.length());
        }
        boolean valid=false;
        int maxJ=-1,maxW=0,i,j;

        Deque<Integer> bup = new ArrayDeque<Integer>();
        bup.push(0);   //out.format("%4d",0);
        bup.push(0);
        while(bup.size()>1){
            j=bup.pop()+1;
            i=bup.peek();
            for(j=Math.max(j,i+min); j<=(i+max) && j<=s.length(); j++)
                if(dict.contains(s.substring(i,j))){
                    if(j!=s.length()){
                        bup.push(j); //out.format("%4d",j);
                        bup.push(j);
                    }else{
                        wordBreakSub(ret,bup,s);
                        valid=true;
                    }
                    if(j>maxJ){ maxJ=j; maxW=bup.size(); }
                    break;
                }else{
                    if(!valid && j==maxJ+1 && (maxW-bup.size())*min>max){
                        bup.clear();
                        break;
                    }
                }
        }
        return ret;
    }
    private void wordBreakSub(List<String> ret, Deque<Integer> bup, String s){
        StringBuilder xx = new StringBuilder();
        Iterator<Integer> itr=bup.descendingIterator();
        int i=0,j;
        if(itr.hasNext()) i=itr.next();
        while(itr.hasNext()){
            j=itr.next();
            xx.append(s.substring(i,j)+" ");
            i=j;
        }
        xx.append(s.substring(i));
        ret.add(xx.toString());
    }
    public String shortestPalindrome(String s) {
        int N=s.length();
        int c=0;
        List<Integer> l = new ArrayList<Integer>();
        for(int i=0; i<N-1; i++)
            if(s.charAt(i)!=s.charAt(i+1)) l.add(i);
outerc:
        for(c=N; c>=1; c--){
            for(int i:l)
                if(i<c){
                   if(s.charAt(i)!=s.charAt(c-1-i)) continue outerc;
                }else break;
            for(int i=0; i<c-1-i; i++)
                if(s.charAt(i)!=s.charAt(c-1-i))
                    continue outerc;
            break;
        }
        StringBuilder ret = new StringBuilder((c>=N)?"":s.substring(c));
        ret.reverse();
        return ret.toString()+s;
    }
    public void solve(char[][] board) {
        int r=board.length,c;
        if(r==0) return;
        c=board[0].length;
        boolean[][] visited=new boolean[r][c];
        for(boolean[] xx : visited) Arrays.fill(xx,false);
        Queue<Integer> q = new ArrayDeque<Integer>();
        int temp;
        for(int i=0; i<r; i++){
            if(i==0 || i==r-1) temp=1; else temp=c-1;
            for(int j=0; j<c; j+=temp) if(board[i][j]=='O' && !visited[i][j]){
                q.clear(); q.offer(i); q.offer(j);
                while(q.size()>0){
                    int ii=q.poll(), jj=q.poll();
                    if(ii<0 || ii>=r || jj<0 || jj>=c || visited[ii][jj] || board[ii][jj]=='X'){ continue; }
                    visited[ii][jj]=true;
                    q.offer(ii-1); q.offer(jj);
                    q.offer(ii); q.offer(jj-1);
                    q.offer(ii+1); q.offer(jj);
                    q.offer(ii); q.offer(jj+1);
                }
            }
        }
//              for(boolean[] x:visited){
//                  for(boolean xy:x) out.print(xy?1:0);
//                  out.println();
//              }
        for(int i=0; i<r; i++)
            for(int j=0; j<c; j++) if(board[i][j]=='O' && !visited[i][j]){
//              for(char[] x:board) out.println(new String(x));
                q.clear(); q.offer(i); q.offer(j);
                while(q.size()>0){
                    int ii=q.poll(), jj=q.poll();
                    if(ii<0 || ii>=r || jj<0 || jj>=c || board[ii][jj]=='X') continue;
                    board[ii][jj]='X';
                    q.offer(ii-1); q.offer(jj);
                    q.offer(ii); q.offer(jj-1);
                    q.offer(ii+1); q.offer(jj);
                    q.offer(ii); q.offer(jj+1);
                }
            }
    }
    public void solve(String[] str) {
        char[][] board= new char[str.length][str[0].length()];
        for(int i=0; i<str.length; i++) board[i]=str[i].toCharArray();
        solve(board);
    }
    public int minDistance(String w1, String w2) {
        int M=w1.length(), N=w2.length();
        int[][] dp = new int[M+1][N+1];
        int ret;
        for(int j=0; j<=N; j++) dp[0][j]=j;
        for(int i=1; i<=M; i++){
            dp[i][0]=i;
            for(int j=1; j<=N; j++){
                ret=dp[i-1][j-1];
                if(w1.charAt(i-1)!=w2.charAt(j-1)) ret++;
                ret=Math.min(ret,1+dp[i-1][j]);
                ret=Math.min(ret,1+dp[i][j-1]);
                dp[i][j]=ret;
            }
        }
        return dp[M][N];
    }
    public boolean isMatch(String s, String p) {
        return true;
    }
    public int compareVersion(String v1, String v2) {
        String[] a1=v1.trim().split("\\.");
        String[] a2=v2.trim().split("\\.");
        int n = Math.max(a1.length, a2.length);
        for(int i=0; i<n; i++){
            long a,b;
            if(i<a1.length) a=Long.parseLong(a1[i]); else a=0;
            if(i<a2.length) b=Long.parseLong(a2[i]); else b=0;
            if(a>b) return 1;
            else if(a<b) return -1;
        }
        return 0;
    }
    boolean diff(String a, String b){
        if(a.length()!=b.length()) return false;
        boolean ret=false;
        for(int i=0; i<a.length(); i++)
            if(a.charAt(i)!=b.charAt(i))
                if(!ret) ret=true;
                else{
                    ret=false;
                    break;
                }
        return ret;
    }
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> route;
        String[] dic = dict.toArray(new String[0]);
        int N = dic.length;
        List<List<Integer>> map = new ArrayList<List<Integer>>();
        for(int i=0; i<N; i++){
            List<Integer> temp=new ArrayList<Integer>();
            for(int j=0; j<N; j++) if(diff(dic[i],dic[j]))
                temp.add(j);
            map.add(temp);
        }
        int st=0, en=0;
        for(int i=0; i<N; i++) if(dic[i].equals(start)){
            st=i; break;
        }
        for(int i=0; i<N; i++) if(dic[i].equals(end)){
            en=i; break;
        }
        //      out.println(Arrays.toString(dic));
        //      out.println("from "+start +"  ->  "+end);
        //      out.println("from "+st +"  ->  "+en);

        List<Integer> q = new ArrayList<Integer>();
        int[] visited = new int[N];
        List<Integer>[] prev = new ArrayList[N];
        Arrays.fill(visited,Integer.MAX_VALUE);
        for(int i=0; i<N; i++) prev[i]=new ArrayList<Integer>();
        q.add(st); visited[st]=0; visited[en]=Integer.MAX_VALUE;
        int i=0, temp;
        while(i<q.size()){
            //            out.format("i=%d\n",i);
            //            out.println(q);
            //            out.println(Arrays.toString(visited));
            //            out.println(Arrays.toString(prev));
            temp=q.get(i);
            if(visited[temp]>=visited[en]) break;
            for(int k:map.get(temp)) if(visited[k]>=visited[temp]+1){
                visited[k]=visited[temp]+1;
                if(!prev[k].contains(temp)) prev[k].add(temp);
                if(k!=en) q.add(k);
            }
            i++;
        }

        out.println(Arrays.toString(prev));
        route = new LinkedList<String>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(en); stack.push(-1);
        while(!stack.isEmpty()){
            i=stack.pop()+1; temp=stack.pop();
            if(prev[temp].size()>i){
                route.add(0,dic[temp]);
                stack.push(temp); stack.push(i);
                stack.push(prev[temp].get(i)); stack.push(-1);
            }else{
                if(i==0 && temp==st){
                    route.add(0,dic[temp]);
                    ret.add(route);
                    route = new LinkedList<String>(route);
                    route.remove(0);
                }
                if(!route.isEmpty())route.remove(0);
            }
            //          out.println(route);
            //      for(List<String> aa : ret) out.println(aa);
        }
        for(List<String> aa : ret) out.println(aa);
        return ret;
    }
    String fractionToDecimal(int num, int den) {
        String ret="";
        if((long)num*den<0)  ret+="-";
        long numerator=Math.abs((long)num);
        long denominator=Math.abs((long)den);
        long a = numerator/denominator;
        long b = numerator%denominator;
        List<Long> l = new ArrayList<Long>();
        while(b!=0){
            //out.println(numerator+" "+denominator+" "+l);
            b*=10;
            if(l.contains(b))
                break;
            else{
                l.add(b);
                b%=denominator;
            }
        }
        ret+=String.valueOf(a);
        if(l.size()>0) ret+='.';
        for(Long i:l){
            if(i==b) ret+="(";
            ret+=String.valueOf(i/denominator);
        }
        if(ret.indexOf('(')!=-1) ret+=')';
        return ret;
    }
    public int maximalRectangle(char[][] matrix) {
        int ret=0, r=matrix.length, c=0;
        if(r>0) c=matrix[0].length;
        int k,l,kk,ll,ii,jj;
        for(int i=0; i<r; i++) for(int j=0; j<c; j++) if(matrix[i][j]=='1'){
            k=r-1; l=c-1;
            ii=i; jj=j;
            //out.format("i=%d, j=%d, ii=%d, jj=%d, k=%d, l=%d\n",i,j,ii,jj,k,l);
outer:
            while(k>=i){
                for(ll=jj; ll<=l; ll++){
                    for(kk=ii; kk<=k; kk++)
                        if(matrix[kk][ll]=='0'){
                            ret=Math.max(ret,(k-i+1)*(ll-j));
                            k=kk-1;
                            jj=ll;
                            continue outer;
                        }
                }
                ret=Math.max(ret,(k-i+1)*(l-j+1));
                break;
            }

        }
        return ret;
    }
    public int largestRectangleArea(int[] height) {
        Deque<Integer> s = new ArrayDeque<Integer>();
        int ret=0,h;
        for(int i=0; i<height.length; i++){
            if(!s.isEmpty() && height[s.peekFirst()]>=height[i])
                while(!s.isEmpty() && height[s.peekFirst()]>=height[i]){
                    h=height[s.removeFirst()];
                    ret=Math.max(ret,h*(s.isEmpty() ? i : i-s.peekFirst()-1));
                }
            s.addFirst(i);
            //System.out.println(s);
        }
        while(!s.isEmpty()){
            h=height[s.removeFirst()];
            ret=Math.max(ret,h*(s.isEmpty() ? height.length : height.length-s.peekFirst()-1));
        }
        return ret;
    }
    public int romanToInt(String s) {
        int ret=0,i=0,temp,N=s.length();
        s.toLowerCase();
        while(i<N){
            temp=0;
            switch (s.charAt(i)){
                case 'M':
                    while(i<N && s.charAt(i)=='M'){ i++; temp+=1000; }
                    break;
                case 'C':
                    while(i<N && s.charAt(i)=='C'){ i++; temp+=100; }
                case 'D':
                    if(i<N && s.charAt(i)=='D'){ temp=500-temp; i++; }
                    while(i<N && s.charAt(i)=='C'){ i++; temp+=100; }
                    if(i<N && s.charAt(i)=='M'){ temp=1000-temp; i++; }
                    break;
                case 'X':
                    while(i<N && s.charAt(i)=='X'){ i++; temp+=10; }
                case 'L':
                    if(i<N && s.charAt(i)=='L'){ temp=50-temp; i++; }
                    while(i<N && s.charAt(i)=='X'){ i++; temp+=10; }
                    if(i<N && s.charAt(i)=='C'){ temp=100-temp; i++; }
                    break;
                case 'I':
                    while(i<N && s.charAt(i)=='I'){ i++; temp+=1; }
                case 'V':
                    if(i<N && s.charAt(i)=='V'){ temp=5-temp; i++; }
                    while(i<N && s.charAt(i)=='I'){ i++; temp+=1; }
                    if(i<N && s.charAt(i)=='X'){ temp=10-temp; i++; }
                    break;
            }
            ret+=temp;
        }
        return  ret;
    }
    public String intToRoman(int num) {
        String ret="";
        ret+=toRoman(num/1000,'M','?','&');
        ret+=toRoman((num%1000)/100,'C','D','M');
        ret+=toRoman((num%100)/10,'X','L','C');
        ret+=toRoman((num%10),'I','V','X');
        return ret;
    }
    String toRoman(int x, char one, char five, char ten){
        String ret="";
        if(x==0) return "";
        else if(x<=3)
            for(int i=0; i<x; i++) ret+=one;
        else if(x==4)
            ret=ret+one+five;
        else if(x<=8){
            ret+=five;
            for(int i=0; i<x-5; i++) ret+=one;
        }else ret=ret+one+ten;
        return ret;
    }
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
    /**
     * Definition for binary tree*/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isBalanced(TreeNode root) {
        return solve(root,0)>=0;
    }
    int solve(TreeNode root, int l){
        if(root==null) return l;
        int left, right;
        left=solve(root.left,l+1);
        if(left>=0){
            right=solve(root.right,l+1);
            if(right>=0 && Math.abs(left-right)<=1)
                return Math.max(left,right);
            else
                return -1;
        }else return -1;
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
    public ListNode deleteDuplicatesII(ListNode head) {
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
        System.out.println(test.findKthLargest(new int[]{1,3,5,3,2,1,5,6,7,8,98,9,4,54,323,542,452,4,54,265,7,68,3456,345,6,53,456,452,345,2,346,7,53,56,3456},12));
        System.out.println(test.wordBreak("a", new HashSet<String>(Arrays.asList(new String[]{}))));
        System.out.println(test.shortestPalindrome("abcd"));
        test.solve(new String[]{"OOOOXX","OOOOOO","OXOXOO","OXOOXO","OXOXOO","OXOOOO"});
        System.out.println("minDistance"+test.minDistance("dini","ainia"));
        System.out.println("minDistance"+test.minDistance("dinitrophenylhydrazine","acetylphenylhydrazine"));
        test.compareVersion("341.343","43.13");
        test.findLadders("hot", "dog", new HashSet<String>(Arrays.asList(new String[]{"hot","dog"})));
        //test.findLadders("hot", "dog", new HashSet<String>(Arrays.asList(new String[]{"hot","cog","dog","tot","hog","hop","pot","dot"})));
        System.out.println(test.fractionToDecimal(-1,-2147483648));
        System.out.println(test.maximalRectangle(new char[][]{{'1'}}));
        System.out.println(test.maximalRectangle(new char[][]{{'0','1'},{'1','0'}}));
        System.out.println(test.largestRectangleArea(new int[]{1,1}));
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
