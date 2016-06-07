import java.util.HashMap;


public class Cube44 {

	public HashMap<String, Face44> faces;
	
	public Cube44() {
		faces = new HashMap<>();
		faces.put("left", new Face44(1));
		faces.put("fore", new Face44(2)); //foreground
		faces.put("right", new Face44(3));
		faces.put("back", new Face44(4)); //background
		faces.put("top", new Face44(5));
		faces.put("bot", new Face44(6));
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Cube44:\n");
		
		//top
		for (int i = 0; i < 4; ++i) {
			str.append("\t      ");
			for (int j = 0; j < 4; ++j) {
				str.append(faces.get("top").elems[i][j]);
			}
			str.append("\n");
		}
		str.append("\n");
		
		//left + fore + right + back
		for (int i = 0; i < 4; ++i) {
			str.append("\t");
			for (int j = 0; j < 4; ++j)
				str.append(faces.get("left").elems[i][j]);
			
			str.append("  ");
			
			for (int j = 0; j < 4; ++j)
				str.append(faces.get("fore").elems[i][j]);
			
			str.append("  ");
			
			for (int j = 0; j < 4; ++j)
				str.append(faces.get("right").elems[i][j]);
			
			str.append("  ");
			
			for (int j = 0; j < 4; ++j)
				str.append(faces.get("back").elems[i][j]);
			
			str.append("\n");
		}
		str.append("\n");
		
		//bot
		for (int i = 0; i < 4; ++i) {
			str.append("\t      ");
			for (int j = 0; j < 4; ++j) {
				str.append(faces.get("bot").elems[i][j]);
			}
			str.append("\n");
		}
		
		return str.toString();
	}

	public void tright(int line) {
		int[] tmp = new int[4];
		int[] tmp2 = new int[4];
		
		for (int i = 0; i < 4; ++i) {
			tmp[i] = faces.get("fore").elems[line][i];
			faces.get("fore").elems[line][i] = faces.get("left").elems[line][i];
		}
		
		for (int i = 0; i < 4; ++i) {
			tmp2[i] = faces.get("right").elems[line][i];
			faces.get("right").elems[line][i] = tmp[i]; //faces.get("fore").elems[line][i];
		}
		
		for (int i = 0; i < 4; ++i) {
			tmp[i] = faces.get("back").elems[line][i];
			faces.get("back").elems[line][i] = tmp2[i];
		}
		
		for (int i = 0; i < 4; ++i) {
			faces.get("left").elems[line][i] = tmp[i];
		}
	}
	
	public void tleft(int line) {
		int[] tmp = new int[4];
		int[] tmp2 = new int[4];
		
		for (int i = 0; i < 4; ++i) {
			tmp[i] = faces.get("fore").elems[line][i];
			faces.get("fore").elems[line][i] = faces.get("right").elems[line][i];
		}
		
		for (int i = 0; i < 4; ++i) {
			tmp2[i] = faces.get("left").elems[line][i];
			faces.get("left").elems[line][i] = tmp[i]; //faces.get("fore").elems[line][i];
		}
		
		for (int i = 0; i < 4; ++i) {
			tmp[i] = faces.get("back").elems[line][i];
			faces.get("back").elems[line][i] = tmp2[i];
		}
		
		for (int i = 0; i < 4; ++i) {
			faces.get("right").elems[line][i] = tmp[i];
		}
	}
	
	public void tdown(int col) {
		int[] tmp = new int[4];
		int[] tmp2 = new int[4];
		
		for (int i = 0; i < 4; ++i) {
			tmp[i] = faces.get("fore").elems[i][col];
			faces.get("fore").elems[i][col] = faces.get("top").elems[i][col];
		}
		
		for (int i = 0; i < 4; ++i) {
			tmp2[i] = faces.get("bot").elems[i][col];
			faces.get("bot").elems[i][col] = tmp[i]; //faces.get("fore").elems[line][i];
		}
		
		for (int i = 0; i < 4; ++i) {
			tmp[i] = faces.get("back").elems[i][col];
			faces.get("back").elems[i][col] = tmp2[i];
		}
		
		for (int i = 0; i < 4; ++i) {
			faces.get("top").elems[i][col] = tmp[i];
		}
	}
	
	public void tup(int col) {
		int[] tmp = new int[4];
		int[] tmp2 = new int[4];
		
		for (int i = 0; i < 4; ++i) {
			tmp[i] = faces.get("fore").elems[i][col];
			faces.get("fore").elems[i][col] = faces.get("bot").elems[i][col];
		}
		
		for (int i = 0; i < 4; ++i) {
			tmp2[i] = faces.get("top").elems[i][col];
			faces.get("top").elems[i][col] = tmp[i]; //faces.get("fore").elems[line][i];
		}
		
		for (int i = 0; i < 4; ++i) {
			tmp[i] = faces.get("back").elems[i][col];
			faces.get("back").elems[i][col] = tmp2[i];
		}
		
		for (int i = 0; i < 4; ++i) {
			faces.get("bot").elems[i][col] = tmp[i];
		}
	}
}
