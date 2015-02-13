# various malformed instructions to test that each throws a 
# sensible exception. Crude procedure used add new test to
# top of file and record its output

# out with invalid register (maximum is 32)
f3 out 32

# malformed out - no register given
###l1 out
