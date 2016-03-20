import java.util.*;
class TrieNode{
    char c;
    boolean isEnd;
    Map<Character,TrieNode> children;
    TrieNode(){
        children = new HashMap<>();
    }
    TrieNode(char c){
        this();
        this.c = c;
    }
    void addWord(String str){
        if(str==null)
            return;
        if(str.isEmpty()){
            isEnd=true;
            return;
        }
        TrieNode child = children.get(str.charAt(0));
        if(child==null){
            child = new TrieNode(str.charAt(0));
            children.put(str.charAt(0),child);
        }
        child.addWord(str.substring(1));
    }
    void addWord(char[] str){
        if(str==null) return;
        addWord(str,0);
    }
    private void addWord(char[] str, int idx){
        if(idx==str.length){
            isEnd=true;
            return;
        }
        TrieNode child = children.get(str[idx]);
        if(child==null){
            child = new TrieNode(str[idx]);
            children.put(str[idx],child);
        }
        child.addWord(str,idx+1);
    }
    boolean search(String str){
        if(str.isEmpty())
            return isEnd;
        if(children.containsKey(str.charAt(0)))
            return children.get(str.charAt(0)).search(str.substring(1));
        else
            return false;
    }
}

