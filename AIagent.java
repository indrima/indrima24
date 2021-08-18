package random;

import java.util.concurrent.ThreadLocalRandom;

import game.Game;
import main.collections.FastArrayList;
import util.AI;
import util.Context;
import util.Move;
import utils.AIUtils;


public class AIagent extends AI
{
	
	/** Our player index */
	protected int player = -1;
	
	
	/**
	 * Constructor
	 */
	public RandomAI()
	{
		this.friendlyName = "Example Random AI";
	}
	
	
	@Override
	public Move selectAction
	(
		final Game game, 
		final Context context, 
		final double maxSeconds,
		final int maxIterations,
		final int maxDepth
	)
	{
		FastArrayList<Move> legalMoves = game.moves(context).moves();
		
		if (!game.isAlternatingMoveGame())
			legalMoves = AIUtils.extractMovesForMover(legalMoves, player);
		
		final int r = ThreadLocalRandom.current().nextInt(legalMoves.size());
		return legalMoves.get(r);
	}
	
	@Override
	public void initAI(final Game game, final int playerID)
	{
		this.player = playerID;
	}
	
	
}