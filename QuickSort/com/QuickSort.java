package com;

public class QuickSort {
	private double []theArray;
	private int finalPosition;

	public QuickSort(int max){
		theArray = new double[max];
		finalPosition=0;
	}

	public void insert(double num){
		theArray[finalPosition] = num;
		finalPosition++;
	}

	public void printArray(){
		for(int i=0;i<finalPosition;i++)
			System.out.print(theArray[i]+" ");
		System.out.println("");
	}

	public void quickSort(){
		recQuickSort(0,finalPosition-1);
	}
	//core function of sort
	public void recQuickSort(int left, int right){
		int partition;
		double pivot;
		if(left>=right)
			return ;
		else{
			pivot = theArray[right];
			partition=getPartition(left, right, pivot);
			recQuickSort(left,partition-1);
			recQuickSort(partition+1,right);
		}
	}
	//partition
	public int getPartition(int left, int right, double pivot){
		int leftIndex=left-1;
		int rightIndex=right;
		double temp;
		while(leftIndex<rightIndex) {
			while (leftIndex < rightIndex && theArray[++leftIndex] < pivot );
			while (rightIndex > leftIndex && theArray[--rightIndex] > pivot ) ;
			if (leftIndex < rightIndex){

				swap(rightIndex, leftIndex);
			}
			else
				break;
		}
		swap(leftIndex, right);
		return leftIndex;
	}

	public void swap(int a1,int a2){
		double temp;
		temp=theArray[a1];
		theArray[a1]=theArray[a2];
		theArray[a2]=temp;
	}
}
