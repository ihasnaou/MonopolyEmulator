👤 AUTHORS AND ACKNOWLEDGEMENT  
This program reproduces a simplified version of the popular board game Monopoly.
This program was made for the SYSC 3110 team project, by group 15, which consists of Ilyes Hasnaou, Sara Shikhhassan, and Sam Al-Zoubi.

📄 GENERAL USAGE NOTES
To run this program, put in the number of players that you want, click on confirm, then put in the number of AI players you want, and hit confirm again.
The maximum number of players allowed in a game is 5, this includes both AI and normal players.
Players are represented by their ID on the board, which will update whenever a player moves.
To move your player, press roll. Rolling a double will allow you to roll again.
The buy button will be enabled whenever you can buy a property. Up to 4 properties can be bought per square so far.
When you land on a property that is owned by another player, you will need to pay your rent to advance further. If you are unable to do so, you will lose and be kicked out of the game.
If you land on the Go To Jail square, you will be jailed for 3 turns. Landing a double when getting out will allow you to bypass the 50$ penalty when leaving.
When landing on railroads or utilities, you will be able to buy them. The rent cost when other players land on them will change depending on the number of 
railroad / utilities you own respectively.

❗️ MILESTONE 4 
When a player begins a new game, if not specified, a USA version of the game is started by default. Otherwise, a player has the option to begin an international
version of the game, a Canadian version.
To play the default regular version, select "New"
To play the Canadian version, select "Load" then type "canadianVersion.xml"
To play the Dubai version, select "Load" then type "dubaiVersion.xml"
General Notes: 
- The "saveExample.xml" is an unplayed saved example of what a user would view once a game is saved.
- International versions of Monopoly must be played by a total of 4 players, and no AI players.


👷 DESIGN DECISIONS
In this milestone, the group decided to work with a boardlayout for squares on the board as it was easier to maniplulate and a rough replica of the actual
Monopoly board. The squares, players, and buttons were created as an Arraylist for ease of access. We decided to create a frame in which a player can input the number of playes and then the number of AI players they wish to have participate in the game. The AI was developed in the Controller class as it controls a player, as opposed to creating it as an additional object since we hard coded the design of the AI functionality. The buttons on the board are disabled when not in use so that the program will not display errors when a player attempts to use them at an invalid time, and those buttons are then enabled when its suited to use them. 
For example, a player cannot "roll" after they have just used that button. The "roll" button is disabled, and they have the options to either buy or give their
turn to the next player. The disabling of the button at that time will prevent any violations of occuring, thus, creating a smoother game experience to the
players. The group developed railroad and utilities as an implementation of property since they can be bought, similarly, jail was not implemented as property as
it cannot be purchased. Also, the static variable "Turn" in the Game class was designed such a way that it designates a turn to each player in the game, so it controls and keeps track of which player is currently playing. Finally, the colours in the Square class are represented as a string and only appear in the Frame 
for an easier implementation. 


📝 LICENSE 
Copyright under "fair use" for educational purposes
