import java.util.*;

public class proj1 {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		
		int[] p1d = {0, 2, 4, 6, 8, 1, 3, 5, 7};
		int hueristic = 1;
		EightPuzzle start = new EightPuzzle(p1d, hueristic, 0);
		int[] win = { 0, 1, 2,
					  3, 4, 5,
					  6, 7, 8};
		EightPuzzle goal = new EightPuzzle(win, hueristic, 0);
		PriorityQueue<EightPuzzle> h1 = new PriorityQueue<EightPuzzle>();
		PriorityQueue<EightPuzzle> h2 = new PriorityQueue<EightPuzzle>();
		int[] z = new int[9];
		for(int i = 0; i < 1;i++)
		{
			z = shuffle();
			h1.add(new EightPuzzle(z, 1, 0));
			h2.add(new EightPuzzle(z, 2, 0));
		}
		System.out.println("|   Steps | \th(n)\t| Openset | Closedset | time(sec) |");
		while(!h1.isEmpty())
		{
		astar(h1.poll(), goal, (long)System.currentTimeMillis()/1000);
		astar(h2.poll(), goal, (long)System.currentTimeMillis()/1000);
		}

		

	}
	
	
	public static int[] shuffle()
	{	
		Random gen = new Random();
		int set[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		boolean con = true;
		while(con){
				for(int i = 0; i < set.length; i++ )
				{
					int ranPos = gen.nextInt(set.length);
					int temp = set[i];
					set[i] = set[ranPos];
					set[ranPos] = temp;
				}
				if(EightPuzzle.solvable(set)){
				con = false;
				}
		}
		return set;
	}
	public static boolean contains(Iterator<EightPuzzle> x, EightPuzzle s)
	{
		while(x.hasNext())
		{
			if(x.next().equals(s))
			return true;
		}
		return false;
	}
	public static void astar(EightPuzzle start, EightPuzzle goal, long startTime)
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
			if(x.mapEquals(goal))
			{
				long endTime = (System.currentTimeMillis() / 1000) - startTime;
//	             return reconstruct_path(came_from, came_from[goal])
				 Stack<EightPuzzle> toDisplay = reconstruct(x);
				 
				 System.out.print("|\t" + x.getG_n() + "|\t" + x.hueristic_type + "|\t" + openset.size() + " |  " + closedset.size() + "|\t" + endTime +"|\n");

				 //print(toDisplay);
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
				if(contains(closedset.iterator(), y)){
//	                 continue
					continue;
				}
//	             tentative_g_score := g_score[x] + dist_between(x,y)
//	 
//	             if y not in openset
				if(!contains(openset.iterator(), y)){
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
	


