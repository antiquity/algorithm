public class FoxSequence{
    public String isValid(int[] seq){
        int d;
        int x=0;
        int i=0, n=seq.length;
        
        if(!((i+1<n) && (d=seq[i+1]-seq[i])>0)) return "NO";
        while((i+1<n) && (seq[i+1]-seq[i]==d))
            i++;
        if(i==x) return "NO";
        else x=i;

        if(!((i+1<n) && (d=seq[i+1]-seq[i])<0)) return "NO";
        while((i+1<n) && (seq[i+1]-seq[i]==d))
            i++;
        if(i==x) return "NO";

        while((i+1<n) && (seq[i]==seq[i+1])) i++;
        x=i;
       
        if(!((i+1<n) && (d=seq[i+1]-seq[i])>0)) return "NO";
        while((i+1<n) && (seq[i+1]-seq[i]==d))
            i++;
        if(i==x) return "NO";
        else x=i;
        
        if(!((i+1<n) && (d=seq[i+1]-seq[i])<0)) return "NO";
        while((i+1<n) && (seq[i+1]-seq[i]==d))
            i++;
        if((i==x)||((i+1)<n)) return "NO";
        return "YES";       
    }
}
