
location(0,0).
locationPriorGold(0,0). //use it to store location before moving to gold so you can continue scanning after you collect one of the resources 
//msize(40,40,40).
agentId(1).

!start.

+!start : true <- !scan_for_gold.
							
+! scan_for_gold : true  <- scan(3).
							
+! move_randomly : true <-  ?location(OldX,OldY)
							?size(SizeX,SizeY)
							.print(SizeX,"+++",SizeY)
							scenario1.nextMove(SizeX,SizeY,X,Y);
							move(X, Y,1);
							NewX=OldX+X;
							NewY=OldY+Y;
							scenario1.recalculateCoordinates(NewX,NewY,SizeX,SizeY,ReducedX,ReducedY);
							-+location(ReducedX,ReducedY);
							.print(NewX,":", ReducedY);
							!scan_for_gold.
							
+! deposit_gold : true <- .print("depositing gold");
							do(deposit);
							if(not end_of_simulation(0)){
								! deposit_gold;
							}.
							
-! deposit_gold : true <- .print("i dont have gold to deposit");
							?location(OldX,OldY);
							?locationPriorGold(LocX,LocY);
							move(OldX,0,1);
							move(LocX,0,1);
							move(0,OldY,1);
							move(0,LocY,1);
							! scan_for_gold.							
	
+! collect_gold : true <-  .print("collecting gold");
						   do(collect)
						   ! collect_gold.
						   
-! collect_gold: true <- .print("I failed to collect gold").
												   
+ result(collect, 4): at_gold <- .print("I can not carry any more gold");
									?location(OldX,OldY);
									NewX = -OldX;
									NewY = -OldY;
									move(NewX,0,1);
									move(0,NewY,1);
									-at_gold;
									! deposit_gold.
									
														

+ result(collect, 3): at_gold <-  .print("I can not carry any more gold");
								  .print("start scanning for next resource");
								  ?location(OldX,OldY);
								  ?locationPriorGold(LocX,LocY);
							      NewX = -OldX;
								  NewY = -OldY;
								  -at_gold;
								  -discovery; 
								  move(NewX,0,1);
								  move(0,NewY,1);								  

								  ! deposit_gold.

					
+ result (move, 0) : at_gold <- .print("I am at the gold deposit");
								!collect_gold.	
									
+ result (scan, 0) : true <- -result(scan,0)[source(percept)];
							?discovery(X,Y,Z,Q);
							?location(OldX,OldY);
							?size(SizeX,SizeY);
							-+locPriorGold(-X,-Y);
							move(X,Y,1);
							+at_gold;
							NewX=OldX+X;
							NewY=OldY+Y;
							scenario1.recalculateCoordinates(NewX,NewY,SizeX,SizeY,RedX,RedY);
							-location(OldX,OldY);
							+location(RedX,RedY);
							!collect_gold.


							
+ result(scan, -1) : true <- 	.print("I did not find anything after scanning");
								!move_randomly.
							 

// if it tries to move to its location: scan bug
+ result (move, 4) : true <- .print("can\'t move there");
   							 !move_randomly.

