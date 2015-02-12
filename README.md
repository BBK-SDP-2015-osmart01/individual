# Software Design and Programming 2015
## Assignment One - Simple Machine Language (SML)

Revised the files given to us in the exercise:

* [Machine](src/sml/Machine.java) to handle missing arguments, 
to halt if a problem was found in inputting the SML program *BUT
reverted back to original version having read Keith's Moodle Message `Leave the code alone!`"*
* [Translator](src/sml/Translator.java) found and fixed bug in `readAndTranslate` method, 
extended `getInstruction` to deal with other instructions, and then converted to use
reflection to load classes rather explicit calls to classes.

Added new classes:
* [OutInstruction](src/sml/OutInstruction.java) to deal with `out` instruction.
* [BnzInstruction](src/sml/BnzInstruction.java) to deal with `bnz` instruction (a bit more complex than others).
* [ArithmeticInstruction](src/sml/ArithmeticInstruction.java) intermediate Abstract class to avoid code duplication in *AddInstruction*, *SubInstruction*, *MulInstruction* and *DivInstruction*
* [SubInstruction](src/sml/SubInstruction.java) to deal with `sub` instruction (really simple with a single constructor function).
* [MulInstruction](src/sml/MulInstruction.java) to deal with `mul` instruction.
* [DivInstruction](src/sml/DivInstruction.java) to deal with `div` instruction.
* [ModInstruction](src/sml/ModInstruction.java). This is a new class to test in practice the exercise sheet assertion that reflection *will enable the SML language to be extended without having to modify the original code.*
This is the case and the use of the abstract class allows this extension to be made very efficiently.



Use test `.sml` program files to develop. (TDD would be nice but a very large amount of work!):
* [add.sml](src/add.sml) basic test of `add` and `out` **N.B. use # as a comment to help my understanding**
[output](out/add_sml_out.txt)
* [mul.sml](src/mul.sml) basic test of `mul`.produces [output](out/mul_sml_out.txt)
* [div.sml](src/div.sml) basic test of `div` produces [output](out/div_sml_out.txt)
* [mod.sml](src/mod.sml) test new `mod` instruction produces [output](out/mod_sml_out.txt)
* [count1to10.sml](src/count1to10.sml) test `bnz` by looping to print 1, 2, ... 10 produces [output](out/count1to10_sml_out.txt)
* [code.sml](src/code.sml) the SML program we were given to find factorial 6, produces [output](out/code_sml_out.txt)
* [outERROR.sml](src/outERROR.sml) ERROR handling on out: giving illegal register 32 produces sensible exception.



