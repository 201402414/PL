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

	// ListNode, QuoteNode, Node에 대한 printNode 함수를 각각 overload 형식으로 작성
	private void printList(ListNode listNode) {
		if (listNode == ListNode.EMPTYLIST) {
			return;
		}
		printNode(listNode.car());
		printList(listNode.cdr());
		// 이후 부분을 주어진 출력 형식에 맞게 코드를 작성하시오.
	}

	private void printNode(QuoteNode quoteNode) {
		if (quoteNode.nodeInside() == null)
			return;
		sb.append(" ");
		printNode(quoteNode.nodeInside());
		// 이후 부분을 주어진 출력 형식에 맞게 코드를 작성하시오.
	}

	private void printNode(Node node) {
		if (node == null)
			return;
		// 이후 부분을 주어진 출력 형식에 맞게 코드를 작성하시오.
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
