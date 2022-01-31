package H05;

import compile.TreeFactory;
import ast.IntNode;
import ast.ListNode;
import ast.Node;

public class Test {
	public static int max(Node node) {
		// 최대값을 리턴하도록 작성
		// value와 next 값 중 큰 값을 리턴
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
		// 노드 value의 총합을 반환
		// value와 next의 총 합을 리턴하면됨
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
		// 이하 결과를 출력하도록 작성
		System.out.println("최대값 : " + max(node));
		System.out.println("총   합 : " + sum(node));
		
		System.out.println("test2");
		Node node1 = TreeFactory.createtTree("( ( 20 ( ( -14 ) ) 0 ) 2 41 ( ) 4 ( ) )");
		System.out.println("최대값 : " + max(node1));
		System.out.println("총   합 : " + sum(node1));

		System.out.println("test3");
		Node node2 = TreeFactory.createtTree("( ( 3 2) -378 ( ) )");
		System.out.println("최대값 : " + max(node2));
		System.out.println("총   합 : " + sum(node2));
	}
}
