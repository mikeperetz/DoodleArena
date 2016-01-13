=================
Doodle Arena 1.0
=================

Instructions
-------------
Use the arrow keys to move around the screen. If you touch an enemy that is larger than you, the game will end. If you touch an enemy that is smaller than you, your score and size will increase proportionally to the size of that enemy. How long can you survive?


How to Run
-------------
You must have Java Runtime Environment 1.8 or higher installed. The .java files can be compiled normally (http://stackoverflow.com/a/16137745). There is also a pre-compiled .jar file included, but your mileage may vary. 


About
-------------
DoodleArena.java contains the main class. It is responsible for instantiating the runnable and creating the Board.

Board.java ties together the program. It handles both the GUI and the state of the game. It constantly checks the positions of the player and enemies. Functionality during this check includes destroying enemies that travel out-of-bounds, spawning new enemies, and re-drawing elements at their new positions.

Player.java is the Player object. It is responsible for sensing key-presses and keeping the player in-bounds.

Doodle.java is the Enemy object. It is responsible for checking if the enemy is in-bounds, sensing collisions with the player, updating the player's size and sending the "game over" signal.

manifest.txt is needed to compile a .jar file.


TODO
-------------
1) Implement restart functionality
2) Extend the gameplay -- the game currently ends once the player gets too larger
3) Replace the shapes with sprites



