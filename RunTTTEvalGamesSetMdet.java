package experiments;

import java.util.Arrays;

import agents.qLearningFifty;
import agents.nonDetMinMax;
import agents.detMinMax;
import agents.random;
import agents.qLearningHund;
import random.RandomAI;
import search.mcts.MCTS;
import supplementary.experiments.EvalGamesSet;
import util.AI;

public class RunTTTEvalGamesSetMdet
{
	
	static final String GAME_NAME = "TicTacToe.lud";
	
	static final boolean USE_GUI = false;
	
	static final int MAX_WALL_TIME = -1;
	
	static final AI[] AGENTS = new AI[]{
			new agents.detMinMax(),
			new agents.detMinMax()
	};
	
	//-------------------------------------------------------------------------
	
	/**
	 * Constructor
	 */
	private RunTTTEvalGamesSetMdet()
	{
		// do not instantiate
	}
	
	//-------------------------------------------------------------------------
	
	public static void main(final String[] args)
	{
		// set up our match
		final EvalGamesSet evalGamesSet = 
				new EvalGamesSet(USE_GUI, MAX_WALL_TIME)
				.setGameName(GAME_NAME)
				.setAgents(Arrays.asList(AGENTS))
				.setNumGames(10000)
				.setMaxSeconds(10.0)
				.setRotateAgents(true);
		
		// start playing
		evalGamesSet.startGames();
	}
	
	//-------------------------------------------------------------------------

}
