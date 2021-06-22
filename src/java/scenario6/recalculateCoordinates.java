// Internal action code for project CM30174_50206

package scenario6;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class recalculateCoordinates extends DefaultInternalAction {

	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
		// execute the internal action

		int x = (int)((NumberTerm)args[0]).solve();
		int y = (int)((NumberTerm)args[1]).solve();

		int mapSizeX = (int)((NumberTerm)args[2]).solve();
		int mapSizeY = (int)((NumberTerm)args[3]).solve();

		int singOfX = (int)Math.signum(x);
		int singOfY = (int)Math.signum(y);

		NumberTerm valx;
		NumberTerm valy;

		//        if((Math.abs(x%mapSizeX))>(mapSizeX/2)) {
		//        	
		//        	
		//        	
		//        }

		if(x>=0 && x<=mapSizeX) {
			if(x > mapSizeX/2) {
				x = x - mapSizeX;
				
			}
			valx = new NumberTermImpl(x) ;
		}

		else if(x<=0 && x < (-mapSizeX)) {
			if(x < (-(mapSizeX/2))) {
				x=mapSizeX+x;

			}
			valx = new NumberTermImpl(x) ;
		}
		else {
			x = x % mapSizeX;
			valx = new NumberTermImpl(x) ;
		}


		if(y>=0 && y<=mapSizeY) {
			if(y > mapSizeY/2) {
				y = y - mapSizeY;
				
			}
			valy = new NumberTermImpl(y) ;
		}

		else if(y<=0 && y < (-mapSizeY)) {
			if(y < (-(mapSizeY/2))) {
				y=mapSizeY+y;

			}
			valy = new NumberTermImpl(y) ;
		}
		else {

			y = y % mapSizeY;
			valy = new NumberTermImpl(y) ;
			//			return un.unifies((valx),args[2]);
		}


		return un.unifies((valx),args[4]) && un.unifies(valy, args[5]);
	}
}
