import java.util.*;
import java.io.*;

public class Solution {
    PrintStream out=System.out;
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
        test.findLadders("hot", "dog", new HashSet<String>(Arrays.asList(new String[]{"hot","dog"})));
        //test.findLadders("hot", "dog", new HashSet<String>(Arrays.asList(new String[]{"hot","cog","dog","tot","hog","hop","pot","dot"})));
        //test.findLadders("sand", "acne", new HashSet<String>(Arrays.asList(new String[]{"slit","bunk","wars","ping","viva","wynn","wows","irks","gang","pool","mock","fort","heel","send","ship","cols","alec","foal","nabs","gaze","giza","mays","dogs","karo","cums","jedi","webb","lend","mire","jose","catt","grow","toss","magi","leis","bead","kara","hoof","than","ires","baas","vein","kari","riga","oars","gags","thug","yawn","wive","view","germ","flab","july","tuck","rory","bean","feed","rhee","jeez","gobs","lath","desk","yoko","cute","zeus","thus","dims","link","dirt","mara","disc","limy","lewd","maud","duly","elsa","hart","rays","rues","camp","lack","okra","tome","math","plug","monk","orly","friz","hogs","yoda","poop","tick","plod","cloy","pees","imps","lead","pope","mall","frey","been","plea","poll","male","teak","soho","glob","bell","mary","hail","scan","yips","like","mull","kory","odor","byte","kaye","word","honk","asks","slid","hopi","toke","gore","flew","tins","mown","oise","hall","vega","sing","fool","boat","bobs","lain","soft","hard","rots","sees","apex","chan","told","woos","unit","scow","gilt","beef","jars","tyre","imus","neon","soap","dabs","rein","ovid","hose","husk","loll","asia","cope","tail","hazy","clad","lash","sags","moll","eddy","fuel","lift","flog","land","sigh","saks","sail","hook","visa","tier","maws","roeg","gila","eyes","noah","hypo","tore","eggs","rove","chap","room","wait","lurk","race","host","dada","lola","gabs","sobs","joel","keck","axed","mead","gust","laid","ends","oort","nose","peer","kept","abet","iran","mick","dead","hags","tens","gown","sick","odis","miro","bill","fawn","sumo","kilt","huge","ores","oran","flag","tost","seth","sift","poet","reds","pips","cape","togo","wale","limn","toll","ploy","inns","snag","hoes","jerk","flux","fido","zane","arab","gamy","raze","lank","hurt","rail","hind","hoot","dogy","away","pest","hoed","pose","lose","pole","alva","dino","kind","clan","dips","soup","veto","edna","damp","gush","amen","wits","pubs","fuzz","cash","pine","trod","gunk","nude","lost","rite","cory","walt","mica","cart","avow","wind","book","leon","life","bang","draw","leek","skis","dram","ripe","mine","urea","tiff","over","gale","weir","defy","norm","tull","whiz","gill","ward","crag","when","mill","firs","sans","flue","reid","ekes","jain","mutt","hems","laps","piss","pall","rowe","prey","cull","knew","size","wets","hurl","wont","suva","girt","prys","prow","warn","naps","gong","thru","livy","boar","sade","amok","vice","slat","emir","jade","karl","loyd","cerf","bess","loss","rums","lats","bode","subs","muss","maim","kits","thin","york","punt","gays","alpo","aids","drag","eras","mats","pyre","clot","step","oath","lout","wary","carp","hums","tang","pout","whip","fled","omar","such","kano","jake","stan","loop","fuss","mini","byrd","exit","fizz","lire","emil","prop","noes","awed","gift","soli","sale","gage","orin","slur","limp","saar","arks","mast","gnat","port","into","geed","pave","awls","cent","cunt","full","dint","hank","mate","coin","tars","scud","veer","coax","bops","uris","loom","shod","crib","lids","drys","fish","edit","dick","erna","else","hahs","alga","moho","wire","fora","tums","ruth","bets","duns","mold","mush","swop","ruby","bolt","nave","kite","ahem","brad","tern","nips","whew","bait","ooze","gino","yuck","drum","shoe","lobe","dusk","cult","paws","anew","dado","nook","half","lams","rich","cato","java","kemp","vain","fees","sham","auks","gish","fire","elam","salt","sour","loth","whit","yogi","shes","scam","yous","lucy","inez","geld","whig","thee","kelp","loaf","harm","tomb","ever","airs","page","laud","stun","paid","goop","cobs","judy","grab","doha","crew","item","fogs","tong","blip","vest","bran","wend","bawl","feel","jets","mixt","tell","dire","devi","milo","deng","yews","weak","mark","doug","fare","rigs","poke","hies","sian","suez","quip","kens","lass","zips","elva","brat","cosy","teri","hull","spun","russ","pupa","weed","pulp","main","grim","hone","cord","barf","olav","gaps","rote","wilt","lars","roll","balm","jana","give","eire","faun","suck","kegs","nita","weer","tush","spry","loge","nays","heir","dope","roar","peep","nags","ates","bane","seas","sign","fred","they","lien","kiev","fops","said","lawn","lind","miff","mass","trig","sins","furl","ruin","sent","cray","maya","clog","puns","silk","axis","grog","jots","dyer","mope","rand","vend","keen","chou","dose","rain","eats","sped","maui","evan","time","todd","skit","lief","sops","outs","moot","faze","biro","gook","fill","oval","skew","veil","born","slob","hyde","twin","eloy","beat","ergs","sure","kobe","eggo","hens","jive","flax","mons","dunk","yest","begs","dial","lodz","burp","pile","much","dock","rene","sago","racy","have","yalu","glow","move","peps","hods","kins","salk","hand","cons","dare","myra","sega","type","mari","pelt","hula","gulf","jugs","flay","fest","spat","toms","zeno","taps","deny","swag","afro","baud","jabs","smut","egos","lara","toes","song","fray","luis","brut","olen","mere","ruff","slum","glad","buds","silt","rued","gelt","hive","teem","ides","sink","ands","wisp","omen","lyre","yuks","curb","loam","darn","liar","pugs","pane","carl","sang","scar","zeds","claw","berg","hits","mile","lite","khan","erik","slug","loon","dena","ruse","talk","tusk","gaol","tads","beds","sock","howe","gave","snob","ahab","part","meir","jell","stir","tels","spit","hash","omit","jinx","lyra","puck","laue","beep","eros","owed","cede","brew","slue","mitt","jest","lynx","wads","gena","dank","volt","gray","pony","veld","bask","fens","argo","work","taxi","afar","boon","lube","pass","lazy","mist","blot","mach","poky","rams","sits","rend","dome","pray","duck","hers","lure","keep","gory","chat","runt","jams","lays","posy","bats","hoff","rock","keri","raul","yves","lama","ramp","vote","jody","pock","gist","sass","iago","coos","rank","lowe","vows","koch","taco","jinn","juno","rape","band","aces","goal","huck","lila","tuft","swan","blab","leda","gems","hide","tack","porn","scum","frat","plum","duds","shad","arms","pare","chin","gain","knee","foot","line","dove","vera","jays","fund","reno","skid","boys","corn","gwyn","sash","weld","ruiz","dior","jess","leaf","pars","cote","zing","scat","nice","dart","only","owls","hike","trey","whys","ding","klan","ross","barb","ants","lean","dopy","hock","tour","grip","aldo","whim","prom","rear","dins","duff","dell","loch","lava","sung","yank","thar","curl","venn","blow","pomp","heat","trap","dali","nets","seen","gash","twig","dads","emmy","rhea","navy","haws","mite","bows","alas","ives","play","soon","doll","chum","ajar","foam","call","puke","kris","wily","came","ales","reef","raid","diet","prod","prut","loot","soar","coed","celt","seam","dray","lump","jags","nods","sole","kink","peso","howl","cost","tsar","uric","sore","woes","sewn","sake","cask","caps","burl","tame","bulk","neva","from","meet","webs","spar","fuck","buoy","wept","west","dual","pica","sold","seed","gads","riff","neck","deed","rudy","drop","vale","flit","romp","peak","jape","jews","fain","dens","hugo","elba","mink","town","clam","feud","fern","dung","newt","mime","deem","inti","gigs","sosa","lope","lard","cara","smug","lego","flex","doth","paar","moon","wren","tale","kant","eels","muck","toga","zens","lops","duet","coil","gall","teal","glib","muir","ails","boer","them","rake","conn","neat","frog","trip","coma","must","mono","lira","craw","sled","wear","toby","reel","hips","nate","pump","mont","died","moss","lair","jibe","oils","pied","hobs","cads","haze","muse","cogs","figs","cues","roes","whet","boru","cozy","amos","tans","news","hake","cots","boas","tutu","wavy","pipe","typo","albs","boom","dyke","wail","woke","ware","rita","fail","slab","owes","jane","rack","hell","lags","mend","mask","hume","wane","acne","team","holy","runs","exes","dole","trim","zola","trek","puma","wacs","veep","yaps","sums","lush","tubs","most","witt","bong","rule","hear","awry","sots","nils","bash","gasp","inch","pens","fies","juts","pate","vine","zulu","this","bare","veal","josh","reek","ours","cowl","club","farm","teat","coat","dish","fore","weft","exam","vlad","floe","beak","lane","ella","warp","goth","ming","pits","rent","tito","wish","amps","says","hawk","ways","punk","nark","cagy","east","paul","bose","solo","teed","text","hews","snip","lips","emit","orgy","icon","tuna","soul","kurd","clod","calk","aunt","bake","copy","acid","duse","kiln","spec","fans","bani","irma","pads","batu","logo","pack","oder","atop","funk","gide","bede","bibs","taut","guns","dana","puff","lyme","flat","lake","june","sets","gull","hops","earn","clip","fell","kama","seal","diaz","cite","chew","cuba","bury","yard","bank","byes","apia","cree","nosh","judo","walk","tape","taro","boot","cods","lade","cong","deft","slim","jeri","rile","park","aeon","fact","slow","goff","cane","earp","tart","does","acts","hope","cant","buts","shin","dude","ergo","mode","gene","lept","chen","beta","eden","pang","saab","fang","whir","cove","perk","fads","rugs","herb","putt","nous","vane","corm","stay","bids","vela","roof","isms","sics","gone","swum","wiry","cram","rink","pert","heap","sikh","dais","cell","peel","nuke","buss","rasp","none","slut","bent","dams","serb","dork","bays","kale","cora","wake","welt","rind","trot","sloe","pity","rout","eves","fats","furs","pogo","beth","hued","edam","iamb","glee","lute","keel","airy","easy","tire","rube","bogy","sine","chop","rood","elbe","mike","garb","jill","gaul","chit","dons","bars","ride","beck","toad","make","head","suds","pike","snot","swat","peed","same","gaza","lent","gait","gael","elks","hang","nerf","rosy","shut","glop","pain","dion","deaf","hero","doer","wost","wage","wash","pats","narc","ions","dice","quay","vied","eons","case","pour","urns","reva","rags","aden","bone","rang","aura","iraq","toot","rome","hals","megs","pond","john","yeps","pawl","warm","bird","tint","jowl","gibe","come","hold","pail","wipe","bike","rips","eery","kent","hims","inks","fink","mott","ices","macy","serf","keys","tarp","cops","sods","feet","tear","benz","buys","colo","boil","sews","enos","watt","pull","brag","cork","save","mint","feat","jamb","rubs","roxy","toys","nosy","yowl","tamp","lobs","foul","doom","sown","pigs","hemp","fame","boor","cube","tops","loco","lads","eyre","alta","aged","flop","pram","lesa","sawn","plow","aral","load","lied","pled","boob","bert","rows","zits","rick","hint","dido","fist","marc","wuss","node","smog","nora","shim","glut","bale","perl","what","tort","meek","brie","bind","cake","psst","dour","jove","tree","chip","stud","thou","mobs","sows","opts","diva","perm","wise","cuds","sols","alan","mild","pure","gail","wins","offs","nile","yelp","minn","tors","tran","homy","sadr","erse","nero","scab","finn","mich","turd","then","poem","noun","oxus","brow","door","saws","eben","wart","wand","rosa","left","lina","cabs","rapt","olin","suet","kalb","mans","dawn","riel","temp","chug","peal","drew","null","hath","many","took","fond","gate","sate","leak","zany","vans","mart","hess","home","long","dirk","bile","lace","moog","axes","zone","fork","duct","rico","rife","deep","tiny","hugh","bilk","waft","swig","pans","with","kern","busy","film","lulu","king","lord","veda","tray","legs","soot","ells","wasp","hunt","earl","ouch","diem","yell","pegs","blvd","polk","soda","zorn","liza","slop","week","kill","rusk","eric","sump","haul","rims","crop","blob","face","bins","read","care","pele","ritz","beau","golf","drip","dike","stab","jibs","hove","junk","hoax","tats","fief","quad","peat","ream","hats","root","flak","grit","clap","pugh","bosh","lock","mute","crow","iced","lisa","bela","fems","oxes","vies","gybe","huff","bull","cuss","sunk","pups","fobs","turf","sect","atom","debt","sane","writ","anon","mayo","aria","seer","thor","brim","gawk","jack","jazz","menu","yolk","surf","libs","lets","bans","toil","open","aced","poor","mess","wham","fran","gina","dote","love","mood","pale","reps","ines","shot","alar","twit","site","dill","yoga","sear","vamp","abel","lieu","cuff","orbs","rose","tank","gape","guam","adar","vole","your","dean","dear","hebe","crab","hump","mole","vase","rode","dash","sera","balk","lela","inca","gaea","bush","loud","pies","aide","blew","mien","side","kerr","ring","tess","prep","rant","lugs","hobo","joke","odds","yule","aida","true","pone","lode","nona","weep","coda","elmo","skim","wink","bras","pier","bung","pets","tabs","ryan","jock","body","sofa","joey","zion","mace","kick","vile","leno","bali","fart","that","redo","ills","jogs","pent","drub","slaw","tide","lena","seep","gyps","wave","amid","fear","ties","flan","wimp","kali","shun","crap","sage","rune","logs","cain","digs","abut","obit","paps","rids","fair","hack","huns","road","caws","curt","jute","fisk","fowl","duty","holt","miss","rude","vito","baal","ural","mann","mind","belt","clem","last","musk","roam","abed","days","bore","fuze","fall","pict","dump","dies","fiat","vent","pork","eyed","docs","rive","spas","rope","ariz","tout","game","jump","blur","anti","lisp","turn","sand","food","moos","hoop","saul","arch","fury","rise","diss","hubs","burs","grid","ilks","suns","flea","soil","lung","want","nola","fins","thud","kidd","juan","heps","nape","rash","burt","bump","tots","brit","mums","bole","shah","tees","skip","limb","umps","ache","arcs","raft","halo","luce","bahs","leta","conk","duos","siva","went","peek","sulk","reap","free","dubs","lang","toto","hasp","ball","rats","nair","myst","wang","snug","nash","laos","ante","opal","tina","pore","bite","haas","myth","yugo","foci","dent","bade","pear","mods","auto","shop","etch","lyly","curs","aron","slew","tyro","sack","wade","clio","gyro","butt","icky","char","itch","halt","gals","yang","tend","pact","bees","suit","puny","hows","nina","brno","oops","lick","sons","kilo","bust","nome","mona","dull","join","hour","papa","stag","bern","wove","lull","slip","laze","roil","alto","bath","buck","alma","anus","evil","dumb","oreo","rare","near","cure","isis","hill","kyle","pace","comb","nits","flip","clop","mort","thea","wall","kiel","judd","coop","dave","very","amie","blah","flub","talc","bold","fogy","idea","prof","horn","shoo","aped","pins","helm","wees","beer","womb","clue","alba","aloe","fine","bard","limo","shaw","pint","swim","dust","indy","hale","cats","troy","wens","luke","vern","deli","both","brig","daub","sara","sued","bier","noel","olga","dupe","look","pisa","knox","murk","dame","matt","gold","jame","toge","luck","peck","tass","calf","pill","wore","wadi","thur","parr","maul","tzar","ones","lees","dark","fake","bast","zoom","here","moro","wine","bums","cows","jean","palm","fume","plop","help","tuba","leap","cans","back","avid","lice","lust","polo","dory","stew","kate","rama","coke","bled","mugs","ajax","arts","drug","pena","cody","hole","sean","deck","guts","kong","bate","pitt","como","lyle","siam","rook","baby","jigs","bret","bark","lori","reba","sups","made","buzz","gnaw","alps","clay","post","viol","dina","card","lana","doff","yups","tons","live","kids","pair","yawl","name","oven","sirs","gyms","prig","down","leos","noon","nibs","cook","safe","cobb","raja","awes","sari","nerd","fold","lots","pete","deal","bias","zeal","girl","rage","cool","gout","whey","soak","thaw","bear","wing","nagy","well","oink","sven","kurt","etna","held","wood","high","feta","twee","ford","cave","knot","tory","ibis","yaks","vets","foxy","sank","cone","pius","tall","seem","wool","flap","gird","lore","coot","mewl","sere","real","puts","sell","nuts","foil","lilt","saga","heft","dyed","goat","spew","daze","frye","adds","glen","tojo","pixy","gobi","stop","tile","hiss","shed","hahn","baku","ahas","sill","swap","also","carr","manx","lime","debs","moat","eked","bola","pods","coon","lacy","tube","minx","buff","pres","clew","gaff","flee","burn","whom","cola","fret","purl","wick","wigs","donn","guys","toni","oxen","wite","vial","spam","huts","vats","lima","core","eula","thad","peon","erie","oats","boyd","cued","olaf","tams","secs","urey","wile","penn","bred","rill","vary","sues","mail","feds","aves","code","beam","reed","neil","hark","pols","gris","gods","mesa","test","coup","heed","dora","hied","tune","doze","pews","oaks","bloc","tips","maid","goof","four","woof","silo","bray","zest","kiss","yong","file","hilt","iris","tuns","lily","ears","pant","jury","taft","data","gild","pick","kook","colt","bohr","anal","asps","babe","bach","mash","biko","bowl","huey","jilt","goes","guff","bend","nike","tami","gosh","tike","gees","urge","path","bony","jude","lynn","lois","teas","dunn","elul","bonn","moms","bugs","slay","yeah","loan","hulk","lows","damn","nell","jung","avis","mane","waco","loin","knob","tyke","anna","hire","luau","tidy","nuns","pots","quid","exec","hans","hera","hush","shag","scot","moan","wald","ursa","lorn","hunk","loft","yore","alum","mows","slog","emma","spud","rice","worn","erma","need","bags","lark","kirk","pooh","dyes","area","dime","luvs","foch","refs","cast","alit","tugs","even","role","toed","caph","nigh","sony","bide","robs","folk","daft","past","blue","flaw","sana","fits","barr","riot","dots","lamp","cock","fibs","harp","tent","hate","mali","togs","gear","tues","bass","pros","numb","emus","hare","fate","wife","mean","pink","dune","ares","dine","oily","tony","czar","spay","push","glum","till","moth","glue","dive","scad","pops","woks","andy","leah","cusp","hair","alex","vibe","bulb","boll","firm","joys","tara","cole","levy","owen","chow","rump","jail","lapp","beet","slap","kith","more","maps","bond","hick","opus","rust","wist","shat","phil","snow","lott","lora","cary","mote","rift","oust","klee","goad","pith","heep","lupe","ivan","mimi","bald","fuse","cuts","lens","leer","eyry","know","razz","tare","pals","geek","greg","teen","clef","wags","weal","each","haft","nova","waif","rate","katy","yale","dale","leas","axum","quiz","pawn","fend","capt","laws","city","chad","coal","nail","zaps","sort","loci","less","spur","note","foes","fags","gulp","snap","bogs","wrap","dane","melt","ease","felt","shea","calm","star","swam","aery","year","plan","odin","curd","mira","mops","shit","davy","apes","inky","hues","lome","bits","vila","show","best","mice","gins","next","roan","ymir","mars","oman","wild","heal","plus","erin","rave","robe","fast","hutu","aver","jodi","alms","yams","zero","revs","wean","chic","self","jeep","jobs","waxy","duel","seek","spot","raps","pimp","adan","slam","tool","morn","futz","ewes","errs","knit","rung","kans","muff","huhs","tows","lest","meal","azov","gnus","agar","sips","sway","otis","tone","tate","epic","trio","tics","fade","lear","owns","robt","weds","five","lyon","terr","arno","mama","grey","disk","sept","sire","bart","saps","whoa","turk","stow","pyle","joni","zinc","negs","task","leif","ribs","malt","nine","bunt","grin","dona","nope","hams","some","molt","smit","sacs","joan","slav","lady","base","heck","list","take","herd","will","nubs","burg","hugs","peru","coif","zoos","nick","idol","levi","grub","roth","adam","elma","tags","tote","yaws","cali","mete","lula","cubs","prim","luna","jolt","span","pita","dodo","puss","deer","term","dolt","goon","gary","yarn","aims","just","rena","tine","cyst","meld","loki","wong","were","hung","maze","arid","cars","wolf","marx","faye","eave","raga","flow","neal","lone","anne","cage","tied","tilt","soto","opel","date","buns","dorm","kane","akin","ewer","drab","thai","jeer","grad","berm","rods","saki","grus","vast","late","lint","mule","risk","labs","snit","gala","find","spin","ired","slot","oafs","lies","mews","wino","milk","bout","onus","tram","jaws","peas","cleo","seat","gums","cold","vang","dewy","hood","rush","mack","yuan","odes","boos","jami","mare","plot","swab","borg","hays","form","mesh","mani","fife","good","gram","lion","myna","moor","skin","posh","burr","rime","done","ruts","pays","stem","ting","arty","slag","iron","ayes","stub","oral","gets","chid","yens","snub","ages","wide","bail","verb","lamb","bomb","army","yoke","gels","tits","bork","mils","nary","barn","hype","odom","avon","hewn","rios","cams","tact","boss","oleo","duke","eris","gwen","elms","deon","sims","quit","nest","font","dues","yeas","zeta","bevy","gent","torn","cups","worm","baum","axon","purr","vise","grew","govs","meat","chef","rest","lame"})));
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
