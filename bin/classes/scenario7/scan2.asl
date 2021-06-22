location(0,0).
lastGoldLocation(0,0).
id(1).
!start.
//+!start : true <- !scan_for_gold.
+!start:true<-!initial_position.

+! initial_position:true <- .print("moving to initial position");
							?size(SizeX,SizeY);
							?id(I);
							scenario5.initialPosition2(SizeX,SizeY,I,X,Y);
							-+location(X,Y);
							move(X,Y,1);
							!scan_for_gold.
							
+! scan_for_gold :true<- scan(3).

+! move_randomly : size(SizeX,SizeY) & scenario5.nextMove2(SizeX,SizeY,X,Y,Flag) & Flag==0	<-	?location(OldX,OldY);
//							?size(SizeX,SizeY);
//							scenario5.nextMove2(SizeX,SizeY,X,Y);
							scenario5.recalculateCoordinates(OldX+X,OldY+Y,SizeX,SizeY,RedX,RedY);
							
							-+location(RedX,RedY);
							move(X,Y,1);
							!scan_for_gold.
					
+! move_randomly : size(SizeX,SizeY) & scenario5.nextMove2(SizeX,SizeY,X,Y,Flag) & Flag==1	<-	?location(OldX,OldY);
//							?size(SizeX,SizeY);
//							scenario5.nextMove2(SizeX,SizeY,X,Y);
							scenario5.recalculateCoordinates(X,Y,SizeX,SizeY,RedX,RedY);
							-+location(0,0);
							move(-OldX,-OldY,1);
//							move(RedX,RedY,1);
							
//							move(RedX,RedY,1);
							.print("FINISHeD MY SCANS");
							.send(coll,achieve,collectGold).
					
//+! move_to_location2(X,Y)[source(Ag)] : true <-
//							.print("moving");
//							-+location(X+1,Y);
//							move(X+1,0,1);
//							move(0,Y,1);
//							!scan_for_gold.													   			
					
+ result (scan, 0) : true <- -result(scan,0)[source(percept)].	

+ result(scan, -1) : true <- 	.print("I did not find anything after scanning");
								!move_randomly.

								 
+discovery(X,Y,Q,gold):lastGoldLocation(L,J) & location(OldX,OldY) &  (OldX+X == L) & (OldY+Y ==J) <- 	.print("I found gold and i am sending coordinates to collector");
								-discovery;
								.print("ALREADY COLLECTED THAT");
								!move_randomly.
+discovery(X,Y,Q,gold):lastGoldLocation(L,J) & location(OldX,OldY) &  (not (OldX+X == L) | not(OldY+Y ==J)) <- 	.print("I found gold and i am sending coordinates to collector");
								-discovery;
//								?location(OldX,OldY);
								.send(coll,tell,goldLocation(OldX+X,OldY+Y));
								-+lastGoldLocation(OldX+X,OldY+Y);
								!move_randomly.
												
								
+ result (move, 0) : true <- .print("move was succesful").

//+ result(move,4,A,B,X,Y)[source(percept)] : true 
//			<- .print("I have moved: (", X , ",", Y , ")");
//			   .print("I still have to move (", A , ",", B , ")");
//			   .print("Place your mouse over both agents to confirm");
//			   move(0,1,1);
//			   move(A,0,1);
//			   move(0,B-1,1);
//			   !scan_for_gold.																	