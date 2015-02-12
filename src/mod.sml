# simple test of new mod function.
f0 lin 20 97	# store 97 in register 20
f1 lin 21 10 	# store 10 in register 21
f2 mod 10 20 21 # % the contents of registers 20 and 21 and store in register 10
f3 out 10	# print the contents of register 10, this should be 7 
# So the program should output 7 
