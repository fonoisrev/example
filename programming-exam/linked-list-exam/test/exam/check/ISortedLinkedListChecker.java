package exam.check;

import exam.ISortedLinkedList;
import example.MySortableLinkedList;

import java.util.Arrays;
import java.util.Random;

public class ISortedLinkedListChecker {

	public static void main(String[] args){

		ISortedLinkedListChecker checker = new ISortedLinkedListChecker();
		for(int i=1; i<=3; ++i){
			System.out.println("第" + i + "次基本测试=========");
			ISortedLinkedList<Integer> list = new MySortableLinkedList<>();
			checker.useCase1(list);
			checker.useCase2(list);
		}
		System.out.println("性能测试=========");
		ISortedLinkedList<Integer> list = new MySortableLinkedList<>();
		checker.useCase3(list);
		System.out.println("反转后插入测试=========");
		checker.useCase4(list);
	}

	public void useCase1(ISortedLinkedList<Integer> list){
		clearList(list);

		Random rand = new Random();
		int elementsCount = 20;
		// 比对数组
		int[] array = new int[elementsCount];

		for(int i=0; i<elementsCount; ++i) {
			int num = rand.nextInt(256);
			array[i] = num;
			list.add(num);
		}

		Arrays.sort(array);
		System.out.println("生成链表：");
		LinkedListPrinter.print(list, Integer.class);

		// 1. 插入元素的对错（比较size，顺序与查询功能）
		int size = list.size();
		Assert.isTrue(size == elementsCount, "size不正确");

		// 2. 插入元素的对错
		for(int i=0; i<elementsCount; ++i){
			int num = list.get(i);
			Assert.isTrue(array[i] == num || array[elementsCount-1-i] == num,
					"链表内容不正确");

		}

		// 3. 随机删除元素
		for(int i=0; i<3; ++i){
			int oldsize = list.size();
			int index = rand.nextInt(oldsize);
			int numToRemove = list.get(index);
			int numRemoved = list.remove(index);
			int newsize = list.size();
			Assert.isTrue(oldsize == newsize + 1, "删除后size不正确");
			Assert.isTrue(numToRemove == numRemoved, "删除元素错误");
			System.out.println("删除 " + index + " 后：");
			LinkedListPrinter.print(list, Integer.class);
		}

	}

	public void useCase2(ISortedLinkedList<Integer> list){
		clearList(list);

		Random rand = new Random();
		int elementsCount = 20;
		// 比对数组
		int[] array = new int[elementsCount];

		for(int i=0; i<elementsCount; ++i) {
			int num = rand.nextInt(100);
			array[i] = num;
			list.add(num);
		}
		Arrays.sort(array);

		// 4. 反转测试
		System.out.println("反转链表");
		LinkedListPrinter.print(list, Integer.class);
		list.reverse();
		System.out.println("反转结果：");
		LinkedListPrinter.print(list, Integer.class);
		for(int i=0; i<elementsCount; ++i){
			int num = list.get(i);
			Assert.isTrue(array[i] == num || array[elementsCount-1-i] == num,
					"链表反转不正确");

		}

	}

	public void useCase3(ISortedLinkedList<Integer> list){
		clearList(list);

		Random rand = new Random();
		int elementsCount = 20000;
		// 比对数组
		int[] array = new int[elementsCount];

		long start = System.currentTimeMillis();
		for(int i=0; i<elementsCount; ++i) {
			int num = rand.nextInt(100);
			array[i] = num;
			list.add(num);
		}
		long end = System.currentTimeMillis();

		System.out.println("插入" + elementsCount + "个元素，耗时" + (end - start) + "ms");

		Arrays.sort(array);
		for(int i=0; i<elementsCount; ++i){
			int num = list.get(i);
			Assert.isTrue(array[i] == num || array[elementsCount-1-i] == num,
					"链表内容不正确");

		}
	}

	public void useCase4(ISortedLinkedList<Integer> list){
		list = new MySortableLinkedList<>();
		useCase2(list);
		Random rand = new Random();

		for(int i=0; i<3; ++i){
			int num = rand.nextInt(256);
			list.add(num);
			System.out.println("插入" + num + "得到链表：");
			LinkedListPrinter.print(list, Integer.class);
		}

	}

	private void clearList(ISortedLinkedList<Integer> list) {
		int size = list.size();
		for(int i=0; i<size; ++i){
			list.remove(0);
		}
	}
}
