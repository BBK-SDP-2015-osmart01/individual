# Software Design and Programming 2015
## Assignment One - Simple Machine Language (SML)

Revised the files given to us in the exercise:

* [Machine](src/sml/Machine.java) to handle missing arguments, 
to halt if a problem was found in inputing the SML program, ...
* [Translator](src/sml/Translator.java) found and fixed bug in `readAndTranslate` method, 
extended `getInstruction` to deal with other instructions,

Added new classes:
* [OutInstruction](src/sml/OutInstruction.java) to deal with `out` instruction.
* [SubInstruction](src/sml/SubInstruction.java) to deal with `sub` instruction - rather nasty cut, paste and modify a couple of lines from add.
* [MulInstruction](src/sml/SubInstruction.java) to deal with `mul` instruction.


Use test `.sml` program files
* [add.sml](src/add.sml) basic test of `add` and `out` **N.B. use # as a comment to help my understanding**
* [mul.sml](src/mul.sml) basic test of `mul`.




