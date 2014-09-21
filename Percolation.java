public class Percolation {
    private boolean [][] open;
    private WeightedQuickUnionUF wqf;
    private int size;
    public Percolation(int N) {
	if (N <= 0) throw new IllegalArgumentException();
	open = new boolean[N][N];
	size = N;
	int i = 0,j = 0;
	for(i = 0; i < N; i++) { 
	    for(j = 0 ; j < N ;j++) {
		open[i][j] = false;
	    }
	}
	wqf = new WeightedQuickUnionUF(N*N);
    }

    public void open(int i, int j) {
	if(i < 1 || i > size || j < 1 || j>size) throw new IndexOutOfBoundsException();
	open[i-1][j-1] = true;
	//top
	if(i>1 && isOpen(i-1,j)) {
	    wqf.union((i-1)*size + j-1, (i-2)*size + j-1);
	}
	//bottom
	if(i<size && isOpen(i+1,j)) {
	    wqf.union((i-1)*size + j-1, (i)*size + j-1);
	}
	//left
	if(j>1 && isOpen(i,j-1)) {
	    wqf.union((i-1)*size + j-1, (i-1)*size + j-2);
	}
	//right
	if(j<size && isOpen(i, j+1)) {
	    wqf.union((i-1)*size + j-1, (i-1)*size + j);
	}
    }

    public boolean isOpen(int i, int j) {
	if(i<1 || i>size || j<1 ||j>size) throw new IndexOutOfBoundsException();
	else return open[i-1][j-1];
    }
    
    public boolean isFull(int i, int j) {
	if(i<1 || i>size || j<1 ||j>size) throw new IndexOutOfBoundsException();
	
	for(int k=0; k<size; k++) {
	    if(isOpen(i,j) && wqf.connected(k,size*(i-1)+j-1)) {
		return true;
	    }
	}
	return false;
    }

    public boolean percolates() {
	int i,j;
	for(j=1; j<=size; j++) {
	    if(isOpen(size,j)) {
		for(i=1;i<=size;i++) {
		    if(isOpen(1,i)) {
			if(wqf.connected(((size-1)*size+j) - 1, i-1))
			    return true;
		    }
		}
	    }
	}
	return false;
    }
  
    public static void main(String [] args) {
	int openItems = 0;
	
	In in= new In(args[0]);
	int N = in.readInt();
	Percolation perc = new Percolation(N);
	while (!in.isEmpty()) {
	    int i = in.readInt();
	    int j = in.readInt();
	    perc.open(i,j);
	    openItems++;
	    if(perc.percolates()) System.out.println("Percolates at " + openItems);
	}
    }
}