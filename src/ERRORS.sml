# various malformed instructions to test that each throws a 
# sensible exception. Crude procedure used add new test to
# top of file and record its output



l1 lin 20 1 
l2 bnz 20 foobar # bnz jump to non-existent label

###l2 bnz 200 l1 # invalid register in bnz

###fz add -1 1 2 # invalid register in add

###l1 bnz 1 # malformed bnz no label to jump to

###l1 foo 1 2 # unrecognized insruction


###l1 out # malformed out - no register given

###f3 out 32 # out with invalid register (maximum is 32)

