package Interpreter;

import java.io.File;

import H08.BinaryOpNode;
import H08.BooleanNode;
import H08.CuteParser;
import H08.FunctionNode;
import H08.IdNode;
import H08.IntNode;
import H08.ListNode;
import H08.Node;
import H08.NodePrinter;
import H08.ParserMain;
import H08.QuoteNode;

public class CuteInterpreter {

	public static void main(String[] args) {
		ClassLoader cloader = ParserMain.class.getClassLoader();
		File file = new File(cloader.getResource("H08/as08.txt").getFile());
		CuteParser cuteParser = new CuteParser(file);
		CuteInterpreter interpreter = new CuteInterpreter();
		Node parseTree = cuteParser.parseExpr();
		Node resultNode = interpreter.runExpr(parseTree);
		NodePrinter nodePrinter = new NodePrinter(resultNode);
		nodePrinter.prettyPrint();
	}

	private void errorLog(String err) {
		System.out.println(err);
	}

	public Node runExpr(Node rootExpr) {
		if (rootExpr == null)
			return null;

		if (rootExpr instanceof IdNode)
			return rootExpr;

		else if (rootExpr instanceof IntNode)
			return rootExpr;

		else if (rootExpr instanceof BooleanNode)
			return rootExpr;

		else if (rootExpr instanceof ListNode)
			return runList((ListNode) rootExpr);

		else
			errorLog("run Expr error");

		return null;

	}

	private Node runList(ListNode list) {
		if (list.equals(ListNode.EMPTYLIST))
			return list;

		if (list.car() instanceof FunctionNode) {
			return runFunction((FunctionNode) list.car(), (ListNode) stripList(list.cdr()));
		}

		if (list.car() instanceof BinaryOpNode) {
			return runBinary(list);
		}
		return list;
	}

	private Node runFunction(FunctionNode operator, ListNode operand) {

		switch (operator.funcType) {

		case CAR: {
			return ((ListNode) runQuote(operand)).car();
		}
		case CDR: {
			return ((ListNode) runQuote(operand)).cdr();
		}
		case CONS: {
			Node head = operand.car();
			Node tail = operand.cdr().car();

			if (head instanceof QuoteNode) {
				return ListNode.cons(runQuote((ListNode) head), (ListNode) runQuote((ListNode) tail));
			} else {
				return ListNode.cons(head, (ListNode) runQuote((ListNode) tail));
			}
		}
		case NULL_Q:
			Node nullv = runQuote(operand);
			if ((ListNode) nullv == ListNode.EMPTYLIST) {
				return new BooleanNode(true);
			} else {
				return new BooleanNode(false);
			}
		case ATOM_Q:
			Node kind_of_node = operand.car();

			if (kind_of_node instanceof IntNode) {
				return new BooleanNode(true);
			}

			else if (kind_of_node instanceof BooleanNode) {
				return new BooleanNode(true);
			}

			else if (kind_of_node instanceof QuoteNode) {
				Node listckeck = runQuote(operand);

				if (listckeck instanceof IdNode) {
					return new BooleanNode(true);
				} else if (listckeck instanceof IntNode) {
					return new BooleanNode(true);
				} else if ((ListNode) listckeck == ListNode.EMPTYLIST) {
					return new BooleanNode(true);
				}

				return new BooleanNode(false);
			}

			else {
				return new BooleanNode(false); // 기타연산쪽?
			}

		case EQ_Q: {
			Node eq1 = operand.car();
			Node eq2 = operand.cdr().car();

			if (eq1 instanceof ListNode) {
				if (((ListNode) eq1).car() instanceof QuoteNode) {
					eq1 = runQuote((ListNode) eq1);
					if (eq1 instanceof ListNode)
						return new BooleanNode(false);
				}
			}
			if (eq2 instanceof ListNode) {
				if (((ListNode) eq2).car() instanceof QuoteNode) {
					eq2 = runQuote((ListNode) eq2);
					if (eq2 instanceof ListNode)
						return new BooleanNode(false);
				}
			}

			if (eq1 instanceof IntNode) {
				return new BooleanNode(eq1.equals(eq2));
			}
			if (eq1 instanceof IdNode) {
				return new BooleanNode(eq1.equals(eq2));
			}
			if (eq1 instanceof BooleanNode) {
				return new BooleanNode(eq1.equals(eq2));
			}

		}

		case COND: {
			ListNode node = (ListNode) operand.car();

			if (!((BooleanNode) runExpr(node.car())).value) {
				return runFunction(operator, operand.cdr());
			} else {
				return node.cdr().car();
			}
		}
		case NOT: {
			if (operand.car() instanceof BooleanNode) {
				boolean not = ((((BooleanNode) runExpr(operand.car())).value));
				return new BooleanNode(!not);
			} else {
				boolean not = (((BooleanNode) runExpr(operand)).value);
				return new BooleanNode(!not);
			}
		}
		default:
			break;
		}
		return null;

	}

	private Node stripList(ListNode node) {
		if (node.car() instanceof ListNode && node.cdr() == ListNode.EMPTYLIST) {
			Node listNode = node.car();
			return listNode;
		} else {
			return node;
		}
	}

	private Node runBinary(ListNode list) {

		BinaryOpNode operator = (BinaryOpNode) list.car();

		int fnum = ((IntNode)  runExpr(list.cdr().car())).getValue();
		int snum = ((IntNode)  runExpr(list.cdr().cdr().car())).getValue();

		switch (operator.binType) {

		case PLUS: {
			int plus = fnum + snum;
			return new IntNode(Integer.toString(plus));
		}
		case MINUS: {
			int minus = fnum - snum;
			return new IntNode(Integer.toString(minus));
		}
		case TIMES: {
			int times = fnum * snum;
			return new IntNode(Integer.toString(times));
		}
		case DIV: {
			int div = fnum / snum;
			return new IntNode(Integer.toString(div));
		}
		case GT: {
			return new BooleanNode(fnum > snum);
		}
		case LT: {
			return new BooleanNode(fnum < snum);
		}
		case EQ: {
			return new BooleanNode(fnum == snum);
		}
		default:
			break;
		}
		return null;
	}

	private Node runQuote(ListNode node) {
		return ((QuoteNode) node.car()).nodeInside(); 
	}
}