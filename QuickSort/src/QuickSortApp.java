import com.QuickSort;

public class QuickSortApp {
	public static void main(String args[]){
		int maxsize = 16;
		QuickSort arr = new QuickSort(maxsize);
		for(int i=0;i<maxsize;i++){
			double n = (int)(java.lang.Math.random()*99);
			arr.insert(n);
		}
		arr.printArray();
		arr.quickSort();
		arr.printArray();
	}
}
