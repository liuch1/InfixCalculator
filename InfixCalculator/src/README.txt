Name: Christina Liu	
Class: CSC 172  
Assignment: Project 1

Summary of Code:
InfixCalculator is a java program that reads a determined file and converts the infix expressions into postfix expressions using the Shunting Yard Algorithm before evaluating each expression and writing them onto a new output file
To run it please go through the following in terminal:
	java --version
	(Select path to where the file location of InfixCalculator.java is using cd *name*)
	javac *.java 
	java InfixCalculator *file name being read.txt* *file name being created/written on.txt*
	Ex. java InfixCalculator infix_expr_short.txt my_eval.txt
The code was first built by creating stack and queue implementations rather than being imported. I additionally created the classes Node and DoubleNode to respectively complement each of the Stack and Queue classes for organizational purposes.
Going off of that, in the InfixCalculator class the first thing the program does is it reads the designated file while creating a new output File if it doesn't already exist using an imported File, FileReader and BufferedReader.
From there the method to convert the infix expression to postfix using the Shunting Yard Algorithm is called where another method to determine precendence is used to help determine operation order.
Once the expression is converted, the method to evaluate postfix expressions is used to generate a value using switch with each operation having a different case with expressions to help with error in invalid expressions implemented.
From there a new file is generated and another method is used to help format writing the resulting values onto a new file.

Files:
InfixCalculator class with main method to read file and write new one, method to determine precedence of operators, method to convert infix to postfix, method to evaluate postfix, method to format lines in new file 
Queue class with constructor and methods needed for implementation without importing queue java library
	DoubleNode class needed for queue with constructor and print method
Stack class with constructor and methods needed for implementation without importing stack java library
	Node class for needed for stack with constructor and print method
README text file with contact info, summary of code, obstacles, list of files
infix_expr_short.txt identically copied from the provided copy as a basis for testing expressions
postfix_eval_short.txt created using the InfixCalculator program with infix_expr_short.txt as the file read, output is identical to the sample provided
additional_test_expr.txt with all the operators being tested including additional tests on ^ % text to support invalid expression, and whether spacing would affect anything
addtional_test_result.txt created using the InfixCalculator program with additional_test_expr.txt as the file read
