package exam;

/**
 *
 */
public interface ISortedLinkedList<T extends Comparable<T>>  {

	/**
	 * 向链表中插入一个元素，请注意是排序链表
	 */
	void add(T element);

	/**
	 * 获取指定位置的元素，位置0表示第一个
	 */
	T get(int index);

	/**
	 * 删除指定位置元素，位置0表示第一个
	 * 并返回被删除的元素
	 */
	T remove(int index);

	/**
	 * 获取链表中元素的个数
	 */
	int size();

	/**
	 * 反转链表
	 */
	void reverse();

}
