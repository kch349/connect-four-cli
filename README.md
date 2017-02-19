# connect-four-cli

connect-four-cli provides a simple command line interface for a drop tile game similar to the popular Connect Four. 

## Setup
This is a java program that is playable through the command line. To use, please download or clone this repository. Ensure java is installed. Navigate to the repository, and run:
`java javac dropTile.py`

Next, to begin a game, run:
`java java dropTile`

## Rules
This is a two player game.
Players alternate turns, and can drop a tile into one of the 4 columns. These fall to the bottom of the column (landing on top of whatever tiles are already present in that column. 0 represents empty spaces, 1 those with player 1's tiles, and 2 those with player 2's tiles.

## Commands
`java PUT n`
where n is between 1 and 4 inclusive.
This command allows a player to place a piece.

`java GET`

This command allows the player to get a list of the pieces previously placed.

`java BOARD`

This command allows the player to get a visual representation of the current board.

## Win Condition
Whoever is the first to get 4 tiles in a row either horizontally, vertically, or diagonally, wins.
