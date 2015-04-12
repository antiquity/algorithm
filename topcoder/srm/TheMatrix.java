import java.util.*;

public class TheMatrix {
    public int MaxArea(String[] board) {
        int M = board.length, N = board[0].length();
        int above;
        int res=1,l,L,temp;
        for(int i=0; i<M; i++)
            for(int j=0; j<N; j++){
                L = N; l=j+1;
                while(l<L && board[i].charAt(l)!=board[i].charAt(l-1)){
                    l++;
                }
                above = l;
                temp = l-j;
                if(temp>res) res=temp;
                //System.out.format("(%d, %d) -> (%d, %d)\n",i,j,i,l);

                for(int k=i+1; k<M; k++){
                    if(board[k].charAt(j)!=board[k-1].charAt(j)){
                        L = above;
                        l = j+1;
                        while(l<L && board[k].charAt(l)!=board[k].charAt(l-1)){
                            l++;
                        }
                        above = l;
                        temp = (k-i+1)*(l-j);
                        if(temp>res) res=temp;
                        //System.out.format("(%d, %d) -> (%d, 
                        //%d)\n",i,j,k,l);
                    }else
                        break;
                }
            }
        return res;

    }
// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new TheMatrix().runTestCase(0);
		new TheMatrix().runTestCase(1);
		new TheMatrix().runTestCase(2);
		new TheMatrix().runTestCase(3);
		new TheMatrix().runTestCase(4);
		new TheMatrix().runTestCase(5);
		new TheMatrix().runTestCase(6);
		new TheMatrix().runTestCase(7);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(MaxArea(new String[] {"1",   "0"}), 2, 0); break;
			}
			case 1 : {
				checkOutput(MaxArea(new String[] {"0000"}), 1, 1); break;
			}
			case 2 : {
				checkOutput(MaxArea(new String[] {"01"}), 2, 2); break;
			}
			case 3 : {
				checkOutput(MaxArea(new String[] {"001",  "000",  "100"}), 2, 3); break;
			}
			case 4 : {
				checkOutput(MaxArea(new String[] {"0"}), 1, 4); break;
			}
			case 5 : {
				checkOutput(MaxArea(new String[] {"101",   "010"}), 6, 5); break;
			}
			case 6 : {
				checkOutput(MaxArea(new String[] {"101",   "011",   "101",   "010"}), 8, 6); break;
			}
			case 7 : {
				checkOutput(MaxArea(new String[] {"11001110011000110001111001001110110011010110001011",   "10100100010111111011111001011110101111010011100001",   "11101111001110100110010101101100011100101000010001",   "01000010001010101100010011111000100100110111111000",   "10110100000101100000111000100001011101111101010010",   "00111010000011100001110110010011010110010011100100",   "01100001111101001101001101100001111000111001101010",   "11010000000011011010100010000000111011001001100101",   "10100000000100010100100011010100110110110001000001",   "01101010101100001100000110100110100000010100100010",   "11010000001110111111011010011110001101100011100010",   "11101111000000011010011100100001100011111111110111",   "11000001101100100011000110111010011001010100000001",   "00100001111001010000101101100010000001100100001000",   "01001110110111101011010000111111101011000110010111",   "01001010000111111001100000100010101100100101010100",   "11111101001101110011011011011000111001101100011011",   "10000100110111000001110110010000000000111100101101",   "01010011101101101110000011000110011111001111011100",   "01101010011111010000011001111101011010011100001101",   "11011000011000110010101111100000101011011111101100",   "11100001001000110010100011001010101101001010001100",   "11011011001100111101001100111100000101011101101011",   "11110111100100101011100101111101000111001111110111",   "00011001100110111100111100001100101001111100001111",   "10001111100101110111001111100000000011110000100111",   "10101010110110100110010001001010000111100110100011",   "01100110100000001110101001101011001010001101110101",   "10110101110100110110101001100111110000101111100110",   "01011000001001101110100001101001110011001001110001",   "00100101010001100110110101001010010100001011000011",   "00011101100100001010100000000011000010100110011100",   "11001001011000000101111111000000110010001101101110",   "10101010110110010000010011001100110101110100111011",   "01101001010111010001101000100011101001110101000110",   "00110101101110110001110101110010100100110000101101",   "11010101000111010011110011000001101111010011110011",   "10010000010001110011011101001110110010001100011100",   "00111101110001001100101001110100110010100110110000",   "00010011011000101000100001101110111100100000010100",   "01101110001101000001001000001011101010011101011110",   "00000100110011001011101011110011011101100001110111",   "00110011110000011001011100001110101010100110010110",   "00111001010011011111010100000100100000101101110001",   "10101101101110111110000011111011001011100011110001",   "00101110010101111000001010110100001110111011100011",   "01111110010100111010110001111000111101110100111011"}), 12, 7); break;
			}
		}
	}
	final void checkOutput(int mine, int them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	final void checkOutput(long mine, long them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	final void checkOutput(double mine, double them, int nbr) {
		boolean success = doubleCompare(mine, them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	private static boolean doubleCompare(double expected, double result){
		double MAX_DOUBLE_ERROR = 1E-9;
		if(Double.isNaN(expected)){
			return Double.isNaN(result);
		}else if(Double.isInfinite(expected)){
			if(expected > 0){
				return result > 0 && Double.isInfinite(result);
			}else{
				return result < 0 && Double.isInfinite(result);
			}
		}else if(Double.isNaN(result) || Double.isInfinite(result)){
			return false;
		}else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){
			return true;
		}else{
			double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR),
				expected * (1.0 + MAX_DOUBLE_ERROR));
			double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR),
					expected * (1.0 + MAX_DOUBLE_ERROR));
			return result > min && result < max;
		}
	}
	final void checkOutput(char mine, char them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("'");
			out.append(mine);
			out.append("'");
			out.append(", Expected: ");
			out.append("'");
			out.append(them);
			out.append("'");
		}
		System.out.println(out);
	}
	final void checkOutput(String mine, String them, int nbr) {
		boolean success = (mine.equals(them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("\"");
			out.append(mine);
			out.append("\"");
			out.append(", Expected: ");
			out.append("\"");
			out.append(them);
			out.append("\"");
		}
		System.out.println(out);
	}
	final void checkOutput(long[] mine, long[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(char[] mine, char[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(double[] mine, double[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(int[] mine, int[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(String[] mine, String[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}

/** end cut - don't modify this line*/
// END CUT HERE
}
