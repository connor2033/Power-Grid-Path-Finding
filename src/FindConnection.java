import java.io.IOException;

/**
 * @author Connor Haines
 * 
 * This class is the class that takes the map files as inputs, and then traverses each map from start to finish based on a specific algorithm.
 */
public class FindConnection {
	
	Map cityMap;
	
	/**
	 * This is the constructor which take in the filename of a map file and creates a new map object.
	 * 
	 * @param filename is the name of a map file that is passed to the class.
	 */
	public FindConnection (String filename) {
		try {
			cityMap = new Map(filename);
		}
		catch (IOException e) {
			System.out.println("You did not enter a valid file name.");
		}
	}
	
	/**
	 * Builds the given map and implements an algorithm which uses the bestCell method to create a stack of cells from
	 * the starting cell (power station) to the destination cell (customer).
	 * 
	 * @param args 
	 */
	public static void main (String[] args) {
		if (args.length < 1) {
			System.out.println("You must provide the name of the input file.");
			System.exit(0);
		}
		String mapFileName = args[0];
		
		try {
			FindConnection input = new FindConnection(mapFileName);
			ArrayStack<MapCell> stack = new ArrayStack<MapCell>();
			
			
			//Main algorithm for building stack/path
			stack.push(input.cityMap.getStart());
			input.cityMap.getStart().markInStack();
			
			while(!stack.isEmpty()) {
				if (stack.peek().isCustomer()) {
					System.out.println("Destination found! There were "+stack.size()+" total cells in the path.");
					break;
				}
				
				
				if (bestCell(stack.peek()) != null) {
						stack.push(bestCell(stack.peek()));
						stack.peek().markInStack();
					
				}
				//Backtracking if there is no bestCell 
				else {
					stack.peek().markOutStack();
					stack.pop();
				}
			}	
			if (stack.isEmpty()) {
				System.out.println("The destination was not found.");
			}
		}
		catch (Exception e) {
			System.out.println("Could not create map without map file.");
		}
		
	}
	
	/**
	 * This method uses a complex structure of if statements, for loops, and try/catch blocks
	 * to determine which is the best cell to add to the stack/move to, based on a cell that is passed to the method.
	 * 
	 * @param cell is the cell object that is passed to the method. The method will determine the best neighbouring
	 * cell for this specific cell.
	 * 
	 * @return This method returns the best cell for the algorithm in the main method to use, and if there is no
	 * best cell, it returns null.
	 */
	private static MapCell bestCell(MapCell cell) {
		MapCell current = cell;
		
		
					//When the current cell is an omni switch or the power station
					if (current.isOmniSwitch() || current.isPowerStation()) {
						
						//If the neighbouring cell is the customer
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isCustomer()) {
										return current.getNeighbour(i); 
									}
								}
								catch (Exception e){}
							}
						
							
							//If the neighbouring cell is an omni switch
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isOmniSwitch() && !current.getNeighbour(i).isMarked()) {
										return current.getNeighbour(i);
									}
								}
								catch (Exception e){}
							}
						
						//If the neighbouring cell is a vertical switch or horizontal switch (checks if they're in proper positions based on index)
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isVerticalSwitch() || current.getNeighbour(i).isHorizontalSwitch()) {
										if (current.getNeighbour(i).isVerticalSwitch() && i%2 == 0 && !current.getNeighbour(i).isMarked()) {
											return current.getNeighbour(i);
										}
										if (current.getNeighbour(i).isHorizontalSwitch() && i%2 != 0 && !current.getNeighbour(i).isMarked()) {
											return current.getNeighbour(i);
										}
									}
								}
								catch (Exception e){}
								
							}
						
					}
					

						//When the current cell is a vertical switch.
						if (current.isVerticalSwitch()) {
							
							//If the neighbouring cell is the customer
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isCustomer() && i%2 == 0){
										return current.getNeighbour(i);
									}
								}
								catch (Exception e){}
								
							}
							//If the neighbouring cell is an omni switch
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isOmniSwitch() && i%2 == 0 && !current.getNeighbour(i).isMarked()){
										//System.out.println("I should go there.");
										return current.getNeighbour(i);
									}
								}
								catch (Exception e) {}
							}
							
							//If the neighbouring cell is a vertical switch and is on the top or bottom (index 0 or 2)
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isVerticalSwitch() && i%2 == 0 && !current.getNeighbour(i).isMarked()){
										return current.getNeighbour(i);
									}
								}
								catch (Exception e) {}
							}
							
						}
		
						//When the current cell is a horizontal switch.
						if (current.isHorizontalSwitch()) {
							
							//If the neighbouring cell is the customer
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isCustomer() && i%2 != 0 && !current.getNeighbour(i).isMarked()){
										return current.getNeighbour(i);
									}
								}
								catch (Exception e) {}
							}
							
							//If the neighbouring cell is an omni switch
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isOmniSwitch() && i%2 != 0 && !current.getNeighbour(i).isMarked()){
										return current.getNeighbour(i);
									}
								}
								catch (Exception e) {}
							}
							
							//If the neighbouring cell is a horizontal switch and is on the left or right side (index 1 or 3)
							for (int i = 0; i < 4; i++) {
								try {
									if (current.getNeighbour(i).isHorizontalSwitch() && i%2 != 0 && !current.getNeighbour(i).isMarked()){
										return current.getNeighbour(i);
									}
								}
								catch (Exception e) {}
							}
							
						}
						
			
			return null;
	}
	
	
	
}
