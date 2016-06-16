import java.util.ArrayList;
import java.util.Random;


public class Annealing {
	
	public final static ArrayList<String> OFAPEL;
	
	static {		
		OFAPEL = new ArrayList<String>();
		Algorithme.readFile("ofapel", OFAPEL);
	}

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
	
	public static final Cube44 PERFECT = new Cube44();
	
	public static final String[] axis_set = {
		"x", "x'",
		"xx", "xx",
		"xy", "y'x'",
		"x'y", "y'x",
		"xy'", "yx'",
		"xz", "z'x'",
		"x'z", "z'x",
		"xz'", "zx'",
		"y", "y'",
		"yy", "yy",
		"yz", "z'y'",
		"y'z", "z'y",
		"yz'", "zy'",
		"z", "z'",
		"zz", "zz",
		"xyz", "z'y'x'",
		"xyz'", "zy'x'",
		"xy'z", "z'yx'",
		"xy'z'", "zyx'",
		"x'yz", "z'y'x",
		"x'yz'", "zy'x",
		"x'y'z", "z'yx",
		"x'y'z'", "zyx",
		"xxy", "y'xx",
		"xxy'", "yxx",
		"xxz", "z'xx",
		"xxz'", "zxx",
		"yyx", "x'yy",
		"yyx'", "xyy",
		"yyz", "z'yy",
		"yyz'", "zyy",
		"zzx", "x'zz",
		"zzx'", "xzz",
		"zzy", "y'zz",
		"zzy'", "yzz",
		"yxx", "xxy'",
		"y'xx", "xxy",
		"zxx", "xxz'",
		"z'xx", "xxz",
		"xyy", "yyx'",
		"x'yy", "yyx",
		"zyy", "yyz'",
		"z'yy", "yyz",
		"xzz", "zzx'",
		"x'zz", "zzx",
		"yzz", "zzy'",
		"y'zz", "zzy",
		"D", "D'",
		"D'", "D",
		"F", "F'",
		"F'", "F",
		"R", "R'",
		"R'", "R",
		"L", "L'",
		"L'", "L",
		"B", "B'",
		"B'", "B",
		"MD", "MD'",
		"MD'", "MD",
		"MF", "MF'",
		"MF'", "MF",
		"MR", "MR'",
		"MR'", "MR",
		"ML", "ML'",
		"ML'", "ML",
		"MB", "MB'",
		"MB'", "MB"
		};

	// -----
	
	public static Cube44 solve(Cube44 cube) {
		addAxisToFormulas();
		
		Random rand = new Random();
				
		Cube44 cur_best = new Cube44(cube);
		Cube44 best = new Cube44(cube);
		
		int best_evl = eval(best, 1);
		//int best_evl = best.valeur1();
		int cur_evl = best_evl;
		
		System.out.println(best_evl);
		
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 5; ++j) {
				Cube44 tmp = new Cube44(cur_best);
				
				boolean change = false;
				
				while (!change) {				
					int rnd_seq = rand.nextInt(OFAPEL.size());
					tmp.executeSeq(OFAPEL.get(rnd_seq));
					
					int tmp_evl = eval(tmp, 1);
					//int tmp_evl = tmp.valeur1();
					
					if (best_evl < tmp_evl) {
						best = tmp;
						best_evl = tmp_evl;
						change = true;
					}
					
					if (cur_evl < tmp_evl + 5) {
						cur_best = tmp;
						cur_evl = tmp_evl;
					}
				}
			}
			
			for (int j = 0; j < 5; ++j) {
				Cube44 tmp = new Cube44(cur_best);
				
				boolean change = false;
				
				while (!change) {				
					int rnd_mv = rand.nextInt(MOVES.length);
					tmp.executeMove(MOVES[rnd_mv]);
					
					int tmp_evl = eval(tmp, 1);
					//int tmp_evl = tmp.valeur1();
					
					if (best_evl < tmp_evl) {
						best = tmp;
						best_evl = tmp_evl;
					}
					
					if (cur_evl < tmp_evl + 5) {
						cur_best = tmp;
						cur_evl = tmp_evl;
						change = true;
					}
				}
			}
			
			cur_best.shuffle(4);
		}
		
		//System.out.println(eval(best));
		System.out.println(best.valeur1());
		
		return best;
	}
	
	//each time we try all ofapel moves and we keep the best result and we move on
	public static Cube44 solve2(Cube44 cube, int n_evl) {		
		Cube44 best = new Cube44(cube);
		Cube44 best_best = null;
		
		int best_evl = eval(best, n_evl);
		int best_best_evl = best_evl;
		System.out.println(best_evl);
		
		for (int i = 0; i < 400; ++i) {
			for (int j = 0; j < 60; ++j) {
				Cube44 cur = null;
				int cur_evl = 0;
				
				for (String ofapel_mv : OFAPEL) {
					Cube44 tmp = new Cube44(best);
					tmp.executeSeq(ofapel_mv);
					
					int tmp_evl = eval(tmp, n_evl);
										
					if (cur_evl < tmp_evl || cur == null) {
						cur = tmp;
						cur_evl = tmp_evl;
					}
				}
				
				for (String basic_mv : MOVES) {
					Cube44 tmp = new Cube44(best);
					
					for (int k = 0; k < 2; ++k) {
						tmp.executeMove(basic_mv);
						
						int tmp_evl = eval(tmp, n_evl);
						
						//System.out.println(tmp_evl + ((tmp_evl > best_evl) ? ">" : "<=") + best_evl);
						
						if (cur_evl < tmp_evl || cur == null) {
							cur = tmp;
							cur_evl = tmp_evl;
						}
					}
				}

				best = cur;
				best_evl = cur_evl;
				
				if (best_best_evl < best_evl || best_best == null) {
					best_best = best;
					best_best_evl = best_evl;
				}
			}
			
			while (best_best_evl - (best_evl = eval(best, n_evl)) < 5) {
				best.shuffle(1);
				
				if (best_best_evl < best_evl) {
					best_best = best;
					best_best_evl = best_evl;
				}
			}
		}
		
		System.out.println(best_best_evl + " " + best_evl);
		return best_best;
	}
	
	public static int eval(Cube44 cube, int n_evl) {
		if (n_evl == 1)
			return eval1(cube);
		else if (n_evl == 2)
			return eval2(cube);
		else if (n_evl == 3)
			return eval3(cube);
		else return cube.valeur1();
	}
	
	private static int eval1(Cube44 cube) {
		int evl = 0;
		for (String faceName : cube.faces.keySet()) {
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					if (cube.faces.get(faceName).elems[i][j] == PERFECT.faces.get(faceName).elems[i][j])
						++evl;
				}
			}
		}
		return evl;
	}
	
	//corners + edge
	private static int eval2(Cube44 cube) {
		int evl = 0;
		for (String faceName : cube.faces.keySet()) {
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					if (cube.faces.get(faceName).elems[i][j] == PERFECT.faces.get(faceName).elems[i][j]) {
						if (isCorner(i, j)) {
							evl += 3;
						} else if (isEdge(i, j)) {
							evl += 2;
						} else ++evl;
					}
				}
			}
		}
		return evl;
	}
	
	//centres
	private static int eval3(Cube44 cube) {
		int evl = 0;
		for (String faceName : cube.faces.keySet()) {
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					if (cube.faces.get(faceName).elems[i][j] == PERFECT.faces.get(faceName).elems[i][j]) {
						if (!isEdge(i, j)) {
							evl += 2;
						} else ++evl;
					}
				}
			}
		}
		return evl;
	}
	
	//beware, please use isCorner before isEdge
	private static boolean isEdge(int i, int j) {
		return i == 0 || j == 0 || i == 3 || j == 3;
	}
	
	private static boolean isCorner(int i, int j) {
		return (i == 0 && (j == 0 || j == 3)) ||
				(i == 3 && (j == 0 || j == 3));
	}


	private static void addAxisToFormulas() {
		ArrayList<String> tmp = new ArrayList<String>();
		int i;
		
		for (String formule : OFAPEL) {
			i = 0;
			while (i < axis_set.length) {
				StringBuilder mystr = new StringBuilder();
				mystr.append(axis_set[i]);
				mystr.append(formule);
				mystr.append(axis_set[i+1]);
				tmp.add(mystr.toString());
				i = i+2;
			}
		}
		
		for (String str : tmp) {
			OFAPEL.add(str);
		}
	}
}
