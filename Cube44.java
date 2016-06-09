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
	
	public Cube44(Cube44 copy) {
		faces = new HashMap<>();
		faces.put("left", new Face44(copy.faces.get("left")));
		faces.put("fore", new Face44(copy.faces.get("fore"))); //foreground
		faces.put("right", new Face44(copy.faces.get("right")));
		faces.put("back", new Face44(copy.faces.get("back"))); //background
		faces.put("top", new Face44(copy.faces.get("top")));
		faces.put("bot", new Face44(copy.faces.get("bot")));
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

	//turn VERS la droite
	public void tright(int line) {
		System.out.println("tright " + line);
		
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
		
		//turn elements of the face 
		if (line == 0) {
			faces.get("top").turn(true);
		} else if (line == 3) {
			faces.get("bot").turn(true);
		}
	}
	
	//turn VERS la gauche
	public void tleft(int line) {
		System.out.println("tleft " + line);
		
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
		
		//turn elements of the face 
		if (line == 0) {
			faces.get("top").turn(false);
		} else if (line == 3) {
			faces.get("bot").turn(false);
		}
	}
	
	public void tdown(int col) {
		System.out.println("tdown " + col);
		
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
			tmp[i] = faces.get("back").elems[3-i][3-col];
			faces.get("back").elems[3-i][3-col] = tmp2[i];
		}
		
		for (int i = 0; i < 4; ++i) {
			faces.get("top").elems[i][col] = tmp[i];
		}
		
		//turn elements of the face 
		if (col == 0) {
			faces.get("left").turn(true);
		} else if (col == 3) {
			faces.get("right").turn(false);
		}
	}
	
	public void tup(int col) {
		System.out.println("tup " + col);
		
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
			tmp[i] = faces.get("back").elems[3-i][3-col];
			faces.get("back").elems[3-i][3-col] = tmp2[i];
		}
		
		for (int i = 0; i < 4; ++i) {
			faces.get("bot").elems[i][col] = tmp[i];
		}
		
		//turn elements of the face 
		if (col == 0) {
			faces.get("left").turn(false);
		} else if (col == 3) {
			faces.get("right").turn(true);
		}
	}

	public void tup() {
		System.out.println("tup");
		
		faces.get("back").transposeLC();
		swapFaces("fore", "bot");
		swapFaces("bot", "back");
		swapFaces("back", "top");
		faces.get("left").turn(false);
		faces.get("right").turn(true);
		faces.get("back").transposeLC();
	}
	
	public void tdown() {
		System.out.println("tdown");
		
		faces.get("back").transposeLC();
		swapFaces("fore", "top");
		swapFaces("top", "back");
		swapFaces("back", "bot");
		faces.get("left").turn(true);
		faces.get("right").turn(false);
		faces.get("back").transposeLC();
	}
	
	public void tright() {
		System.out.println("tright");
		
		swapFaces("fore", "left");
		swapFaces("left", "back");
		swapFaces("back", "right");
		faces.get("top").turn(false);
		faces.get("bot").turn(true);
	}
	
	public void tleft() {
		System.out.println("tleft");
		
		swapFaces("fore", "right");
		swapFaces("right", "back");
		swapFaces("back", "left");
		faces.get("top").turn(true);
		faces.get("bot").turn(false);
	}
	
	private void swapFaces(String a, String b) {
		Face44 face = faces.get(a);
		faces.replace(a, faces.get(b));
		faces.replace(b, face);
	}
	
	@SuppressWarnings("unused")
	private boolean equals(Cube44 o) {
		if (o.faces.get("left").equals(faces.get("left")) &&
				o.faces.get("right").equals(faces.get("right")) &&
				o.faces.get("fore").equals(faces.get("fore")) &&
				o.faces.get("back").equals(faces.get("back")) &&
				o.faces.get("top").equals(faces.get("top")) &&
				o.faces.get("bot").equals(faces.get("bot")))
			return true;
		
		return false;
	}
}
