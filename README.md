# Software Design and Programming 2015
## Assignment One - Simple Machine Language (SML)

Revised the files given to us in the exercise:

* [Machine](src/sml/Machine.java) to handle missing arguments, 
to halt if a problem was found in inputing the SML program, ...
* [Translator](src/sml/Translator.java) found and fixed bug in `readAndTranslate` method, 
extended `getInstruction` to deal with other instructions,

Added new classes:
* [OutInstruction](src/sml/OutInstruction.java) to deal with `out` instruction.
* [ArithmeticInstruction](src/sml/ArithmeticInstruction.java) intermediate Abstract class to avoid code duplication in *AddInstruction*, *SubInstruction*, *MulInstruction* and *DivInstruction*
* [SubInstruction](src/sml/SubInstruction.java) to deal with `sub` instruction (really simple with a single constructor function).
* [MulInstruction](src/sml/MulInstruction.java) to deal with `mul` instruction.
* [DivInstruction](src/sml/DivInstruction.java) to deal with `div` instruction.
* [BnzInstruction](src/sml/BnzInstruction.java) to deal with `bnz` instruction (a bit more complex than others).


Use test `.sml` program files to develop. (TDD would be nice but a very large amount of work!):
* [add.sml](src/add.sml) basic test of `add` and `out` **N.B. use # as a comment to help my understanding**
[output](out/mul_sml_out.txt)
* [mul.sml](src/mul.sml) basic test of `mul`.produces [output](out/mul_sml_out.txt)
* [div.sml](src/div.sml) basic test of `div` produces [output](out/div_sml_out.txt)
* [count1to10.sml](src/count1to10.sml) test `bnz` by looping to print 1, 2, ... 10 produces [output](out/count1to10_sml_out.txt)
* [code.sml](src/code.sml) the SML program we were given to find factorial 6, produces [output](out/code_sml_out.txt)



