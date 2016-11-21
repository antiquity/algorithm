class UnionFind{
  int[] p, size;
  int comps;
  UnionFind(int n){
    p = new int[n];
    size = new int[n];
    reset();
  }
  void reset(){
    for(int i=0; i<p.length; i++){
      p[i]=i;
      size[i]=1;
    }
    comps = p.length;
  }
  int find(int x){
    if(x!=p[x]) p[x]=find(p[x]);
    return p[x];
  }
  void union(int x, int y){
    x = find(x);
    y = find(y);
    if(x!=y)
      if(size[x]>size[y]){
        p[y]=x;
        size[x]+=size[y];
      }else{
        p[x]=y;
        size[y]+=size[x];
      }
    comps--;
  }
}
