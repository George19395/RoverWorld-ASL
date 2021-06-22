// Internal action code for project CM30174_50206

package scenario5;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;
import rover.RoverWorld;

public class nextMove extends DefaultInternalAction {
	
	private int scanRange = 2;
	
	private int moveAgent = (scanRange)*2 + 1;
	private int agentCalls = 1;
	
//	int movesOnX= mapSizeX /moveAgent;
	int movesOnXindex = 0;

//	int movesOnY= mapSizeY /moveAgent;
	int movesOnYindex =0;
	
	
    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
//        ts.getAg().getLogger().info("executing internal action 'scenario3Com.nextMove'");
        
//        int x = (int)((NumberTerm)args[0]).solve();
//        int y = (int)((NumberTerm)args[1]).solve();
    
		
        int x = 0;
        int y = 0;
        
        int mapSizeX = (int)((NumberTerm)args[0]).solve();
        int mapSizeY = (int)((NumberTerm)args[1]).solve();
        
    	int movesOnX= (mapSizeX /moveAgent);
    	int movesOnXindex = 0;

    	int movesOnY= (mapSizeY /moveAgent);
    	int movesOnYindex =0;
    	
    	
        
    	if(agentCalls == 0) {
    		agentCalls ++;
    		
    		NumberTerm valX = new NumberTermImpl(x);
    		NumberTerm valY = new NumberTermImpl(y);
    		return un.unifies(valX, args[2]) && un.unifies(valY, args[3]);
    	}
    	else if(agentCalls < movesOnY) {
    		y = moveAgent;
    		movesOnYindex++;
    		agentCalls++;
    		
    		NumberTerm valX = new NumberTermImpl(x);
    		NumberTerm valY = new NumberTermImpl(y);
    		return un.unifies(valX, args[2]) && un.unifies(valY, args[3]);
    		
    	}
    	else if(agentCalls < movesOnY) {
    		y = moveAgent;
    		movesOnYindex++;
    		agentCalls++;
    		
    		NumberTerm valX = new NumberTermImpl(x);
    		NumberTerm valY = new NumberTermImpl(y);
    		return un.unifies(valX, args[2]) && un.unifies(valY, args[3]);
    		
    	}
//    	else if(agentCalls == movesOnY) {
//    		y = 2;
//    		movesOnYindex++;
//    		agentCalls++;
//    		
//    		NumberTerm valX = new NumberTermImpl(x);
//    		NumberTerm valY = new NumberTermImpl(y);
//    		return un.unifies(valX, args[2]) && un.unifies(valY, args[3]);
//    		
//    	}

    	else {
    		x = moveAgent;
			movesOnXindex ++;
			agentCalls =1;
			NumberTerm valX = new NumberTermImpl(x);
    		NumberTerm valY = new NumberTermImpl(y);
    		return un.unifies(valX, args[2]) && un.unifies(valY, args[3]);
    		
    	}

    }
}
