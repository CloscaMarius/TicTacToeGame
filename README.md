# TicTacToeGame

###### Implement the TicTacToe ('three in a row') game:

- there is an empty board, size 3x3;
- played by 2 players, each marking a cell of the board with his mark
  ('X' or '0'), taking turns;
- first one which has 3 of his marks in a line (in any direction) wins;
- if table fills up first, it's a tie (nobody wins).

___

###### Implement the basic game first:

- should be able to play 1 game;
- for displaying the board after each move: use a simple text mode UI, just display the rows each on a separate line,
  with cell contents separated by one space, each cell shown as: 'X', 'O' or '_' (for empty);
- get the position which the player want to mark by reading from console 2 integer values (one for the row and one for
  the column of the cell);
- should validate the attempted move is valid;
- should detect when game ends, and display the winner.

___

###### Improve user experience

- allow players to specify the cell by a single string value like `'A2'`, where the first letter ('A'..'C') represents
  the row, and the number after it ('1'..'3') represents the column;
- for better visualization, display the column number (1..3) above each of the board columns (like a header row), and
  the row letter (A..C)
  in front of each row.

___

###### Configurable player names

- read the names of the two players at the start of game;
- display them when reading the next move, and on final results.

___

###### Support playing multiple games in a row:

- at the end of one game, ask the user if he wants to continue with another game (Y/N);
- if he chooses Y, start a new game (resetting the board, etc);
- you need to keep the **overall score** (considering all played games), which you should update and display at the end
  of each game;
- the **starting player** should alternate between games: first game stars with Player1, next one with Player2, next one
  with Player1 again etc..

___

###### ASCII graphics

Improve your display of the board by using the special ASCII characters for displaying margins and corners, something
like this (note that you should still include the numbers/letters indications near each row/column):

```
    1   2   3
  ┌───┬───┬───┐
A │ X │ O │ X │  
  ├───┼───┼───┤  
B │ X │ O │ X │  
  ├───┼───┼───┤  
C │ O │ X │ X │  
  └───┴───┴───┘
```

___

###### Computer player

Create a __computer adversary__! Write the code which can control one of the players, so a human player can play against
the computer.

Some simpler _strategies_ you can start with:

- _Random_: computer should select at random from the empty table cells. Then play yourself a few times against it, on a
  3x3 table. How probable is that the computer ever wins? (or at least end with a draw)

- _Defensive_: computer should try to block the opponent from completing any line: it should check if the user is about
  to complete any line (has 2 in a row somewhere) and then block the cell on which the user would win; if there is no
  immediate danger from the opponent, the computer can select an empty cell at random (or maybe more useful: trying to
  select a cell closer to the already marked ones - either by itself or by the opponent..)

- _Smart/offensive_: try to apply the general strategy for any 2-player game: choose your move in such a way that: first
  try to neutralize any urgent threat posed by the opponent AND/OR create a threatening position yourself (will also
  need to be able to evaluate a risk/reward score for multiple possible positions and somehow choose one of the best
  options)
