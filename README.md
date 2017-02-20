# connect-four-cli

connect-four-cli provides a simple command line interface for a drop tile game similar to the popular Connect Four. 

## Setup
### Run the Game
This is a java program that is playable through the command line. To use, please download or clone this repository. Ensure java is installed. This is written with Java 8.
To compile all code and tests, navigate to the repository home directory, and run:
`javac src/* -d ./bin -cp "./lib/junit.jar:./lib/hamcrest.jar"`

Next, to begin a game, run:
`java -cp "./bin" ConnectFour`

### Run the Tests
Tests are written in JUnit. To run the tests, make sure you have compiled using the command
above for compiling with javac.

Next to run the Board unit test, run:
`java -cp "./lib/junit.jar:././lib/junit.jar:./lib/hamcrest.jar:./bin" org.junit.runner.JUnitCore BoardTest`

To run the ConnectFour integration test, run:


## Rules
This is a two player game.
Players alternate turns, and can drop a tile into one of the 4 columns. These fall to the bottom of the column (landing on top of whatever tiles are already present in that column. 0 represents empty spaces, 1 those with player 1's tiles, and 2 those with player 2's tiles.

## Commands
`PUT n` where n is between 1 and 4 inclusive allows a player to place a piece.

`GET` allows the player to get a list of the pieces previously placed in order of placement.

`BOARD` allows the player to get a visual representation of the current board.

`EXIT` allows the players to end the game.

## Win Condition
Whoever is the first to get 4 tiles in a row either horizontally, vertically, or diagonally, wins.
