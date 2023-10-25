package com.codingame;



public class Node {
	
	Node left, right;
	int value;
	Node current = this;
	
	public static void main(String[] args) {
		
		Node root = new Node(10);
		root.left = new Node(1);
		root.left.left = new Node(3);
		root.left.left.left = new Node(7);
		root.left.right = new Node(4);
		root.left.right.left = new Node(8);
		root.left.right.right = new Node(9);
		root.right = new Node(2);
		root.right.left = new Node(5);
		root.right.right = new Node(6);
		
	//	Node n = root.find_iteratif(2);
	//	System.out.println(n.value);
		boolean trouve = root.find_recursif(9) == null ? false : true;
		System.out.println(trouve);
	}
	
	public Node (int v) {
		this.value = v;
		this.left = null;
		this.right = null;
	}
	
	public Node (int v, Node g, Node d) {
		this.value = v;
		this.left = g;
		this.right = d;
	}
	
  Node find_recursif(int v) {
		
		if (current.value == v) return current;
		else if(v < value) {
			if (left == null) return null;
			else return left.find_recursif(v);
		}
		else {
			if (right == null) return null;
			else return right.find_recursif(v);
		}
		
	}
	
	Node find_iteratif(int v) {
		//System.out.println(current.value+" | "+v);
		while (current != null && current.value != v) {
			System.out.println(current.value+" | "+v);
			if (current.value == v) return current;
			
			// current = v < current.value : current.left : current.right;
			if (v < current.value) {
				current = current.left;
			}
			else {
				current = current.right;
			}
		}
		
		if(current.value == v) return current;
		return null;
	}

}