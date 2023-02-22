The Tic-Tac-Toe game is a two-player game in which players, one after the other, bet in
cells on the field or "cross", if the player plays for crosses, or "zero", if the player
plays for zero.
The first person to form a winning combination of three matching symbols wins.
contract. Winning combinations are either in a row of three identical symbols, or diagonally.

Technical task:
It is necessary to implement applications with the ability to play via Bluetooth for two players.
0. The application must have a start screen with buttons: "game via bluetooth", "game with a computer",
"game on one device".
     0.1. This screen should have the game logo.
     0.2. When you click on "Bluetooth play", the screen for connecting to a Bluetooth device should launch.
     0.3. When you click on "game with a computer", the screen with the game opens, and the game with the computer starts.
     0.4. When you click on "game on one device", a screen with the game opens, where the cross goes first.
1. The application must have a screen with the ability to connect via Bluetooth with a second player.
     1.1. The screen should have a button to search for devices nearby.
     1.2. When you click on the button, the devices available for connection should be displayed.
         1.2.1. The device must be a phone
         1.2.2. The device must have the application installed to be able to play.
         1.2.3. If it is impossible to comply with paragraphs 1.2.1 and 1.2.2, the device must not
         be displayed in the list of available devices for connection.
     1.3. Clicking on the device should connect to the second user's device.
         1.3.1. If the connection could not be established, a text message should be displayed stating
         that a connection error has occurred.
         1.3.2. If the connection was successful, on the device of the player who initiated the joint
         game, each of the players is assigned the role for which he plays: either a cross or a zero.
         1.3.3. After the roles are assigned, a screen with a game opens on each of the applications.
2. The application must have a screen for the game itself.
     2.1. There should be a field with 3x3 cells on the screen, in order to be able to put a cross or a zero.
     2.2. The player playing the crosses goes first.
     2.3. At the moment of the player's turn, an event is sent to the game controller that the player was like.
     The event contains the type of the player and his move coordinates.
     2.4. The controller of the player who makes the move checks whether the game is over - is there
     winning combination.
         2.4.1. If the combination is winning, then the game ends and the second player is sent
         the event that the game is over with the opposite side winning.
         2.4.2. If the combination is not winning, then the second player is sent an event about his
         queues to walk.
         2.4.3. If the combination is a draw - there are no more cells available, in order to be like,
         then both players receive an event that the game is over.
     2.5. If the user does not make a move within 90 seconds, the game ends and the second
     the player is awarded the victory.
     2.6. When the game ends, screen 3 appears.
3. The application must have a screen with the result of the game.
     3.1. The screen must contain the name of the player who won: either "X" or "Naught".
     3.2. For the player who won, an animation of fireworks, or confetti, should be played.
     3.3. There should be a Retry button on the screen.
         3.3.1. When you click on the "Repeat" button, a new game is created, then actions occur
         starting from paragraph 1.3.2.
     3.4. There should be a button on the screen to end or close the game.
         3.4.1. Pressing the button closes the bluetooth connection.
         3.4.2. After closing the connection, the user is redirected to the start screen 0.
4. The screens for playing via bluetooth, playing on the same device and playing with a computer must be different
Activity, with common logic, for more abstraction.
5. The controller for the game should not know how the players play. New controller
must be created when the game starts.