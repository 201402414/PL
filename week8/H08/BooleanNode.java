package H08;

public class BooleanNode implements ValueNode {
	// 새로 수정된 BooleanNode Class
	public static BooleanNode FALSE_NODE = new BooleanNode(false);
	public static BooleanNode TRUE_NODE = new BooleanNode(true);
	public Boolean value;

	public BooleanNode(Boolean b) {
		value = b;
	}

	@Override
	public String toString() {
		return value ? "#T" : "#F";

	}
}
