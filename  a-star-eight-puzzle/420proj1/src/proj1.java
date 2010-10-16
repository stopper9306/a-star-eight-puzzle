
public class proj1 {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		int[] p1d = {1, 2, 4, 0, 5, 6, 8, 3, 7};
		
		EightPuzzle start = new EightPuzzle(p1d, 1, 0);
		Node<EightPuzzle> root = new Node<EightPuzzle>();
		root.setData(start);
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
	
	
}
