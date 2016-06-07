
public class Face44 {

	public int[][] elems;
	
	public Face44(int color) {
		elems = new int[4][4];
		
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				elems[i][j] = color;
			}
		}
	}
}
