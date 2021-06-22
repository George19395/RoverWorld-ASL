// Internal action code for project CM30174_50206

package scenario6;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class initialPosition2 extends DefaultInternalAction {
	

	private int agentCalls = 0;
	private int scanRange=2;
	private int moveAgent= (scanRange*2) +1;

    
	@Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action

        int x = 0;
        int y = 0;
        
        int mapSizeX = (int)((NumberTerm)args[0]).solve();
        int mapSizeY = (int)((NumberTerm)args[1]).solve();
        int agentNum = (int)((NumberTerm)args[2]).solve();
        agentCalls=agentNum;
        
        
//        double moX = mapSizeX % moveAgent;
        
        double moveX = mapSizeX/moveAgent;
        int movesOnX = (int)Math.ceil(moveX);
        
        int agentMove = ((int) Math.ceil(movesOnX))/3;
        
        int agentSquares= agentMove*moveAgent;
        
//        double moY= mapSizeX/moveAgent;
//        int movesOnY = (int) Math.ceil(moX);
             
        
        if(agentCalls == 1) {
        	agentCalls++ ;
        	x=0;
        	y=0;
        	NumberTerm valX = new NumberTermImpl(x);
        	NumberTerm valY = new NumberTermImpl(y);
        	return un.unifies(valX, args[3]) && un.unifies(valY, args[4]);
        }
        else if(agentCalls == 2) {
        	agentCalls++ ;
        	x=agentSquares;
        	y=0;
        	NumberTerm valX = new NumberTermImpl(x);
        	NumberTerm valY = new NumberTermImpl(y);
        	return un.unifies(valX, args[3]) && un.unifies(valY, args[4]);
        }

        else {
        	x=-agentSquares;
        	y=0;
        	
        	NumberTerm valX = new NumberTermImpl(x);
        	NumberTerm valY = new NumberTermImpl(y);
        	return un.unifies(valX, args[3]) && un.unifies(valY, args[4]);
        }

    }
}
