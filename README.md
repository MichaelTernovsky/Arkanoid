# Arkanoid Game
Made by Michael Ternovsky.
That implementation of the classic Arkanoid game which was written in Java as a project in the Object-Oriented course I took at Bar Ilan University.

The game has 3 level sets which you can enjoy.

# Make File
The makefile has 3 targets:

compile: compiles all packages in the src directory, using the resources in the directory with the same name, and using the biuoop jar and the tools provided by it.

run: runs the game after compiling with the "compile" target.

jar: generates the portable (uber - "fat") jar, which can later be moved and executed from any other computer that runs JVM, without the need for any other files.
