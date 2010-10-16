
public class EightPuzzle implements Comparable <Object> {
	
	
	int[] puzzle = new int[9];
	int h_n= 0;
	int hueristic_type = 0;
	int g_n = 0;
	int f_n = 0;
	
	
	public EightPuzzle(int[] p, int h_type, int branching_factor)
	{
		this.puzzle = p;
		this.hueristic_type = h_type;
		this.h_n = (h_type == 1) ?  h1(p) : h2(p);
		this.g_n = branching_factor;
		this.f_n = h_n + g_n;
	}
	public int getF_n()
	{
		return f_n;
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
			if(list[i] == 0)
			{
				if(i < 3)
					inversion += 1;
				else if(i > 5)
					inversion += 3;
				else
					inversion += 2;
			}
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
	
	public int compareTo(Object input) {
		
		
		if (this.f_n < ((EightPuzzle) input).getF_n())
			return 1;
		else if (this.f_n > ((EightPuzzle) input).getF_n())
			return -1;
		return 0;
	}

}
