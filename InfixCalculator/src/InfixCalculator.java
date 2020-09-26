/* Name: Christina Liu
   NetID: cliu61	
   Class: CSC 172  
   Assignment: Project 1
 */
//Import the java libraries that were used
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class InfixCalculator {
	
	//Method to determine precedence of each operator
	public static int precedence(String operator) {
		//Operators are listed in order of precedence, including parenthesis
		String opString = "!|&=<>+-*/%^()";
		//Operators are assigned an integer value from index that is multiplied by one half as there are doubles
		return(int) Math.floor(opString.indexOf(operator) * 0.5);
	}
	//Method to first convert infix to postfix with the end result being a queue
	public static Queue<String> infixToPostfix(String line) {
		//Stack and queue used to store operators and operands
		Queue<String> q = new Queue<String>();
		Stack<String> s = new Stack<String>();
		//Split line of file according to spacing
		String[] infixExpression = line.split(" ");
		//Used to determine operators from operands, including parenthesis
		String operators = "!|&=<>+-*/%^()";
		//Loop to run through each character of length of the string
		for(int i = 0; i < infixExpression.length; i++) {
			//Enqueue operands
			if(operators.indexOf(infixExpression[i]) == -1) {
				q.enqueue(infixExpression[i]);
			}
			else {
				//When there is a right parentheses then pop and enqueue while there is no left parentheses and the stack is not empty
				if(infixExpression[i].equals(")")) {
					while(!s.isEmpty() && s.peek().equals("(") == false) {
						q.enqueue(s.pop());
					}
					s.pop();
				}
				//When there is no right parentheses then pop and enqueue until an operator of equal of lower precendence is reached
				else {
					while(!s.isEmpty() && s.peek().equals("(") == false) {
						if(precedence(s.peek()) >= precedence(infixExpression[i])) {
							q.enqueue(s.pop());
						}
						else {
							break;
						}
					}
					//Push operators into stack
					s.push(infixExpression[i]);
				}
			}
		}
		//Enqueue any remaining operators should the stack not be empty before returning queue
		while(s.isEmpty() == false) {
			q.enqueue(s.pop());
		}
		return q;
	}
	
	//Method to evaluate the postfix expression we have from the previous method
	public static String postfixEval(Queue<String> postfixExp) {
		//Operators are written out in this string in order they would be conducted, excluding parentheses
		String operators = "+-*/<>=&|!^%";
		//New stack used for evaluating the values of operators and operands
		Stack<String> evaluate = new Stack<String>();
		//The item being processed
		String item;
		//The index the operator belongs
		int operatorIndex;
		//Operands as different types and result as string
		String numSA, numSB;
		double numA, numB;
		boolean numBA, numBB;
		String result;
		//Format numbers to end at hundredths place
		DecimalFormat h = new DecimalFormat("0.00");
		
		//Loops while not empty
		while(postfixExp.isEmpty() == false) {
			item = postfixExp.dequeue();
			//if the token is an operand, push it onto the stack
			if(operators.indexOf(item) == -1) {
				evaluate.push(item);
			}
			else{
				//Makes sure operators aren't leftover from previous round
				numSA = numSB = result = "";
				numA = numB = 0;
				numBA = numBB = false;
				//Using the string of operators, the index can be determined
				operatorIndex = operators.indexOf(item);
				//Chooses which operator is used and determines the output based on index using switch
				switch(operatorIndex) {
				//Addition where the operands are added together
				case 0: numSA = evaluate.pop();
						numSB = evaluate.pop();
						try {
							numA = Double.parseDouble(numSA); //Change to double
							numB = Double.parseDouble(numSB); //Change to double
						}
						catch(NumberFormatException nfe) { //Check if input format is valid
							System.out.println("Format error: " + nfe.getMessage()); 
							return "Please check the expression is valid.";
						}
						result = Double.toString((numA + numB)); //Conducts operation
						evaluate.push(result); //Pushes out evaluated result
						break;
				//Subtraction where the first operand is subtracted from the second	
				case 1: numSA = evaluate.pop();
						numSB = evaluate.pop();
						try {
							numA = Double.parseDouble(numSA);
							numB = Double.parseDouble(numSB);
						}
						catch(NumberFormatException nfe) {
							System.out.println("Format error: " + nfe.getMessage());
							return "Please check the expression is valid.";
						}
						result = Double.toString((numB - numA));
						evaluate.push(result);
						break;
				//Multiplication where the two operands are multiplied by each other
				case 2: numSA = evaluate.pop();
						numSB = evaluate.pop();
						try {
						numA = Double.parseDouble(numSA);
						numB = Double.parseDouble(numSB); 
						}
						catch(NumberFormatException nfe) {
							System.out.println("Format error: " + nfe.getMessage());
							return "Please check the expression is valid.";
						}
						result = Double.toString((numA * numB));
						evaluate.push(result);
						break;
				//Division where the second operand is divided by the second
				case 3: numSA = evaluate.pop();
						numSB = evaluate.pop();
						try {
							numA = Double.parseDouble(numSA);
							numB = Double.parseDouble(numSB);
						}
						catch(NumberFormatException nfe) {
							System.out.println("Format error: " + nfe.getMessage());
							return "Please check the expression is valid.";
						}
						if(numA != 0) {
							result = Double.toString((numB / numA));
						}
						else {
							return "No answer can be provided as numbers cannot be divided by zero.";
						}
						evaluate.push(result);
						break;
				//Less than using 1 and 0 to denote true or false
				case 4: numSA = evaluate.pop();
						numSB = evaluate.pop();
						try {
							numA = Double.parseDouble(numSA);
							numB = Double.parseDouble(numSB);
						}
						catch(NumberFormatException nfe) {
							System.out.println("Format error: " + nfe.getMessage());
							return "Please check the expression is valid.";
						}
						if(numB < numA) {
							result = "1";
						}
						else {
							result = "0";
						}
						evaluate.push(result);
						break;
				//Greater than using 1 and 0 to denote true or false
				case 5: numSA = evaluate.pop();
						numSB = evaluate.pop();
						try {
							numA = Double.parseDouble(numSA);
							numB = Double.parseDouble(numSB);
						}
						catch(NumberFormatException nfe) {
							System.out.println("Format error: " + nfe.getMessage());
							return "Please check the expression is valid.";
						}
						if(numB > numA) {
							result = "1";
						}
						else {
							result = "0";
						}
						evaluate.push(result);
						break;
				//Equal to using 1 and 0 to denote true or false
				case 6: numSA = evaluate.pop();
						numSB = evaluate.pop();
						try {
							numA = Double.parseDouble(numSA);
							numB = Double.parseDouble(numSB);
						}
						catch(NumberFormatException nfe) {
							System.out.println("Format error: " + nfe.getMessage());
							return "Please check the expression is valid.";
						}
						if(numA == numB) {
							result = "1";
						}
						else {
							result = "0";
						}
						evaluate.push(result);
						break;					
				//And using 1 and 0 to denote true or false
				case 7: numSA = evaluate.pop();
						numSB = evaluate.pop();
						if(numSA.equals("1")) {
							numBA = true;
						}
						else {
							numBA = false;
						}
						if(numSB.equals("1")) {
							numBB = true;
						}
						else {
							numBB = false;
						}
						if(numBA && numBB) {
							result = "1";
						}
						else {
							result = "0";
						}
						evaluate.push(result);
						break;	
				//Or using 1 and 0 to denote true or false
				case 8: numSA = evaluate.pop();
						numSB = evaluate.pop();
						if(numSA.equals("1")) {
							numBA = true;
						}
						else {
							numBA = false;
						}
						if(numSB.equals("1")) {
							numBB = true;
						}
						else {
							numBB = false;
						}
						if(numBA || numBB) {
							result = "1";
						}
						else {
							result = "0";
						}
						evaluate.push(result);
						break;	
				//Not using 1 and 0 to denote true or false
				case 9: numSA = evaluate.pop();
					if(numSA.equals("1")) {
						numBA = true;
					}
					else {
						numBA = false;
					}
					if(!numBA == true) {
						result = "1";
					}
					else {
						result = "0";
					}
					evaluate.push(result);
					break;
				//EXTRA: Exponent using first operand to the power of second operand
				case 10: numSA = evaluate.pop();
						 numSB = evaluate.pop();
				try {
					numA = Double.parseDouble(numSA);
					numB = Double.parseDouble(numSB);
				}
				catch(NumberFormatException nfe) {
					System.out.println("Format error: " + nfe.getMessage());
					return "Please check the expression is valid.";
				}
				result = Double.toString((Math.pow(numA,numB)));
				evaluate.push(result);
				break;
				//EXTRA: Modulus using second operand mod first operand
				case 11: numSA = evaluate.pop();
						 numSB = evaluate.pop();
						try {
							numA = Double.parseDouble(numSA);
							numB = Double.parseDouble(numSB);
						}
						catch(NumberFormatException nfe) {
							System.out.println("Format error: " + nfe.getMessage());
							return "Please check the expression is valid.";
						}
						result = Double.toString((numB%numA));
						evaluate.push(result);
						break;
				}
			}
		}
		//Pops out evaluated number from the stack
		String finalResult = evaluate.pop();
		//Assures final number has two decimal places if not originally valid
		try{
		Double finalResultDouble = Double.parseDouble(finalResult);
		finalResult = h.format(finalResultDouble);
		}
		catch(NumberFormatException nfe) {
			System.out.println("Format error: " + nfe.getMessage());
			return "Please check the expression is valid.";
		}
		//The stack should be empty and thus the result can be returned
		if(evaluate.isEmpty() == true) {
			return finalResult;
		}
		//Stack was not empty so there were multiple answers
		else {
			return "Please double check input, cannot determine one answer.";
		}	
	}
	//Method to format the strings to match the basis document provided
	public static String format(String line) {
		//Determine operators, including parentheses
		String operators = "!|&=<>+-*/%^()";
		//Replaces double spaces with single spaces
		line = line.replaceAll(" ","");
		//New string builder is created with result
		StringBuilder result = new StringBuilder();
		//Run through the length of the line
		for (int i = 0; i < line.length(); i++) {
			//Determine if minus sign is negative sign or operator by seeing if it is at beginning and if there are operands before or after
			if(operators.indexOf(line.charAt(i)) == 7 && i != 0) {
				if(operators.indexOf(line.charAt(i - 1)) == -1 && operators.indexOf(line.charAt(i + 1)) == -1) {
					result.append(" ");
				    result.append(line.charAt(i));
				    result.append(" ");
			}
			else {
				//Character previous is an operand
				if(operators.indexOf(line.charAt(i - 1)) == -1) {
					result.append(" ");
				    result.append(line.charAt(i));
				}
					else {
						//Character after is an operand
						if(operators.indexOf(line.charAt(i + 1)) == -1){
						result.append(" ");
					    result.append(line.charAt(i));
					}
						else {
							if(operators.indexOf(line.charAt(i - 1)) != -1 && operators.indexOf(line.charAt(i + 1)) != -1) {
								result.append(" ");
							    result.append(line.charAt(i));
							    result.append(" ");
							}
						}
					}
				}
			}
			else {
				//Determines if subtraction or negative sign
				if(operators.indexOf(line.charAt(i)) == 7 && i == 0) {
					result.append(line.charAt(i));
				}
				else {
					//if the character is an operator that is not at the beginning of the line
				  if (operators.indexOf(line.charAt(i)) != -1 && i != 0) {
				      result.append(" ");
				      result.append(line.charAt(i));
				      result.append(" ");
				  }
				  else {
					  //Add a space for operator, or if operand then no need
					  if(operators.indexOf(line.charAt(i)) != -1){
						  result.append(line.charAt(i));
					      result.append(" ");
					  }
					  else {
						  result.append(line.charAt(i));
					  }
				  }
			  }
		   }
	   }
		//Update the current line from string builder and yet again correct spacing as needed
		line = result.toString();
		line = line.replaceAll("  ", " ");
		return line;
	}
	//Main method to execute calculator in conjunction with files
	public static void main(String[] args) throws IOException {
		//Create file reader to read determined file and write an output
		File file = new File(args[0]);
		FileReader input = new FileReader(file);
		BufferedReader reader = new BufferedReader(input);
		File output = new File(args[1]);
		//Creates a file for the program to write output onto if it has yet to exist
		if (!output.exists()) {
			output.createNewFile();
		}
		//Write the onto the file line by line
		FileWriter fileWriter = new FileWriter(output.getAbsoluteFile());
		BufferedWriter writer = new BufferedWriter(fileWriter);
		String line;
		//Continues to read file until no more lines
		while ((line = reader.readLine()) != null) {
			//Format the the lines
			line = format(line);
			//Use a queue the infixtoPostfix method converts the string
			Queue<String> postfix = infixToPostfix(line);
			//Final result is the value the postfixEval method evaluates that is then written
			String finalResult = postfixEval(postfix);
			writer.write(finalResult);
			writer.flush();
			writer.newLine();
		}
		//Closing the reader and the writer now that it's job is finished
		reader.close();
		writer.close();
	}

}