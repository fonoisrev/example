package exam.check;

import exam.ISortedLinkedList;

public class LinkedListPrinter {

	public static <T extends Comparable> void print(ISortedLinkedList list, Class<T> tClass){
		StringBuilder sb = new StringBuilder();
		int size = list.size();
		sb.append("MySortableLinkedList [size=")
				.append(size)
				.append("][elements: ");
			for (int i = 0; i < size; ++i) {
				T toPrint = (T)list.get(i);
				sb.append(toPrint).append(", ");
			}
		sb.append("]");
		System.out.println(sb.toString());
	}
}
