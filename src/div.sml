# simple test of div function.
f0 lin 20 9	# store 9 in register 20
f1 lin 21 -7	# store -7 in register 21
f2 div 10 20 21 # divide the contents of register 20 by register 21 and store in register 10
f3 out 10	# print the contents of register 10, this should be -1 
# So the program should output -1 
