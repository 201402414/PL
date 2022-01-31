package H08;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NodePrinter {
	private final String OUTPUT_FILENAME = "output08.txt";
	private StringBuffer sb = new StringBuffer();
	private Node root;

	public NodePrinter(Node root) {
		if (root instanceof ListNode) {
			sb.append("\'");
		}
		this.root = root;
	}

	// ListNode, QuoteNode, Node�� ���� printNode �Լ��� ���� overload �������� �ۼ�
	private void printList(ListNode listNode) {
		if (listNode == ListNode.EMPTYLIST) {
			return;
		}
		printNode(listNode.car());
		printList(listNode.cdr());
		// ���� �κ��� �־��� ��� ���Ŀ� �°� �ڵ带 �ۼ��Ͻÿ�.
	}

	private void printNode(QuoteNode quoteNode) {
		if (quoteNode.nodeInside() == null)
			return;
		sb.append(" ");
		printNode(quoteNode.nodeInside());
		// ���� �κ��� �־��� ��� ���Ŀ� �°� �ڵ带 �ۼ��Ͻÿ�.
	}

	private void printNode(Node node) {
		if (node == null)
			return;
		// ���� �κ��� �־��� ��� ���Ŀ� �°� �ڵ带 �ۼ��Ͻÿ�.
		if (node instanceof ListNode) {
			if (((ListNode) node).car() instanceof QuoteNode)
				printList((ListNode) node);
			else {
				sb.append("(");
				printList((ListNode) node);
				sb.append(")");
			}
		} else if (node instanceof QuoteNode) {
			printNode((QuoteNode) node);
		} else {
			sb.append(" " + node + " ");
		}
	}

	public void prettyPrint() {
		printNode(root);
		try (FileWriter fw = new FileWriter(OUTPUT_FILENAME); PrintWriter pw = new PrintWriter(fw)) {
			pw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
