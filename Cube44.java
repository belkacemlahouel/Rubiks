import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Cube44 {
	
	public static final HashSet<Character> BASIC_MOVES, M_MOVES;
	public static final String[] MOVES = {
		//Basic moves
		"R", "R2", "R'",
		"L", "L2", "L'",
		"U", "U2", "U'",
		"D", "D2", "D'",
		"F", "F2", "F'",
		"B", "B2", "B'",
		//x, y, z
		"x", "x'",
		"y", "y'",
		"z", "z'",
		//M moves
		"MU", "MU2", "MU'",
		"ML", "ML2", "ML'",
		"MR", "MR2", "MR'",
		"MD", "MD2", "MD'"};
	
	static {
		BASIC_MOVES = new HashSet<Character>();
		BASIC_MOVES.add('R');
		BASIC_MOVES.add('L');
		BASIC_MOVES.add('U');
		BASIC_MOVES.add('D');
		BASIC_MOVES.add('F');
		BASIC_MOVES.add('B');
		//BASIC_MOVES.add('M');
		BASIC_MOVES.add('x');
		BASIC_MOVES.add('y');
		BASIC_MOVES.add('z');
		
		M_MOVES = new HashSet<Character>();
		M_MOVES.add('U');
		M_MOVES.add('L');
		M_MOVES.add('R');
		M_MOVES.add('D');
		M_MOVES.add('B');
		M_MOVES.add('F');
	}

	public HashMap<String, Face44> faces;
	public String[] rpz;
	
	public Cube44() {
		faces = new HashMap<>();
		faces.put("left", new Face44(1));
		faces.put("fore", new Face44(2)); //foreground
		faces.put("right", new Face44(3));
		faces.put("back", new Face44(4)); //background
		faces.put("top", new Face44(5));
		faces.put("bot", new Face44(0));
		
		//ansi color, 31m = red, 31;1m = bright red
		//install: https://marketplace.eclipse.org/content/ansi-escape-console
		//drag and drop in eclipse
		rpz = new String[6];
		rpz[0] = "\033[31;1m █"; //red
		rpz[1] = "\033[34;1m █"; //blue
		rpz[2] = "\033[32;1m █"; //green
		rpz[3] = "\033[0;36m █"; //cyan
		rpz[4] = "\033[33;1m █"; //yellow
		rpz[5] = "\033[35;1m █"; //pink
	}
	
	public Cube44(Cube44 copy) {
		faces = new HashMap<>();
		faces.put("left", new Face44(copy.faces.get("left")));
		faces.put("fore", new Face44(copy.faces.get("fore"))); //foreground
		faces.put("right", new Face44(copy.faces.get("right")));
		faces.put("back", new Face44(copy.faces.get("back"))); //background
		faces.put("top", new Face44(copy.faces.get("top")));
		faces.put("bot", new Face44(copy.faces.get("bot")));

		//ansi color, 31m = red, 31;1m = bright red
		//install: https://marketplace.eclipse.org/content/ansi-escape-console
		//drag and drop in eclipse
		rpz = new String[6];
		rpz[0] = "\033[31;1m █"; //red
		rpz[1] = "\033[34;1m █"; //blue
		rpz[2] = "\033[32;1m █"; //green
		rpz[3] = "\033[0;36m █"; //cyan
		rpz[4] = "\033[33;1m █"; //yellow
		rpz[5] = "\033[35;1m █"; //pink
	}
	
	public void set(Cube44 copy) {
		faces.clear();
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
			str.append("\t          ");
			for (int j = 0; j < 4; ++j) {
				str.append(rpz[faces.get("top").elems[i][j]]);
			}
			str.append("\n");
		}
		str.append("\n");
		
		//left + fore + right + back
		for (int i = 0; i < 4; ++i) {
			str.append("\t");
			for (int j = 0; j < 4; ++j)
				str.append(rpz[faces.get("left").elems[i][j]]);
			
			str.append("  ");
			
			for (int j = 0; j < 4; ++j)
				str.append(rpz[faces.get("fore").elems[i][j]]);
			
			str.append("  ");
			
			for (int j = 0; j < 4; ++j)
				str.append(rpz[faces.get("right").elems[i][j]]);
			
			str.append("  ");
			
			for (int j = 0; j < 4; ++j)
				str.append(rpz[faces.get("back").elems[i][j]]);
			
			str.append("\n");
		}
		str.append("\n");
		
		//bot
		for (int i = 0; i < 4; ++i) {
			str.append("\t          ");
			for (int j = 0; j < 4; ++j) {
				str.append(rpz[faces.get("bot").elems[i][j]]);
			}
			str.append("\n");
		}
		
		return str.toString();
	}

	//turn VERS la droite
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
		
		//turn elements of the face 
		if (line == 0) {
			faces.get("top").turn(false);
		} else if (line == 3) {
			faces.get("bot").turn(true);
		}
	}
	
	//turn VERS la gauche
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
		
		//turn elements of the face 
		if (line == 0) {
			faces.get("top").turn(true);
		} else if (line == 3) {
			faces.get("bot").turn(false);
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
		
		faces.get("back").transposeLC();
		swapFaces("fore", "bot");
		swapFaces("bot", "back");
		swapFaces("back", "top");
		faces.get("left").turn(false);
		faces.get("right").turn(true);
		faces.get("back").transposeLC();
	}
	
	public void tdown() {
		
		faces.get("back").transposeLC();
		swapFaces("fore", "top");
		swapFaces("top", "back");
		swapFaces("back", "bot");
		faces.get("left").turn(true);
		faces.get("right").turn(false);
		faces.get("back").transposeLC();
	}
	
	public void tright() {
		
		swapFaces("fore", "left");
		swapFaces("left", "back");
		swapFaces("back", "right");
		faces.get("top").turn(false);
		faces.get("bot").turn(true);
	}
	
	public void tleft() {
		
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
	
	public boolean equals(Cube44 o) {
		if (o.faces.get("left").equals(faces.get("left")) &&
				o.faces.get("right").equals(faces.get("right")) &&
				o.faces.get("fore").equals(faces.get("fore")) &&
				o.faces.get("back").equals(faces.get("back")) &&
				o.faces.get("top").equals(faces.get("top")) &&
				o.faces.get("bot").equals(faces.get("bot")))
			return true;
		
		return false;
	}
	
	//retourne la somme du nombre d'éléments de même couleur maximale de chaque faces
	public int valeur1(){
		int res = 0;
		for(Face44 face :faces.values()){
			res += face.valeur1();
		}
		return res;
	}
	
	public boolean isSolved(){
		for(Face44 face:faces.values()){
			if(!face.isUnicolor()) return false;
		}
		return true;
	}
	
	public void shuffle() {
		int nb_moves = MOVES.length;
		Random rand = new Random();
		
		for (int i = 0; i < 50; ++i)
			executeMove(MOVES[rand.nextInt(nb_moves)]);
	}
	
	
	public void shuffle(int nb_sh) {
		int nb_moves = MOVES.length;
		Random rand = new Random();
		
		for (int i = 0; i < nb_sh; ++i)
			executeMove(MOVES[rand.nextInt(nb_moves)]);
	}
	
	public void executeSeq(String[] moves) {
		for (String mv : moves) {
			executeSeq(mv);
		}
	}
	
	public void executeSeq(String seq) {
		int i = 0;
		
		while (i < seq.length()) {
			String move = "";
			boolean _m = false;
			
			while (i < seq.length() && seq.charAt(i) == ' ')
				++i;
			
			if (i < seq.length() && (BASIC_MOVES.contains(seq.charAt(i)) || seq.charAt(i) == 'M')) {
				_m = seq.charAt(i) == 'M';
				move += seq.charAt(i);
				++i;
			}
			
			while (i < seq.length() && seq.charAt(i) != ' ' && !BASIC_MOVES.contains(seq.charAt(i)) && seq.charAt(i) != 'M') {
				move += seq.charAt(i);
				++i;
			}
			
			if (_m && i < seq.length() && seq.charAt(i) != ' ' && M_MOVES.contains(seq.charAt(i))) {
				move += seq.charAt(i);
				++i;
			}
			
			if (_m && i < seq.length() && seq.charAt(i) != ' ' && !BASIC_MOVES.contains(seq.charAt(i)) && seq.charAt(i) != 'M') {
				move += seq.charAt(i);
				++i;
			}
			
			//System.err.println(move);
			executeMove(move);
		}
	}
	
	public void executeMove(String move) {
		switch(move) {
		case "R":
			R();
			break;
		case "R2":
			R2();
			break;
		case "R'":
			Rinv();
			break;
			
		case "L":
			L();
			break;
		case "L2":
			L2();
			break;
		case "L'":
			Linv();
			break;
			
		case "U":
			U();
			break;
		case "U2":
			U2();
			break;
		case "U'":
			Uinv();
			break;
			
		case "D":
			D();
			break;
		case "D2":
			D2();
			break;
		case "D'":
			Dinv();
			break;
			
		case "F":
			F();
			break;
		case "F2":
			F2();
			break;
		case "F'":
			Finv();
			break;
			
		case "B":
			B();
			break;
		case "B2":
			B2();
			break;
		case "B'":
			Binv();
			break;
			
		case "MU":
			MU();
			break;
		case "MU2":
			MU2();
			break;
		case "MU'":
			MUinv();
			break;
			
		case "ML":
			ML();
			break;
		case "ML2":
			ML2();
			break;
		case "ML'":
			MLinv();
			break;
			
		case "MR":
			MR();
			break;
		case "MR2":
			MR2();
			break;
		case "MR'":
			MRinv();
			break;
			
		case "MD":
			MD();
			break;
		case "MD2":
			MD2();
			break;
		case "MD'":
			MDinv();
			break;

		case "MB":
			MB();
			break;
		case "MB2":
			MB2();
			break;
		case "MB'":
			MBinv();
			break;
		case "MF":
			MF();
			break;
		case "MF2":
			MF2();
			break;
		case "MF'":
			MFinv();
			break;
			
		case "x":
			XX();
			break;
		case "x'":
			XXinv();
			break;

		case "y":
			YY();
			break;
		case "y'":
			YYinv();
			break;

		case "z":
			ZZ();
			break;
		case "z'":
			ZZinv();
			break;

		default:
			System.err.println("Move not recognized: " + move);
		}
	}
	
	public void R(){tup(3);}
	public void R2(){tup(3); tup(3);}
	public void Rinv(){tdown(3);}
	public void L(){tdown(0);}
	public void L2(){tdown(0); tdown(0);}
	public void Linv(){tup(0);}
	public void U(){tleft(0);}
	public void U2(){tleft(0); tleft(0);}
	public void Uinv(){tright(0);}
	public void D(){tright(3);}
	public void D2(){tright(3); tright(3);}
	public void Dinv(){tleft(3);}
	public void F(){tright(); tup(3); tleft();}
	public void F2(){tright(); tup(3); tup(3); tleft();}
	public void Finv(){tright(); tdown(3); tleft();}
	public void B(){tleft(); tup(3); tright();}
	public void B2(){tleft(); tup(3); tup(3); tright();}
	public void Binv(){tleft(); tdown(3); tright();}
	public void MU(){tleft(1);}
	public void MU2(){tleft(1); tleft(1);}
	public void MUinv(){tright(1);}
	public void ML(){tdown(1);}
	public void ML2(){tdown(1); tdown(1);}
	public void MLinv(){tup(1);}
	public void MR(){tup(2);}
	public void MR2(){tup(2); tup(2);}
	public void MRinv(){tdown(2);}
	public void MD(){tright(2);}
	public void MD2(){tright(2); tright(2);}
	public void MDinv(){tleft(2);}
	public void MB(){tleft(); tdown(2); tright();}
	public void MB2(){tleft(); tdown(2); tdown(2); tright();}
	public void MBinv(){tleft(); tup(2); tright();}
	public void MF(){tleft(); tup(1); tright();}
	public void MF2(){tleft(); tup(1); tup(1); tright();}
	public void MFinv(){tleft(); tdown(1); tright();}
	public void XX(){tup();} //rotation du cube selon l'axe x
	public void XXinv(){tdown();} //rotation inverse du cube selon l'axe x
	public void YY(){tleft();} //rotation du cube selon l'axe x
	public void YYinv(){tright();} //rotation inverse du cube selon l'axe x
	public void ZZ(){tdown(); tright(); tup();} //rotation du cube selon l'axe x
	public void ZZinv(){tdown(); tleft(); tup();} //rotation inverse du cube selon l'axe x
}
