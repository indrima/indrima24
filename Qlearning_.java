package random;

import java.util.concurrent.ThreadLocalRandom;

import game.Game;
import main.collections.FastArrayList;
import util.AI;
import util.Context;
import util.Move;
import utils.AIUtils;
import java.util.Random;

/**
 * The class provides implementation of Q-Learning algorithm
 * 
 * @author Indrima Upadhyay
 */
public class QLearning extends AI{
    // amount of possible states
    private int states;
    // amount of possible actions
    private int actions;
    // q-values
    private double[][] qvalues;
    // exploration policy
    private IExplorationPolicy explorationPolicy;

    // discount factor
    private double discountFactor = 0.9;
    // learning rate
    private double learningRate = 0.4;

    public int getStates() {
        return states;
    }

    public int getActions() {
        return actions;
    }

    
    public IExplorationPolicy getExplorationPolicy() {
        return explorationPolicy;
    }

    /**
     * Policy, which is used to select actions.
     * @param explorationPolicy Exploration Policy
     */
    public void setExplorationPolicy(IExplorationPolicy explorationPolicy) {
        this.explorationPolicy = explorationPolicy;
    }
    
    /**
     *  Initializes a new instance of the QLearning class.
     * @param states Amount of possible states.
     * @param actions Amount of possible actions.
     * @param explorationPolicy Exploration policy.
     * @param randomize Randomize action estimates or not.
     */
    public QLearning( int states, int actions, IExplorationPolicy explorationPolicy, boolean randomize ){
    this.states  = states;
    this.actions = actions;
    this.explorationPolicy = explorationPolicy;

    // create Q-array
    qvalues = new double[states][];
    for ( int i = 0; i < states; i++ ){
        qvalues[i] = new double[actions];
    }

    // do randomization
    if (randomize){
        Random r = new Random();

        for ( int i = 0; i < states; i++ ){
            for ( int j = 0; j < actions; j++ ){
                qvalues[i][j] = r.nextDouble() / 10;
            }
        }
    }
    }
    
    /**
     * Get next action from the specified state.
     * @param state Current state to get an action for.
     * @return Returns the action for the state.
     */
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
    
    /**
     * Update Q-function's value for the previous state-action pair.
     * @param previousState Previous state.
     * @param action Action, which leads from previous to the next state.
     * @param reward Reward value, received by taking specified action from previous state.
     * @param nextState Next state.
     */
    public void UpdateState( int previousState, int action, double reward, int nextState ){
    // next state's action estimations
    double[] nextActionEstimations = qvalues[nextState];
                // find maximum expected summary reward from the next state
    double maxNextExpectedReward = nextActionEstimations[0];

    for ( int i = 1; i < actions; i++ ){
            if ( nextActionEstimations[i] > maxNextExpectedReward )
                    maxNextExpectedReward = nextActionEstimations[i];
    }

    // previous state's action estimations
    double[] previousActionEstimations = qvalues[previousState];
    // update expexted summary reward of the previous state
    previousActionEstimations[action] *= (1.0 - learningRate);
    previousActionEstimations[action] += (learningRate * (reward + discountFactor * maxNextExpectedReward));
    }
   
}