package com.text.analysis;

class Base {
	private int data;

	public Base() {
		data = 5;
	}

	public int gatData() {
		return this.data;
	}
}

class Derived extends Base {
	private int data;

	public Derived() {
		data = 6;
	}

	public int gatData() {
		return this.data;
	}
}

class demo {
	int a, b;

	demo() {
		a = 10;
		b = 20;
	}

	public void print() {
		System.out.println("a=" + a + "b=" + b + "n");
	}
}

public final class Ultimetric {

	public static void main(String[] args) {
		demo d1 = new demo();
		demo d2 = d1;
		d1.a += 1;
		d1.b += 1;
		d1.print();
		d2.print();
	}
}
