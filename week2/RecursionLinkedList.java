package H02;

import org.omg.Messaging.SyncScopeHelper;

public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;

	/**
	 * 새롭게 생성된 노드를 리스트의 처음으로 연결
	 */
	private void linkFirst(char element) {
		head = new Node(element, head);
	}

	/**
	 * 과제 (1) 주어진 Node x 의 마지막으로 연결된 Node 의 다음으로 새롭게 생성된 노드를 연결
	 *
	 * @param element
	 *            데이터
	 * @param x
	 *            노드
	 */
	private void linkLast(char element, Node x) {
		// 채워서 사용, recursion 사용
		if(x.next == null) {
			linkNext(element,x);
			return;
		}
		else {
			linkLast(element, x.next);
			return;
		}
	}

	/**
	 * 이전 Node 의 다음 Node 로 새롭게 생성된 노드를 연결
	 *
	 * @param element
	 *            원소
	 * @param pred
	 *            이전노드
	 */
	private void linkNext(char element, Node pred) {
		Node next = pred.next;
		pred.next = new Node(element, next);
	}
	/**
	 * 추가 과제 (5) 현재 노드의 이전 노드부터 리스트의 끝까지를 거꾸로 만듬 ex)노드가 [s]->[t]->[r]일 때, reverse 실행
	 * 후 [r]->[t]->[s]
	 * 
	 * @param x
	 *            현재 노드
	 * @param pred
	 *            현재노드의 이전 노드
	 */
	private void reverse(Node x, Node pred) {
		// 채워서 사용, recursion 사용
		if(x.next == null) {
			linkLast(x.item, x);
			head = x.next;
			return;
		}
		else {
			reverse(x.next, pred);
			pred = x;
			linkLast(x.item, pred);
			unlinkNext(x);
			return;	
		}
	}

	/**
	 * 리스트를 거꾸로 만듬
	 */
	public void reverse() {
		reverse(head, null);
	}
	/**
	 * 리스트의 첫번째 원소 해제(삭제)
	 *
	 * @return 첫번째 원소의 데이터
	 */
	private char unlinkFirst() {
		Node x = head;
		char element = x.item;
		head = head.next;
		x.item = UNDEF;
		x.next = null;
		return element;
	}

	/**
	 * 이전 Node 의 다음 Node 연결 해제(삭제)
	 *
	 * @param pred
	 * 이전노드
	 * @return 다음노드의 데이터
	 */
	private char unlinkNext(Node pred) {
		Node x = pred.next;
		Node next = x.next;
		char element = x.item;
		x.item = UNDEF;
		x.next = null;
		pred.next = next;
		return element;
	}
	
	/**
	 * 과제 (2) x 노드에서 index 만큼 떨어진 Node 반환
	 */
	private Node node(int index, Node x) {
		if(index == 0) {
			return x;
		}
		else {
			return node(index-1, x.next);// 채워서 사용, recursion 사용
		}
	}
	public char get(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);
		return node(index, head).item;
	}

	/**
	 * 과제 (3) 노드로부터 끝까지의 리스트의 노드 갯수 반환
	 */
	private int length(Node x) {
		if(x.next == null) {
			return 1;
		}
		else {
			return length(x.next)+1;
		}
		// 채워서 사용, recursion 사용
		
	}

	/**
	 * 과제 (4) 노드로부터 시작하는 리스트의 내용 반환
	 */
	private String toString(Node x) {
		if(x == null) {
			return "()";
		}
		else {
			return x.item+" "+toString(x.next);
		}
		// 채워서 사용, recursion 사용
	}
	public String toString() {
		if (head == null)
			return "[ ]";
		return "[" + toString(head) + "]";
	}

	

	/**
	 * 추가 과제 (6) 두 리스트를 합침 ( A + B ) ex ) list1 =[l]->[o]->[v]->[e] , list2=[p]->[l]
	 * 일 때, list1.addAll(list2) 실행 후 [l]->[o]->[v]->[e]-> [p]->[l]
	 * 
	 * @param x
	 *            list1의 노드
	 * @param y
	 *            list2 의 head
	 */
	private void addAll(Node x, Node y) {
		char temp = y.item;
		if(x.next == null) {
			if(y.next == null) {
				linkLast(temp,x);
				return;
			}
			else {
				linkLast(temp,x);
				addAll(x,y.next);
				return;
			}
		}
		else {
			addAll(x.next,y);
			return;
		}
		// 채워서 사용, recursion 사용
	}

	/**
	 * 두 리스트를 합침 ( this + B )
	 */
	public void addAll(RecursionLinkedList list) {
		addAll(this.head, list.head);
	}

	/**
	 * 원소를 리스트의 마지막에 추가
	 */
	public boolean add(char element) {
		if (head == null) {
			linkFirst(element);
		} else {
			linkLast(element, head);
		}
		return true;
	}

	/**
	 * 원소를 주어진 index 위치에 추가
	 *
	 * @param index
	 *            리스트에서 추가될 위치
	 * @param element
	 *            추가될 데이터
	 */
	public void add(int index, char element) {
		if (!(index >= 0 && index <= size()))
			throw new IndexOutOfBoundsException("" + index);
		if (index == 0)
			linkFirst(element);
		else
			linkNext(element, node(index - 1, head));
	}

	/**
	 * 리스트에서 index 위치의 원소 반환
	 */
	

	/**
	 * 리스트에서 index 위치의 원소 삭제
	 */
	public char remove(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);
		if (index == 0) {
			return unlinkFirst();
		}
		return unlinkNext(node(index - 1, head));
	}

	/**
	 * 리스트의 원소 갯수 반환
	 */
	public int size() {
		return length(head);
	}

	/**
	 * 리스트에 사용될 자료구조
	 */
	private static class Node {
		char item;
		Node next;

		Node(char element, Node next) {
			this.item = element;
			this.next = next;
		}
	}
}
