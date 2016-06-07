
public class Face44 {

	public int[][] elems;
	
	public Face44(int color) {
		elems = new int[4][4]; //line, col
		
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				elems[i][j] = color;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Face44:\n");
		
		for (int i = 0; i < 4; ++i) {
			str.append("\t");
			for (int j = 0; j < 4; ++j) {
				str.append(elems[i][j]);
			}
			str.append("\n");
		}
		
		return str.toString();
	}
	
	public void turn(boolean horaire) {
		boolean[][] bx = new boolean[4][4];
		
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				if (bx[i][j])
					continue;
				
				int x = elems[i][j];
				elems[i][j] = elems[j][i];
				elems[j][i] = x;
				
				bx[i][j] = true;
				bx[j][i] = true;
			}
		}
		
		if (!horaire) {
			//reverse each column
			for (int i = 0; i < 4; ++i) {
				int tmp = elems[0][i];
				elems[0][i] = elems[3][i];
				elems[3][i] = tmp;
				
				tmp = elems[1][i];
				elems[1][i] = elems[2][i];
				elems[2][i] = tmp;
			}
		} else {
			//reverse each line
			for (int i = 0; i < 4; ++i) {
				int tmp = elems[i][0];
				elems[i][0] = elems[i][3];
				elems[i][3] = tmp;
				
				tmp = elems[i][1];
				elems[i][1] = elems[i][2];
				elems[i][2] = tmp;
			}
		}
	}
}
