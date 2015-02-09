# count 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
a lin 29 0 	# use register 29 for the COUNT start at zero
b lin 1 1 	# store 1 in register 1 
c lin 10 10 	# store 10 in register 10
d add 29 29 1 	# add one to COUNT 
e out 29	# print COUNT
f sub 30 29 10  # reg 30 is COUNT-10 
g bnz 30 d	# unless reg 30 is zero jump up to d
