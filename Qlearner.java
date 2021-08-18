import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public static QLearner train(Board board, int episodes) {

    int stateCount = (int)Math.pow(3, board.size() * board.size());
    int actionCount = board.size() * board.size();

    QLearner learner = new QLearner(stateCount, actionCount);
    //learner.setActionSelection(SoftMaxActionSelectionStrategy.class.getCanonicalName());

    Qplayer player1 = new Qplayer(1, board, learner);
    Qplayer player2 =new Qplayer(2, board, learner);
    
    int wins = 0;

    for(int i=0; i < episodes; ++i) {
        player1.clearHistory();
        player2.clearHistory();

        logger.info("Iteration: {} / {}", (i+1), episodes);
        board.reset();
        while (board.canBePlayed()) {
            player1.act();
            player2.act();
        }
        logger.info("winner: {}", board.getWinner());
        player1.updateStrategy();
        player2.updateStrategy();
        logger.info("board: \n{}", board);
        
        wins += board.getWinner() == 1 ? 1 : 0;
        logger.info("success rate: {} %", (wins * 100) / (i+1));
    }

    return learner;
}