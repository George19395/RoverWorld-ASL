

!start.

+!start : true <- !scan_for_gold.

+! scan_for_gold : true  <- .print("scanning for gold.....");
							scan(3).
							
+! move_randomly : true <-  rover.ia.random(X);
							rover.ia.random(Y);
							.print("moving around the map");
							move(X,Y,3);
							.print("keeping track of my steps")
							rover.ia.add_journey(X, Y);
							!scan_for_gold.
	
+! collect_gold : true <-  .print("collecting gold");
						   do(collect)
						   ! collect_gold.
						   
-! collect_gold: true <- .print("I failed to collect gold").
												   
+ result(collect, 4): at_gold <- .print("I can not carry any more gold").

+ result(collect, 3): at_gold <-  .print("I can not carry any more gold").
					
+ result (move, 0) : at_gold <- .print("I am at the gold deposit");
								!collect_gold.	
									
+ result (scan, 0) : true <- -result(scan,0).						
							
+ result(scan, -1) : true <- 	.print("I did not find anything after scanning");
								!move_randomly.
							 
+ discovery(X,Y,Q,gold): true <- .print("I found gold and I am moving to it")
								 move(X, Y, 1);
								 + at_gold;
								 rover.ia.add_journey(X, Y);
								 !collect_gold.	
								 
// if it tries to move to its location: scan bug
+ result (move, 4) : true <- .print("can\'t move there");
   							 !move_randomly.
								
							 

