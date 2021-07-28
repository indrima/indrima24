package experiments;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import agents.qLearningFifty;
import agents.nonDetMinMax;
import agents.detMinMax;
import agents.random;
import agents.qLearningHund;
import util.AI;
import util.Context;
import util.GameLoader;
import util.Trial;
import util.model.Model;

public class RunCustomMatch
{
	
	//-------------------------------------------------------------------------
	
	/** Name of game we wish to play */
	static final String GAME_NAME = "TicTacToe.lud";
	
	/** Number of games to play*/
	static final int NUM_GAMES = 100;

	//-------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	private RunCustomMatch()
	{
		// do not instantiate
	}

	//-------------------------------------------------------------------------

	public static void main(final String[] args)
	{
		// load and create game
		final Game game = GameLoader.loadGameFromName(GAME_NAME);

		final Trial trial = new Trial(game);
		final Context context = new Context(game, trial);
		final List<AI> ais = new ArrayList<AI>();
		ais.add(null);
		// player 1
		ais.add(new agents.qLearningFifty());
		// player 2 
		ais.add(new agents.qLearningFifty());	
		
		for (int gameCounter = 0; gameCounter < NUM_GAMES; ++gameCounter)
		{
			// play a game
			game.start(context);
			
			for (int p = 1; p < ais.size(); ++p)
			{
				ais.get(p).initAI(game, p);
			}
			
			final Model model = context.model();
			
			while (!context.trial().over())
			{
				model.startNewStep(context, ais, 1.0);
			}
			
			System.out.println("Outcome = " + context.trial().status());
		}
	}

	//-------------------------------------------------------------------------

}