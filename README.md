# indrima24
Master's thesis work:
This thesis examines the application of Q-learning in a Reinforcement Learning (RL) frame-work to develop game playing agents for abstract board games With increasing state-space com-plexity. The two aspects that this thesis investigates are, first, training of the Q-learning agent, andsecond, providing a methodology to evaluate a variety of agents.  We focus on agent performancein three games - Tic-Tac-Toe, Nine-Men’s Morris, and Mancala.  Each of these games has an in-creased state-space complexity going from first to last, respectively. However, each of these gameshave been demonstrated to be solved by learning agents.To  train  and  evaluate  our  agents,  we  use  the  Ludii  General  Game  System,  created  by  theDigital Ludeme Project (DLP). To see how the Q-learning based game playing agent trains, thelearning agent plays a particular game against a teaching agent where we test out different RLagents.  The teaching agents include a Q-learning agent,  a deterministic Min-Max agent,  and anon-deterministic Min-Max agent.  We observe the number of training generations needed untilthe learning agent converges as all the games we test, in this thesis, are solved by RL agents.  We record this information and provide some insights into training. We find that although Min-Max isthe best teaching agent, but Q-learning allows us to solve the problem without needing an existingagent Additionally, we provide a method to create weaker agents that can be used in a variety ofsituations.For the evaluation of each agent, we make use of a methodology to evaluate a group of agentsplaying the three board games.  This includes a methodology on how to find the number of trialssuch that evaluation of a particular agent is stable.  We use a round-robin tournament where ourselected agents play each other both as first and second players. Additionally, we provide a meansto  determine  how  many  games  are  played  in  each  match.To  quantitatively  assess  which  of  theplayers is the best we use a point system.  Our results show that the deterministic Min-Max agentis the best agent for all three of the games, the fully converged Q-learning based agent is a closesecond for both Tic-Tac-Toe and Nine Men’s Morris, and the non deterministic Min-Max agentcomes in second place for Mancala.

**Getting started:**

1.	Download Ludii's JAR file. 

2.	Create a new Java project using any IDE. 

3.	Make sure to add the Ludii's JAR file already downloaded as a library for the new project.

4.	To implement any agent, extend the abstract class util.AI. This contains three methods that may be overridden:
•	public Move selectAction(final Game game, final Context context, final double maxSeconds, final int maxIterations, final int maxDepth). This method can be used to return the next Move to be played by the agent by taking a reference to the game being played, and the current context (which contains data including but not limited to the current game state) as arguments. The three final arguments maxSeconds, maxIterations, and maxDepth can be used to constrain the agent's processing (its search time, its maximum iteration count, or search depth).
•	public void initAI(final Game game, final int playerID). This method may be overridden to initialise of the AI when the game to be played has been determined, but before the initial game state has been generated.
•	public boolean supportsGame(final Game game). By default, this method returns true for any game. This method can be overridden to always return false for the games a particular AI agent cannot play. Ludii will then know not to try to make your AI play such a game.
•	public void closeAI(). This method can perform any cleanup of resources when a game has been finished.

5.	Export your project to a new JAR file.

**Loading Custom AI in the Ludii Application**

1.	Click one of the player names in the GUI to open the dialog box where agent types can be assigned to players, another way to of doing this is by selecting Ludii > Preferences... in the menubar. 

2.	Select the From JAR option in the drop-down menu.

3.	Select the JAR file containing .class file of the custom AI. 

4.	Choose one of the classes in the selected JAR file that extend Ludii's util.AI abstract class from the dialog box. 

5.	Ludii will then call a zero-arguments constructor of that class to instantiate an agent of the selected class.

More information on getting started with the development of third- party AI implementations with the Ludii General Game System can be found on:

http://www.ludeme.eu/

http://ludii.games/


 

