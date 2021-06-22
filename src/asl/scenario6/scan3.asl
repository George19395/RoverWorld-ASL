location(0,0).
lastGoldLocation(0,0).
lastDiamondLocation(0,0).
id(3).

!start.
//+!start : true <- !scan_for_gold.
+!start:true<-!initial_position.

+! initial_position:true <- .print("moving to initial position");
							?size(SizeX,SizeY);
							?id(I);
							scenario6.initialPosition2(SizeX,SizeY,I,X,Y);
							-+location(X,Y);
							move(X,Y,1);
							!scan_for_gold.

+! scan_for_gold :rover.ia.get_energy(X) & X>40  <- 
													scan(3).
												
+! move_randomly : size(SizeX,SizeY) & scenario6.nextMove2(SizeX,SizeY,X,Y,Flag) & Flag==0	<-	?location(OldX,OldY);
//							?size(SizeX,SizeY);
//							scenario6.nextMove2(SizeX,SizeY,X,Y);
							scenario6.recalculateCoordinates(OldX+X,OldY+Y,SizeX,SizeY,RedX,RedY);
							
							-+location(RedX,RedY);
							move(X,Y,1);
							!scan_for_gold.
					
+! move_randomly : size(SizeX,SizeY) & scenario6.nextMove2(SizeX,SizeY,X,Y,Flag) & Flag==1	<-	?location(OldX,OldY);
//							?size(SizeX,SizeY);
//							scenario6.nextMove2(SizeX,SizeY,X,Y);
							scenario6.recalculateCoordinates(X,Y,SizeX,SizeY,RedX,RedY);
							-+location(0,0);
							move(-OldX,-OldY,1);
//							move(RedX,RedY,1);
							
//							move(RedX,RedY,1);
							.print("FINISHeD MY SCANS").
					
												   			
					
+ result (scan, 0) : true <- -result(scan,0)[source(percept)].	

+ result(scan, -1) : true <- 	.print("I did not find anything after scanning");
								!move_randomly.

								 
+discovery(X,Y,Q,gold):lastGoldLocation(L,J) & location(OldX,OldY) &  (OldX+X == L) & (OldY+Y ==J) <- 	
								-discovery;
								.print("ALREADY COLLECTED THAT");
								!move_randomly.
+discovery(X,Y,Q,gold):lastGoldLocation(L,J) & location(OldX,OldY) &  (not (OldX+X == L) | not(OldY+Y ==J)) <- 	.print("I found gold and i am sending coordinates to collector");
								-discovery;
//								?location(OldX,OldY);
								.send(coll,tell,goldLocation(OldX+X,OldY+Y));
								-+lastGoldLocation(OldX+X,OldY+Y);
								!move_randomly.
								
+discovery(X,Y,Q,diamond):lastDiamondLocation(L,J) & location(OldX,OldY) &  (OldX+X == L) & (OldY+Y ==J) <- 	
								-discovery;
								.print("ALREADY COLLECTED THAT");
								!move_randomly.
+discovery(X,Y,Q,diamond):lastDiamondLocation(L,J) & location(OldX,OldY) &  (not (OldX+X == L) | not(OldY+Y ==J)) <- 	.print("I found DIAMOND and i am sending coordinates to collector");
								-discovery;
//								?location(OldX,OldY);
								.send(coll2,tell,goldLocation(OldX+X,OldY+Y));
								-+lastDiamondLocation(OldX+X,OldY+Y);
								!move_randomly.
																								
+ result (move, 0) : true <- .print("move was succesful").