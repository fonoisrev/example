package example;

import exam.ISortedLinkedList;

public class MySortableLinkedList<T extends Comparable<T>> implements
		ISortedLinkedList<T> {

	/**
	 * 头指针
	 */
	private MyNode<T> head = null;

	private int size = 0;

	/**
	 * 链表默认是增序的
	 */
	private int order = ASC;
	public static final int ASC = -1;
	public static final int DESC = 1;

	@Override
	public synchronized void add(T t) {
		if (head == null) {
			head = new MyNode<T>(t);
			size = 1;
			return;
		}

		MyNode<T> current = head;
		MyNode<T> indexToAdd = null;

		while (true) {
			int comp = current.value.compareTo(t);

			// 标识是否继续比较
			boolean continueFlag = true;
			if ((order == ASC && comp >= 0) || (order == DESC && comp <= 0)) {
				continueFlag = false;
			}

			if (continueFlag) {
				// 继续比较
				indexToAdd = current;
				current = current.next;
				if (current == null) {
					break;
				} else {
					continue;
				}
			} else {
				// 找到插入节点
				break;
			}
		}

		// 插入节点
		MyNode<T> newNode = new MyNode<>(t);
		newNode.next = current;
		if (indexToAdd == null) {
			head = newNode;
		} else {
			indexToAdd.next = newNode;
		}
		size++;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}

		MyNode<T> toGet = head;
		for (int i = 0; i < index; ++i) {
			toGet = toGet.next;
		}

		return toGet.value;
	}

	@Override
	public synchronized T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}

		MyNode<T> toRemove = head;
		MyNode<T> last = null;

		for (int i = 0; i < index; ++i) {
			last = toRemove;
			toRemove = toRemove.next;
		}

		if (last == null) {
			head = toRemove.next;
		} else {
			last.next = toRemove.next;
		}
		size--;
		return toRemove.value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public synchronized void reverse() {
		// 通过将当前的头节点一个个移动到新链表的头节点实现反转
		MyNode<T> newHead = null;
		while (head != null) {
			MyNode<T> currentNode = head;
			head = head.next;
			currentNode.next = newHead;
			newHead = currentNode;
		}
		head = newHead;

		// 反转链表的排序
		if (order == ASC) {
			order = DESC;
		} else {
			order = ASC;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MySortableLinkedList [size=").append(size).append("] " +
				"[elements: ");
		if (head != null) {
			MyNode<T> toPrint = head;
			for (int i = 0; i < size; ++i) {
				sb.append(toPrint.value.toString()).append(", ");
				toPrint = toPrint.next;
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
