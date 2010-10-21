import java.util.*;

public class EightPuzzle implements Comparable <Object> {
	
	
	int[] puzzle = new int[9];
	int h_n= 0;
	int hueristic_type = 0;
	int g_n = 0;
	int f_n = 0;
	EightPuzzle parent = null;

	
	public EightPuzzle(int[] p, int h_type, int cost)
	{
		this.puzzle = p;
		this.hueristic_type = h_type;
		this.h_n = (h_type == 1) ?  h1(p) : h2(p);
		this.g_n = cost;
		this.f_n = h_n + g_n;
	}
	public int getF_n()
	{
		return f_n;
	}
	public void setParent(EightPuzzle input)
	{
		this.parent = input;
	}
	public EightPuzzle getParent()
	{
		return this.parent;
	}

	public int inversions(int[] list)
	{
		/*
		 * Definition: For any other configuration besides the goal, 
		 * whenever a tile with a greater number on it precedes a 
		 * tile with a smaller number, the two tiles are said to be inverted
		 */
		int inversion = 0;
		for(int i = 0; i < list.length; i++ )
		{
			for(int j = 0; j < i; j++)
			{
				if(list[i] != 0 && list[j] != 0)
				{
				if(list[i] < list[j])
					inversion++;
				}
			}
//			if(list[i] == 0)
//			{
//				if(i < 3)
//					inversion += 1;
//				else if(i > 5)
//					inversion += 3;
//				else
//					inversion += 2;
//			}
		}
		return inversion;
		
	}
	public int h1(int[] list)
	// h1 = the number of misplaced tiles 
	{
		int gn = 0;
		for(int i = 0; i < list.length; i++)
		{
			if(list[i] != i && list[i] != 0)
				gn++;
		} 
		return gn;
	}
	public LinkedList<Node<EightPuzzle>> getChildren()
	{
		LinkedList<Node<EightPuzzle>> children = new LinkedList<Node<EightPuzzle>>();
		int loc = 0;
        int temparray[] = new int[puzzle.length];
		
		while(this.puzzle[loc] != 0)
		{
			loc++;
		}
		
		if(loc % 3 == 0){
		//add one child that swaps with right
			for(int x = 0; x < puzzle.length; x++)
			{
				temparray[x] = puzzle[x];
			}
			temparray[loc] = temparray[loc + 1];
			temparray[loc + 1] = 0;
			
			EightPuzzle rightP = new EightPuzzle(temparray, this.hueristic_type, this.g_n);
			rightP.setParent(this);
			Node<EightPuzzle> right = new Node<EightPuzzle>();
			children.add(right);
		}else if(loc % 3 == 1){
		//add one child swaps with right
			for(int x = 0; x < puzzle.length; x++)
			{
				temparray[x] = puzzle[x];
			}
			temparray[loc] = temparray[loc + 1];
			temparray[loc + 1] = 0;
			
			EightPuzzle rightP = new EightPuzzle(temparray, this.hueristic_type, this.g_n);
			rightP.setParent(this);
			Node<EightPuzzle> right = new Node<EightPuzzle>();
			children.add(right);
		//add one child swaps with left
			for(int x = 0; x < puzzle.length; x++)
			{
				temparray[x] = puzzle[x];
			}
			temparray[loc] = temparray[loc - 1];
			temparray[loc - 1] = 0;
			
			EightPuzzle leftP = new EightPuzzle(temparray, this.hueristic_type, this.g_n);
			leftP.setParent(this);
			Node<EightPuzzle> left = new Node<EightPuzzle>();
			children.add(left);
		}else
		{
		// add one child swaps with left
			for(int x = 0; x < puzzle.length; x++)
			{
				temparray[x] = puzzle[x];
			}
			temparray[loc] = temparray[loc - 1];
			temparray[loc - 1] = 0;
			
			EightPuzzle leftP = new EightPuzzle(temparray, this.hueristic_type, this.g_n);
			leftP.setParent(this);
			Node<EightPuzzle> left = new Node<EightPuzzle>();
			children.add(left);
		}
		
		if(loc / 3 == 0)
		{
		//add one child swaps with lower
			for(int x = 0; x < puzzle.length; x++)
			{
				temparray[x] = puzzle[x];
			}
			temparray[loc] = temparray[loc + 3];
			temparray[loc + 3] = 0;
			
			EightPuzzle downP = new EightPuzzle(temparray, this.hueristic_type, this.g_n);
			downP.setParent(this);
			Node<EightPuzzle> down = new Node<EightPuzzle>();
			children.add(down);
		}
		else if(loc / 3 == 1 )
		{
			//add one child, swap with upper
			for(int x = 0; x < puzzle.length; x++)
			{
				temparray[x] = puzzle[x];
			}
			temparray[loc] = temparray[loc - 3];
			temparray[loc - 3] = 0;
			
			EightPuzzle upP = new EightPuzzle(temparray, this.hueristic_type, this.g_n);
			upP.setParent(this);
			Node<EightPuzzle> up = new Node<EightPuzzle>();
			children.add(up);
			//add one child, swap with lower
			for(int x = 0; x < puzzle.length; x++)
			{
				temparray[x] = puzzle[x];
			}
			temparray[loc] = temparray[loc + 3];
			temparray[loc + 3] = 0;
			
			EightPuzzle downP = new EightPuzzle(temparray, this.hueristic_type, this.g_n);
			downP.setParent(this);
			Node<EightPuzzle> down = new Node<EightPuzzle>();
			children.add(down);
		}
		else
		{
			//add one child, swap with
			for(int x = 0; x < puzzle.length; x++)
			{
				temparray[x] = puzzle[x];
			}
			temparray[loc] = temparray[loc - 3];
			temparray[loc - 3] = 0;
			
			EightPuzzle upP = new EightPuzzle(temparray, this.hueristic_type, this.g_n);
			upP.setParent(this);
			Node<EightPuzzle> up = new Node<EightPuzzle>();
			children.add(up);
		}
	
				
		
		return children;
	}
	public int h2(int[] list)
	// h2 = the sum of the distances of the tiles from their goal positions
	// for each item find its goal position
	// calculate how many positions it needs to move to get into that position
	{
		int gn = 0;
		int row = 0;
		int col = 0;
		for(int i = 0; i < list.length; i++)
		{
			if(list[i] != 0)
			{
				row = list[i] / 3;
				col = list[i] % 3;
				row = Math.abs(row - (i / 3));
				col = Math.abs(col - (i % 3));
				gn += row;
				gn += col;
			}
			
		}
		return gn;
	}
//	public boolean puzzleCheck(EightPuzzle test){
//		for(int i = 0; i < this.puzzle.length; i++)
//		{
//			if(this.puzzle[i] != test.puzzle[i])
//				return false;
//		}
//		return true;
//	}
	public String toString()
	{
		String x = "";
		for(int i = 0; i < this.puzzle.length; i++){
			x += puzzle[i] + " ";
			if((i + 1) % 3 == 0)
				x += "\n";
		}
		return x;
	}
	public int compareTo(Object input) {
		
		
		if (this.f_n < ((EightPuzzle) input).getF_n())
			return 1;
		else if (this.f_n > ((EightPuzzle) input).getF_n())
			return -1;
		return 0;
	}
	
	public boolean equals(EightPuzzle test){

		for(int i = 0 ; i < this.puzzle.length; i++)
		{
			if(this.puzzle[i] != test.puzzle[i])
				return false;
		}
		return true;
	}

}
