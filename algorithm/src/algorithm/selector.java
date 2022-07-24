package algorithm;

public class selector {

	public static void select(int[] arr) {
		int leng= arr.length;
		for(int i=0;i<leng;i++){
			int minIndex=i;
			
			for(int j=1+i;j<leng;j++) {
				if(arr[i]>arr[j]) {
					minIndex=j;
					swap(arr,i,minIndex);
				}
			}
		}
	}
	
	public static void print(int[] arr) {
		
		int leng= arr.length;
		for(int i=0;i<leng;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	
	
	public static void swap(int[] array,int i,int j) {
		int tmp=array[i];
		array[i]=array[j];
		array[j]=tmp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array= {3,2,4,6,7,3,1,8,6};
		print(array);
		select(array);
		print(array);
		
	}
	
	

}
