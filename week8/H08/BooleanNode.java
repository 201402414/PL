package H08;

public class BooleanNode implements ValueNode {
	// ���� ������ BooleanNode Class
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
