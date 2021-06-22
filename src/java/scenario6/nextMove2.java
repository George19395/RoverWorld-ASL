// Internal action code for project CM30174_50206

package scenario6;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;
import rover.RoverWorld;

public class nextMove2 extends DefaultInternalAction {

	private int scanRange = 2;

	private int moveAgent = (scanRange)*2 + 1;
	private int agentCallsX = 1;
	private int agentCallsY = 1;
	private int totalX=0;
	private int totalY=0;

	private int flagBase=0;
	//	int movesOnX= mapSizeX /moveAgent;
	//	int movesOnXindex = 0;

	//	int movesOnY= mapSizeY /moveAgent;
	//	int movesOnYindex =0;


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

		//    	int movesOnX= (mapSizeX /moveAgent);
		//    	int movesOnXindex = 0;

		int moY=mapSizeY%moveAgent;
		double motY=Math.ceil(mapSizeY/moveAgent);
		int movesOnY=mapSizeY/moveAgent;
		//		int movesOnY = (int)motY;
		//        int movesOnY = (int)Math.ceil(moveY);
		//        int agentMoveY = ((int) Math.ceil(movesOnY))/2;


//		int moX=mapSizeX%3;
		int moX=mapSizeX%moveAgent;
		double moveX = mapSizeX/moveAgent;
		double movesOnX = moveX/3;
		int agentMoveX = ((int) Math.ceil(movesOnX));

		//        int agentSquares= agentMove*moveAgent;

		if(moY==0) {
			if(agentCallsY < movesOnY) {
				y = moveAgent;
				agentCallsY++;
				totalY=totalY+y;

				NumberTerm valX = new NumberTermImpl(x);
				NumberTerm valY = new NumberTermImpl(y);
				NumberTerm valBase = new NumberTermImpl(flagBase);
				return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);

			}
			else {
				if(moX==0) {
					if(agentCallsX < agentMoveX) {
						if(moY==0) {
							y = moveAgent;	
						}
						else {
							y=moY;
						}
						x = moveAgent;
						totalY=totalY+y;
						totalX=totalX+x;
						agentCallsY=1;
						agentCallsX++;

						NumberTerm valX = new NumberTermImpl(x);
						NumberTerm valY = new NumberTermImpl(y);
						NumberTerm valBase = new NumberTermImpl(flagBase);
						return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);
					}
					else {
						totalY=-totalY;
						totalX=-totalX;
						flagBase=1;
						NumberTerm valX = new NumberTermImpl(totalX);
						NumberTerm valY = new NumberTermImpl(totalY);
						NumberTerm valBase = new NumberTermImpl(flagBase);
						return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);	

					}
				}	
				else {
					if(agentCallsX <= agentMoveX) {
						if(moY==0) {
							y = moveAgent;	
						}
						else {
							y=moY;
						}
						x = moveAgent;
						totalY=totalY+y;
						totalX=totalX+x;
						agentCallsY=1;
						agentCallsX++;

						NumberTerm valX = new NumberTermImpl(x);
						NumberTerm valY = new NumberTermImpl(y);
						NumberTerm valBase = new NumberTermImpl(flagBase);
						return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);
					}
					else {
						totalY=-totalY;
						totalX=-totalX;
						flagBase=1;
						NumberTerm valX = new NumberTermImpl(totalX);
						NumberTerm valY = new NumberTermImpl(totalY);
						NumberTerm valBase = new NumberTermImpl(flagBase);
						return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);	

					}
				}
			}

		}
		else {
			if(agentCallsY <= movesOnY) {
				y = moveAgent;
				agentCallsY++;
				totalY=totalY+y;

				NumberTerm valX = new NumberTermImpl(x);
				NumberTerm valY = new NumberTermImpl(y);
				NumberTerm valBase = new NumberTermImpl(flagBase);
				return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);

			}
			else {

				if(moX==0) {
					if(agentCallsX < agentMoveX) {
						if(moY==0) {
							y = moveAgent;	
						}
						else {
							y=moY;
						}
						x = moveAgent;
						totalY=totalY+y;
						totalX=totalX+x;
						agentCallsY=1;
						agentCallsX++;

						NumberTerm valX = new NumberTermImpl(x);
						NumberTerm valY = new NumberTermImpl(y);
						NumberTerm valBase = new NumberTermImpl(flagBase);
						return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);
					}
					else {
						totalY=-totalY;
						totalX=-totalX;
						flagBase=1;
						NumberTerm valX = new NumberTermImpl(totalX);
						NumberTerm valY = new NumberTermImpl(totalY);
						NumberTerm valBase = new NumberTermImpl(flagBase);
						return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);	

					}
				}	
				else {
					if(agentCallsX <= agentMoveX) {
						if(moY==0) {
							y = moveAgent;	
						}
						else {
							y=moY;
						}
						x = moveAgent;
						totalY=totalY+y;
						totalX=totalX+x;
						agentCallsY=1;
						agentCallsX++;

						NumberTerm valX = new NumberTermImpl(x);
						NumberTerm valY = new NumberTermImpl(y);
						NumberTerm valBase = new NumberTermImpl(flagBase);
						return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);
					}
					else {
						totalY=-totalY;
						totalX=-totalX;
						flagBase=1;
						NumberTerm valX = new NumberTermImpl(totalX);
						NumberTerm valY = new NumberTermImpl(totalY);
						NumberTerm valBase = new NumberTermImpl(flagBase);
						return un.unifies(valX, args[2]) && un.unifies(valY, args[3])&& un.unifies(valBase, args[4]);	

					}
				}
			}
		}
	}
}
