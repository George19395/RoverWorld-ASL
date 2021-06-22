location(0,0).
num(0).

+! collectGold: goldAt(X,Y) <- -goldAt(X,Y)
							.wait(500);
							.print("GOING TO COLLECT Diamond");
							?size(SizeX,SizeY);
							scenario5.recalculateCoordinates(X,Y,SizeX,SizeY,RedX,RedY);
							-+location(RedX,RedY);
							-moveToBase;
							+moveToGold;
							move(RedX,RedY,1);
//							-goldAt;
							+at_gold;
							! collect_gold.


+! deposit_gold : true <- .print("depositing gold");
							do(deposit);
							if(not end_of_simulation(0)){
								! deposit_gold;
							}.
							
-! deposit_gold : true <- 	.print("i dont have gold to deposit");
							!collectGold.





+! collect_gold : true <-  .print("collecting gold");
						   do(collect)
						   ! collect_gold.
						   
-! collect_gold: true <- .print("I failed to collect gold").



+ result(collect, 3): at_gold <-  .print("COLLECTED ALL GOLD");
								  .print("move to BASE");
								  ?location(OldX,OldY);
								  -at_gold;
								  -+location(0,0);
								  -moveToGold;
								  +moveToBase;
//								  -goldLocation;
								  move(-OldX,-OldY,1);							  
								 .print("AT BASE",0,0);
								  ! deposit_gold.
+goldLocation(X,Y)[source(Ag)]: .count(goldLocation(_,_),L)  <-.print("STored Gold Location");
							?num(G);
//							-+num(G+1);
							+goldAt(X,Y);
//							.count(goldAt(X,Y),N);
							-+num(L).
//							-goldLocation(X,Y)[source(Ag)].
							  

//			   