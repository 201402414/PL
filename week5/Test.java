package H05;

import compile.TreeFactory;
import ast.IntNode;
import ast.ListNode;
import ast.Node;

public class Test {
	public static int max(Node node) {
		// �ִ밪�� �����ϵ��� �ۼ�
		// value�� next �� �� ū ���� ����
		if (node instanceof ListNode) {
			if (node.getNext() == null) {
				return max(((ListNode) node).value);
			} else {
				if (max(((ListNode) node).value) < max(node.getNext())) {
					return max(node.getNext());
				} else {
					return max(((ListNode) node).value);
				}
			}
		} else if (node instanceof IntNode) {
			if (node.getNext() == null) {
				return ((IntNode) node).value;
			} else {
				if (((IntNode) node).value < max(node.getNext())) {
					return max(node.getNext());
				} else {
					return ((IntNode) node).value;
				}
			}
		} else {
			return -201402414;
		}
	}

	public static int sum(Node node) {
		// ��� value�� ������ ��ȯ
		// value�� next�� �� ���� �����ϸ��
		if (node instanceof ListNode) {
			if (node.getNext() == null) {
				return sum(((ListNode) node).value);
			} else {
				return sum(((ListNode) node).value) + sum(node.getNext());
			}
		} else if (node instanceof IntNode) {
			if (node.getNext() == null) {
				return ((IntNode) node).value;
			} else {
				return ((IntNode) node).value + sum(node.getNext());
			}
		} else {
			return 0;
		}
	}

	public static void main(String... args) {
		System.out.println("test1");
		Node node = TreeFactory.createtTree("( ( 3 ( ( 10 ) ) 6 ) 4 1 ( ) -2 ( ) )");
		// ���� ����� ����ϵ��� �ۼ�
		System.out.println("�ִ밪 : " + max(node));
		System.out.println("��   �� : " + sum(node));
		
		System.out.println("test2");
		Node node1 = TreeFactory.createtTree("( ( 20 ( ( -14 ) ) 0 ) 2 41 ( ) 4 ( ) )");
		System.out.println("�ִ밪 : " + max(node1));
		System.out.println("��   �� : " + sum(node1));

		System.out.println("test3");
		Node node2 = TreeFactory.createtTree("( ( 3 2) -378 ( ) )");
		System.out.println("�ִ밪 : " + max(node2));
		System.out.println("��   �� : " + sum(node2));
	}
}
