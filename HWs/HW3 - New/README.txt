****INSTRUCTIONS AND CLARIFICATIONS***

-IN ORDER TO PLAY THE GAME RUN THE MAIN.JAVA
-OUR GAME IS PLAYER VS COMPUTER 
-YOU HAVE THE FIRST MOVE AND YOU ARE THE 'X'
-CLICK ONLY ONCE ON THE SQUARE THAT YOU WANT TO PUT THE 'X'
-THE VIRTUAL VALUES UNDER THE GRID SHOW THE CURRENT SITUATION OF THE GRID

***IMPORTANT***
SINCE THE PROGRAM TAKES TOO MUCH TIME FOR COMPUTING THE ALPHA-BETA ALGORITHM
WE HAVE IMPLEMENTED OUR CODE IN A WAY THAT DECREASE THIS OVERHEAD

***IN GameBot.java LİNE 50 >> if(turn <= 3 || isPlayer)***

THIS CONDITION INDICATES HOW MANY TURN THAT WE WILL NOT CHECK THE OPTIMA VALUE
WE HAVE DECIDED THAT 3 IS THE OPTIMAL TURN NUMBER
IF YOU DECREASE THAT NUMBER THE COMPUTER WILL PLAY MORE OPTIMALLY BUT WAIT LONGER
0 GIVES THE BEST RESULT BUT LONGEST WAITING TIME

***AS A CONCLUSION***
The tictactoe 4x4 is a futile game because our bot plays optimally and prevents us to win the game when we play optimally too.
We are not able to beat the bot but if we play bad, the bot will always beat us since it plays optimally all the time.
Therefore there is no such case when we win the game except we increase the turn number to 6. 
Please check the report.pdf for the further explanation.
