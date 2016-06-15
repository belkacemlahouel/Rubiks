import java.util.ArrayList;
import java.util.Random;


public class Annealing {
	
	private static ArrayList<String> formules;
	
	static {		
		formules = new ArrayList<String>();
		Algorithme.readFile("ofapel", formules);
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
	
	// -----
	
	public static Cube44 solve(Cube44 cube) {
		Random rand = new Random();
				
		Cube44 cur_best = new Cube44(cube);
		Cube44 best = new Cube44(cube);
		
		int best_evl = eval(best);
		int cur_evl = best_evl;
		
		System.out.println(best_evl);
		
		for (int i = 0; i < 1000; ++i) {
			for (int j = 0; j < 100; ++j) {
				Cube44 tmp = new Cube44(cur_best);
				
				boolean change = false;
				
				while (!change) {				
					int rnd_mv = rand.nextInt(MOVES.length);
					tmp.executeMove(MOVES[rnd_mv]);
					
					int tmp_evl = eval(tmp);
					
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
			//cur_best.shuffle(5);
		}
		
		System.out.println(eval(best));
		
		return best;
	}
	
	private static int eval(Cube44 cube) {
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
}
