// Performance of Array, ArrayList, LinkedList, List, 

package mykafka;

import java.util.ArrayList;
import java.util.LinkedList;


public class CollectionsPerformance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> arraylist = new ArrayList<Integer>();
		LinkedList<Integer> linkedlist = new LinkedList<Integer>();
		
		long startTime = System.nanoTime();
		
		for(int i =0;i<100000;i++){
			arraylist.add(i);
		}
		
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		
		System.out.println(" ARRAYLIST add : " + duration);
		
		
		long startTime1 = System.nanoTime();
		
		for(int i =0;i<100000;i++){
			linkedlist.add(i);
		}
		
		long endTime1 = System.nanoTime();
		long duration1 = endTime1 - startTime1;
		
		System.out.println(" LinkedList add : " + duration1);
		
		
		long startTime2 = System.nanoTime();
		
		for(int i =0;i<100000;i++){
			arraylist.get(i);
		}
		
		long endTime2 = System.nanoTime();
		long duration2 = endTime2 - startTime2;
		
		System.out.println(" ArrayList get : " + duration2);
		
		long startTime3 = System.nanoTime();
		
		for(int i =0;i<100000;i++){
			linkedlist.get(i);
		}
		
		long endTime3 = System.nanoTime();
		long duration3 = endTime3 - startTime3;
		
		System.out.println(" linkedlist get : " + duration3);
		
		//------------------------------------------------
		
		long startTime4 = System.nanoTime();
		
		for(int i =9999;i<=0;i--){
			arraylist.remove(i);
		}
		
		long endTime4 = System.nanoTime();
		long duration4 = endTime4 - startTime4;
		
		System.out.println(" arraylist remove : " + duration4);
		
		//------------------------------------------------

		long startTime5 = System.nanoTime();
		
		for(int i =9999;i<=0;i--){
			linkedlist.remove(i);
		}
		
		long endTime5 = System.nanoTime();
		long duration5 = endTime5 - startTime5;
		
		System.out.println(" linkedlist remove : " + duration5);

		
		

	}

}
