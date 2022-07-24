package algorithm;

public class factorial {

	public static void format(int num) {
		int b=0;
		int c=1;
		for(int i=1;i<num+1;i++) {
			c=c*i;
			b +=c;
		}
		System.out.print(b);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		format(3);
	}

}
