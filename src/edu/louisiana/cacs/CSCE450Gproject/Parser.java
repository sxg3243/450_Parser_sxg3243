package edu.louisiana.cacs.CSCE450Gproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Parser {

	Stack myStack=new Stack();
	String file;
	String[] input_exp;
	String fileName;
	int last;
	String top_of_stack;
	String[] expr;
	int look_a_head;
	String action_value;
	String action_lookup;
	String[] stack;
	String stack_action;
	String str1="E+T";
	String str2="T";
	String str3="T*F";
	String str4="F";
	String str5="(E)";
	String str6="id";
	int length_of_RHS;
	String value_of_LHS;
	String[] exp;
	String goto_value;
	String goto_lookup;
	String temp1;
	String below;
	public Parser(String fileName){
		System.out.println("File to parse : "+fileName);
		file=fileName;
		
	}
	/*public Parser(String[] in){
		input_exp=in;
	}*/
	public void parse() throws IOException{
		//Retrieves the data from file into string array and invokes the actual parsing technique
		

		FileInputStream fis = null;
        BufferedReader reader = null;
      
        try {
            fis = new FileInputStream("src/sample.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
            //System.out.println("Reading File line by line using BufferedReader");
          
            String line = reader.readLine();
            int x=0;
            while(line != null){
                //input_exp[x]=(line.toCharArray()).toString();
                line = reader.readLine();
            }           
            String[] input_exp = line.split("");
        }  finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                  }
        }     
		expr=input_exp;
		while(expr[look_a_head]!=" "){	
		
			action(top_of_stack,expr[look_a_head]);
			//action_lookup=action_lookup.substring(0, 1)+top_of_stack+action_lookup.substring(1, 2)+expr[look_a_head]+action_lookup.substring(2, action_lookup.length());
			System.out.println("\t"+"\t"+action_lookup+"\t"+action_value+"\t"+value_of_LHS+"\t"+"\t"+goto_lookup+"\t"+goto_value+"\t"+stack_action+"\t"+"\t");
		}
	}

	private void action(String a, String b) {
		// TODO Auto-generated method stub
		if(b=="("){
			//action(0,();(4,();(6,(),(7,()
			if(a=="0"||a=="4"||a=="6"||a=="7"){
				shift("4");
				stack_action="push"+b+'4';
				action_value="s4";
				look_a_head++;
			}
			else{
				System.out.println("ERROR");
			}			
		}
		else if(b.contains("[a-zA-Z]+") == true){
			//action(0,id);(4,id);(6,id);(7,id)
			if(a=="0"||a=="4"||a=="6"||a=="7"){
				shift("5");
				stack_action="push"+b+'5';
				action_value="s5";
				look_a_head++;
			}
			else{
				System.out.println("ERROR");
			}
		}
		else if(b=="+"){
			//action(1,+);(8,+)
			if(a=="1"||a=="8"){
				shift("6");
				stack_action="push"+b+'6';
				action_value="s6";
				look_a_head++;
			}
			else if(a=="2"){
				reduce("2");
				action_value="r2";
			}
			else if(a=="3"){
				reduce("4");
				action_value="r4";
			}
			else if(a=="5"){
				reduce("6");
				action_value="r6";
			}
			else if(a=="9"){
				reduce("1");
				action_value="r1";
			}
			else if(a=="10"){
				reduce("3");
				action_value="r3";
			}
			else if(a=="11"){
				reduce("5");
				action_value="r5";
			}
			else{
				System.out.println("ERROR");
			}				
		}
		else if(b=="*"){
			if(a=="2"||a=="9"){
				shift("7");
				action_value="s7";
				look_a_head++;
			}
			else if(a=="3"){
				reduce("4");
				action_value="r4";
			}
			else if(a=="5"){
				reduce("6");
				action_value="r6";
			}
			else if(a=="10"){
				reduce("3");
				action_value="r3";
			}
			else if(a=="11"){
				reduce("5");
				action_value="r5";
			}
			else{
				System.out.println("ERROR");
			}
		}
		else if(b==")"){
			if(a=="2"){
				reduce("2");
				action_value="r2";
			}
			else if(a=="3"){
				reduce("4");
				action_value="r4";
			}
			else if(a=="5"){
				reduce("6");
				action_value="r6";
			}
			else if(a=="8"){
				shift("11");
				action_value="s11";
				look_a_head++;
			}
			else if(a=="9"){
				reduce("1");
				action_value="r1";
			}
			else if(a=="10"){
				reduce("3");
				action_value="r3";
			}
			else if(a=="11"){
				reduce("5");
				action_value="r5";
			}
			else{
				System.out.println("ERROR");
			}			
		}
		else if(b=="$"){
			if(a=="1"){
				System.out.println("ACCEPT");
			}
			else if(a=="2"){
				reduce("2");
				action_value="r2";
			}
			else if(a=="3"){
				reduce("4");
				action_value="r4";
			}
			else if(a=="5"){
				reduce("6");
				action_value="r6";
			}
			else if(a=="9"){
				reduce("1");
				action_value="r1";
			}
			else if(a=="10"){
				reduce("3");
				action_value="r3";
			}
			else if(a=="11"){
				reduce("5");
				action_value="r5";
			}
			else{
				System.out.println("ERROR");
			}
		}
	}

	

	private void shift(String i) {
		// TODO Auto-generated method stub
		String temp;		
		temp=expr[look_a_head];
		stack=myStack.push(temp,stack);
		top_of_stack=temp;
		stack=myStack.push(i,stack);
		top_of_stack=i;
		
	}
	private void reduce(String a) {
		// TODO Auto-generated method stub
		int b,i;
		String[] c;
		String d,temp;
		
		exp=expr;
		if(a=="1"){
			b=str1.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,exp);
			temp="E";
			stack=myStack.push(temp,stack);
			top_of_stack=temp;
			top_of_stack="E";
			value_of_LHS="E";
			for(i=stack.length;i>=0;i--)
			{
				boolean onlyDigits = stack[i].matches("\\d+");
				if(onlyDigits){
					below=stack[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+top_of_stack+"]";
			stack_action="push"+value_of_LHS+d;
		}
		else if(a=="2"){
			b=str2.length();
			length_of_RHS=b;
			b=b*2;
	 		c=myStack.pop(b,exp);
	 		temp="E";
			stack=myStack.push(temp,stack);
			top_of_stack=temp;
			top_of_stack="E";
			value_of_LHS="E";			
			for(i=stack.length;i>=0;i--)
			{
				boolean onlyDigits = stack[i].matches("\\d+");
				if(onlyDigits){
					below=stack[i];
					break;			
				}
			}			
			d=GOTO(below,top_of_stack);
			goto_lookup="["+c+","+top_of_stack+"]";
			stack_action="push"+value_of_LHS+d;
		}
		else if(a=="3"){
			b=str3.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,exp);
			temp="T";
			stack=myStack.push(temp,stack);
			top_of_stack="T";
			value_of_LHS="T";
			for(i=stack.length;i>=0;i--)
			{
				boolean onlyDigits = stack[i].matches("\\d+");
				if(onlyDigits){
					below=stack[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+top_of_stack+"]";
			stack_action="push"+value_of_LHS+d;
		}
		else if(a=="4"){
			b=str4.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,exp);
			temp="T";
			stack=myStack.push(temp,stack);
			top_of_stack=temp;
			value_of_LHS="T";
			for(i=stack.length;i>=0;i--)
			{
				boolean onlyDigits = stack[i].matches("\\d+");
				if(onlyDigits){
					below=stack[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+top_of_stack+"]";
			stack_action="push"+value_of_LHS+d;
		}
		else if(a=="5"){
			b=str5.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,exp);
			temp="F";
			stack=myStack.push(temp,stack);
			top_of_stack=temp;
			value_of_LHS="F";
			for(i=stack.length;i>=0;i--)
			{
				boolean onlyDigits = stack[i].matches("\\d+");
				if(onlyDigits){
					below=stack[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+top_of_stack+"]";
			stack_action="push"+value_of_LHS+d;
		}
		else if(a=="6"){
			b=str6.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,exp);
			temp="F";
			stack=myStack.push(temp,stack);
			top_of_stack=temp;
			value_of_LHS="F";
			for(i=stack.length;i>=0;i--)
			{
				boolean onlyDigits = stack[i].matches("\\d+");
				if(onlyDigits){
					below=stack[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+top_of_stack+"]";
			stack_action="push"+value_of_LHS+d;
		}
		else{
			System.out.println("UNGRAMMATICAL");
		}
		
	}

	private String GOTO(String a, String b) {
		// TODO Auto-generated method stub
		
		if(a=="0"){
			if(b=="E"){
				temp1="1";
				stack=myStack.push(temp1,stack);				
				top_of_stack=temp1;
				goto_value="1";
				
			}
			else if(b=="T"){
				temp1="2";
				stack=myStack.push(temp1,stack);
				top_of_stack=temp1;
				goto_value="2";
			}
			else if(b=="F"){
				temp1="3";
				stack=myStack.push(temp1,stack);
				top_of_stack=temp1;
				goto_value="3";
			}
			else{
				System.out.println("ERROR");
			}
		}
		else if(a=="4"){
			if(b=="E"){
				temp1="8";
				stack=myStack.push(temp1,stack);
				top_of_stack=temp1;
				goto_value="8";
			}
			else if(b=="T"){
				temp1="2";
				stack=myStack.push(temp1,stack);
				top_of_stack=temp1;
				goto_value="2";
			}
			else if(b=="F"){
				temp1="3";
				stack=myStack.push(temp1,stack);
				top_of_stack=temp1;
				goto_value="3";
			}
			else{
				System.out.println("ERROR");
			}
		}
		else if(a=="6"){
			if(b=="T"){
				temp1="9";
				stack=myStack.push(temp1,stack);
				top_of_stack=temp1;
				goto_value="9";
			}
			else if(b=="F"){
				temp1="3";
				stack=myStack.push(temp1,stack);
				top_of_stack=temp1;
				goto_value="3";
			}
			else{
				System.out.println("ERROR");
			}
		}
		else if(a=="7"){
			if(b=="F"){
				temp1="10";
				stack=myStack.push(temp1,stack);
				top_of_stack=temp1;
				goto_value="10";
			}
			else{
				System.out.println("ERROR");
			}
		}
		else{
			System.out.println("ERROR");
		}
		return goto_value;
	}
	public void printHeader() {
		// TODO Auto-generated method stub
		final String s1 = "-----------------------------------------------------------------------------------------------------------------------------------------------------------";
		System.out.format("%10s%15s%15s%15s%15s%15s%15s%15s%15s%20s",
				"stack", "	input tokens", "	action lookup", "	action value", "	value of LHS","	temp stack","	goto lookup","	goto value","	stack action","	sparse tree stack" );

		System.out.print("\n");
	}
}	


