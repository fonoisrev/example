package example;

/**
 * @author wuhang
 */
public class MyNode<T> {

	public final T value;
	
	public MyNode<T> next;
	
	public MyNode(T value){
		this.value = value;
	}
}
