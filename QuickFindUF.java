public class QuickFindUF {

    private int[] id;
    
    public QuickFindUF(int N) {
        id = new int[N];
        for (int i=0; i< N; i++) {
            id[i] = i;
        }
    }
    
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p], qid = id[q];
        
        for(int i=0; i<id.length; i++) {
            if(id[i] == pid) id[i] = qid;
        }
    }
       
    public static void main(String [] args) {
        
        QuickFindUF list = new QuickFindUF(10);
	
	list.union(2,3);
	list.union(4,5);
	list.union(2,6);
	System.out.println("Is connected : " + list.connected(2,4));
    }
}
	
