import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xy16 on 16-11-25.
 */
public class Calculator {
	public static void main(String[] args) {
		Stack stack = new Stack();
		ArrayList list = new ArrayList();
		Pattern entryOfExpression = Pattern.compile("[0-9]+(\\.[0-9]+)?|\\+|-|\\*|/|\\(|\\)");
		String expression = args[0];
		Matcher m = entryOfExpression.matcher(expression);

		while (m.find()) {
			String node = expression.substring(m.start(), m.end());

			// handle number
			if(node.matches("[0-9].*")) {
				list.add(Double.valueOf(node));
			}

			// handle operator
			else {
				Op op = new Op(node);
				int peekLevel = (stack.isEmpty())?0:((Op)(stack.peek())).level;
				if(op.level > peekLevel) {
					stack.push(op);
				}
				//when meet '('
				else if(op.level == -3) {
					stack.push(op);
				}
				//when meet ')'
				else if(op.level == -1) {
					Op tempOp = (Op)stack.pop();
					while(tempOp.level != -3)
					{
						list.add(tempOp);
						tempOp = (Op)stack.pop();
					}
				}
				else {
					Op tempOp = (Op) (stack.pop());
					while (tempOp.level >= op.level) {
						list.add(tempOp);
						if (stack.isEmpty()) break;
						tempOp = (Op) stack.pop();
					}
				}
			}
		}
		Op tempOp = null;
		while (!stack.isEmpty()) {
			tempOp = (Op)stack.pop();
			list.add(tempOp);
		}

		for(Object o:list) {
			if(o instanceof Double) {
				stack.push(o);
			}
			else {
				if(stack.isEmpty()) break;
				double opd1 = (Double)stack.pop();
				double opd2 = (Double)stack.pop();
				switch (((Op)o).op) {
					case '+':
						stack.push(opd1+opd2);
						break;
					case '-':
						stack.push(opd1-opd2);
						break;
					case '*':
					case 'x':
					case 'X':
						stack.push(opd1*opd2);
						break;
					case '/':
						stack.push(opd1/opd2);
						break;
				}
			}
		}
		System.out.println("Result : " + stack.pop());
	}
}

class Op {
	char op;
	int level;
	public Op(String op) {
		this.op = op.charAt(0);
		if(op.equals("+") || op.equals("-"))
			this.level = 1;
		else if(op.equals("*") || op.equals("x")
				  || op.equals("X")|| op.equals("/"))
			this.level = 2;
		else if(op.equals("("))
			this.level = -3;
		else
			this.level = -1;

	}
}
