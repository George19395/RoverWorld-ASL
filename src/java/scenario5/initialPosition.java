// Internal action code for project CM30174_50206

package scenario5;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class initialPosition extends DefaultInternalAction {
	

	private int agentCalls = 0;

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
        ts.getAg().getLogger().info("executing internal action 'scenario3Com.initialPosition'");

        int x = 0;
        int y = 0;
        
        int mapSizeX = (int)((NumberTerm)args[0]).solve();
        int mapSizeY = (int)((NumberTerm)args[1]).solve();
        int agentNum = (int)((NumberTerm)args[2]).solve();
        agentCalls=agentNum;
        
        if(agentCalls == 1) {
        	agentCalls++ ;
        	
        	NumberTerm valX = new NumberTermImpl(x);
        	NumberTerm valY = new NumberTermImpl(y);
        	return un.unifies(valX, args[3]) && un.unifies(valY, args[4]);
        }
        else if(agentCalls == 2) {
        	agentCalls++ ;
        	x = x + mapSizeX/3 ;
        	
        	NumberTerm valX = new NumberTermImpl(x);
        	NumberTerm valY = new NumberTermImpl(y);
        	return un.unifies(valX, args[3]) && un.unifies(valY, args[4]);
        	
        }
        else {
        	x = x - (mapSizeX-(2*(mapSizeX/3)));
        	
        	NumberTerm valX = new NumberTermImpl(x);
        	NumberTerm valY = new NumberTermImpl(y);
        	return un.unifies(valX, args[3]) && un.unifies(valY, args[4]);
        }

    }
}
