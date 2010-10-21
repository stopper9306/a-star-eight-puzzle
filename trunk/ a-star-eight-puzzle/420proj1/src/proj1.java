import java.util.*;

public class proj1 {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		int[] p1d = {1, 2, 4, 0, 5, 6, 8, 3, 7};
		
		EightPuzzle start = new EightPuzzle(p1d, 1, 0);
		int[] win = { 0, 1, 2, 3, 4, 5, 6, 7, 8};
		EightPuzzle goal = new EightPuzzle(win, 1, 0);
		astar(start, goal, 1);

		{
			
		}

	}
	
	public static void astar(EightPuzzle start, EightPuzzle goal, int hueristic)
	{
//		function A*(start,goal)
//	     closedset := the empty set                 // The set of nodes already evaluated. 
		LinkedList<Node<EightPuzzle>> closedset = new LinkedList<Node<EightPuzzle>>();
//	     openset := set containing the initial node // The set of tentative nodes to be evaluated. priority queue
		PriorityQueue<Node<EightPuzzle>> openset = new PriorityQueue<Node<EightPuzzle>>();
		Node<EightPuzzle> temp = new Node<EightPuzzle>();
		temp.setData(start);
		openset.add(temp);
//	     came_from := the empty map                 // The map of navigated nodes.
//	     g_score[start] := 0                        // Distance from start along optimal path.
//	     h_score[start] := heuristic_estimate_of_distance(start, goal)
//	     f_score[start] := h_score[start]           // Estimated total distance from start to goal through y.
//	     while openset is not empty
		while(openset.size() > 0){
//	         x := the node in openset having the lowest f_score[] value
			Node<EightPuzzle> x = new Node<EightPuzzle>();
			x.setData(openset.peek().getData());
//	         if x = goal
			if(x.getData().equals(goal))
			{
//	             return reconstruct_path(came_from, came_from[goal])
				 Stack<EightPuzzle> toDisplay = reconstruct(x);
				 print(toDisplay);
				 
			}
//	         remove x from openset
//	         add x to closedset
			closedset.add(openset.poll());
			LinkedList <Node<EightPuzzle>> neighbor = x.getData().getChildren();
//	         foreach y in neighbor_nodes(x)			
			while(neighbor.size() > 0)
			{
				Node<EightPuzzle> y = new Node<EightPuzzle>();
				y.setData(neighbor.removeFirst().getData());
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
			x.pop().toString();
		}
	}

	public static Stack<EightPuzzle> reconstruct(Node<EightPuzzle> winner)
	{
		Stack<EightPuzzle> correctOutput = new Stack<EightPuzzle>();
		
		while(winner.getData().parent != null)
		{
		correctOutput.add(winner.getData());
		winner.setData(winner.getData().getParent());
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
	
	

