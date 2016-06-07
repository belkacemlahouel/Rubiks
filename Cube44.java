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
}
