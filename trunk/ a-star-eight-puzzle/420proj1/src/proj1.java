import java.util.*;

public class proj1 {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		
		int[] p1d = {4, 1, 2, 3, 5, 0, 8, 6, 7};
		int hueristic = 2;
		EightPuzzle start = new EightPuzzle(p1d, hueristic, 0);
		int[] win = { 0, 1, 2,
					  3, 4, 5,
					  6, 7, 8};
		EightPuzzle goal = new EightPuzzle(win, hueristic, 0);
		LinkedList<EightPuzzle> test = start.getChildren();
		PriorityQueue<EightPuzzle> hah = new PriorityQueue<EightPuzzle>();
		while(!test.isEmpty())
		{
			hah.add(test.pop());	
		}
		
		while(!hah.isEmpty())
		{
			System.out.println(hah.peek().h_n +" " + hah.peek().g_n + "<=f of n for => " + hah.poll().toString());
		}
		
		//astar(start, goal);

		

	}
	
	public static void astar(EightPuzzle start, EightPuzzle goal)
	{
		if(start.inversions() % 2 == 1)
		{
			System.out.println("Unsolvable");
			return;
		}
//		function A*(start,goal)
//	     closedset := the empty set                 // The set of nodes already evaluated. 
		LinkedList<EightPuzzle> closedset = new LinkedList<EightPuzzle>();
//	     openset := set containing the initial node // The set of tentative nodes to be evaluated. priority queue
		PriorityQueue<EightPuzzle> openset = new PriorityQueue<EightPuzzle>();

		openset.add(start);
		
//	     came_from := the empty map                 // The map of navigated nodes.
//	     g_score[start] := 0                        // Distance from start along optimal path.
//	     h_score[start] := heuristic_estimate_of_distance(start, goal)
//	     f_score[start] := h_score[start]           // Estimated total distance from start to goal through y.
//	     while openset is not empty
		while(openset.size() > 0){
//	         x := the node in openset having the lowest f_score[] value
			EightPuzzle x = openset.peek();

//	         if x = goal
			if(x.equals(goal))
			{
//	             return reconstruct_path(came_from, came_from[goal])
				 Stack<EightPuzzle> toDisplay = reconstruct(x);
				 System.out.println("Printing solution... ");
				 print(toDisplay);
				 return;
				 
			}
//	         remove x from openset
//	         add x to closedset
			closedset.add(openset.poll());
			LinkedList <EightPuzzle> neighbor = x.getChildren();
//	         foreach y in neighbor_nodes(x)			
			while(neighbor.size() > 0)
			{
				EightPuzzle y = neighbor.removeFirst();
//	             if y in closedset
				if(closedset.contains(y)){
//	                 continue
					continue;
				}
//	             tentative_g_score := g_score[x] + dist_between(x,y)
//	 
//	             if y not in openset
				if(!closedset.contains(y)){
//	                 add y to openset
					openset.add(y);
//	                 tentative_is_better := true
				}
//	             elseif tentative_g_score < g_score[y]
//	                 tentative_is_better := true
			}
//	             else
//	                 tentative_is_better := false
//	             if tentative_is_better = true
//	                 came_from[y] := x
//	                 g_score[y] := tentative_g_score
//	                 h_score[y] := heuristic_estimate_of_distance(y, goal)
//	                 f_score[y] := g_score[y] + h_score[y]
		}
	}
//	     return failure
//	 
//	 function reconstruct_path(came_from, current_node)
	public static void print(Stack<EightPuzzle> x)
	{
		while(!x.isEmpty())
		{
			EightPuzzle temp = x.pop();
			System.out.println(temp.toString());
		}
	}

	public static Stack<EightPuzzle> reconstruct(EightPuzzle winner)
	{
		Stack<EightPuzzle> correctOutput = new Stack<EightPuzzle>();
		
		while(winner.getParent() != null)
		{
		correctOutput.add(winner);
		winner = winner.getParent();
		}
		
//	     if came_from[current_node] is set
//	         p = reconstruct_path(came_from, came_from[current_node])
//	         return (p + current_node)
//	     else
//	         return current_node
		return correctOutput;
	}
	
	}
	
	


	
//	public void convert(int[][] adjust)
//	{
//		int z = 0;
//		for(int i = 0; i < 3; i++)
//		{
//			for(int j = 0; j < 3; j++)
//			{
//				p1d[z++] = adjust[i][j]; 
//			}
//		}
//		
//	}
//
//	public void convert(int[] adjust)
//	{
//		int z = 0;
//		for(int i = 0; i < 3; i++)
//		{
//			for(int j = 0; j < 3; j++)
//			p2d[i][j] = adjust[z++];
//		}
//	}
	
	

