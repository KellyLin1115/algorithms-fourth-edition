package chapter1.item3;

import java.util.Stack;

/**
 * Arithmetic expression evaluation. Evaluate.java is a stack client that evaluates fully parenthesized arithmetic expressions. It uses Dijkstra's 2-stack algorithm:
 * Push operands onto the operand stack.
 * Push operators onto the operator stack.
 * Ignore left parentheses.
 * On encountering a right parenthesis, pop an operator, pop the requisite number of operands, and push onto the operand stack the result of applying that operator to those operands.
 */
public class Evaluate {
    public static void main(String[] args) {
        String expression = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        String[] expr = expression.split(" ");

        Stack<String> ops  = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        for(int i = 0; i < expr.length; i++){
            String s = expr[i];
            if      (s.equals("("))               ;
            else if (s.equals("+"))    ops.push(s);
            else if (s.equals("-"))    ops.push(s);
            else if (s.equals("*"))    ops.push(s);
            else if (s.equals("/"))    ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if      (op.equals("+"))    v = vals.pop() + v;
                else if (op.equals("-"))    v = vals.pop() - v;
                else if (op.equals("*"))    v = vals.pop() * v;
                else if (op.equals("/"))    v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            else vals.push(Double.parseDouble(s));
        }
        System.out.println(vals.pop());
    }
}
