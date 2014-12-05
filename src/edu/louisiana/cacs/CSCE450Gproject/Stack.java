package edu.louisiana.cacs.CSCE450Gproject;


public class Stack {

	public String[] push(String expr, String[] stack) {
		int a=stack.length;
		a++;
		stack[a++]=expr;
		return stack;
		// TODO Auto-generated method stub

		
		
	}

	public String[] pop(int b, String[] exp) {
		// TODO Auto-generated method stub
		int a=exp.length;
		
		for(int i=0;i<b;i++){
			exp[a]="";
			a--;
		}
		return exp;
	}

	

}

