package exam;

public interface ISimpleSortedLinkedList {

	/**
	 * 向链表中插入一个元整数，请注意是排序链表
	 */
	void add(int element);

	/**
	 * 获取指定位置的整数，位置0表示第一个
	 */
	int get(int index);

	/**
	 * 删除指定位置元素，位置0表示第一个
	 * 并返回被删除的元素
	 */
	int remove(int index);

	/**
	 * 反转链表
	 */
	void reverse();

	/**
	 * 获取链表中元素的个数
	 */
	int size();
}
