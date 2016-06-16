import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

class SequencesEval {
	public String[] seq;
	public int evl;
	
	public SequencesEval(String[] _seq, int _evl) {
		seq = _seq;
		evl = _evl;
	}
}

public class SolutionAnnealing {

	/*
	 * we use the "number of God" in this solution
	 * the number of god is supposably < 75
	 * we create a given "path" to the solution
	 * and we make mutations on it
	 * if the path becomes better we keep it and we keep going
	 * 
	 * improvement:
	 * we keep in memory a list of sequences with their evaluation (evaluation of the state)
	 * we remove them if we find better OR if it cannot be improved after X trials
	 */
	
	public final static int NEI_SIZE = 5000;
	public static ArrayList<SequencesEval> paths = new ArrayList<>(NEI_SIZE);
	
	public final static int GODS_NB = 60;
	
	public static final String[] BASIC_MOVES = {
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
	
	public final static ArrayList<String> MOVES;
	
	static {
		MOVES = new ArrayList<>();
		
		ArrayList<String> tmp = new ArrayList<String>();
		int i;
		
		for (String formule : BASIC_MOVES) {
			MOVES.add(formule);
			i = 0;
			while (i < Annealing.axis_set.length) {
				StringBuilder mystr = new StringBuilder();
				mystr.append(Annealing.axis_set[i]);
				mystr.append(formule);
				mystr.append(Annealing.axis_set[i+1]);
				tmp.add(mystr.toString());
				i = i+2;
			}
		}
		
		for (String str : tmp) {
			MOVES.add(str);
		}
	}
	
	public static Cube44 solve(Cube44 cube, int evl_nb) {
		Random rand = new Random();
		
		System.out.println(Annealing.eval(cube, evl_nb));
		
		//generation of first neighbourhood, randomly
		for (int j = 0; j < NEI_SIZE; ++j) {
			//generation of a random path
			String[] cur = new String[GODS_NB];
			for (int i = 0; i < GODS_NB; ++i) {
				cur[i] = MOVES.get(rand.nextInt(GODS_NB));
			}
			
			paths.add(new SequencesEval(cur, applySeq(cube, cur, evl_nb)));
		}
		
		//sorting paths with eval
		paths.sort(new Comparator<SequencesEval>() {
			@Override
			public int compare(SequencesEval o1, SequencesEval o2) {
				if (o1.evl > o2.evl)
					return -1;
				else if (o1.evl < o2.evl)
					return 1;
				else
					return 0;
			}
		});
		
		//improving paths with random mutations
		for (int i = 0; i < 5000; ++i) {
			int pos_rnd = rand.nextInt(GODS_NB);
			int mut_rnd = rand.nextInt(MOVES.size());
			
			String[] seq = paths.get(0).seq.clone();
			seq[pos_rnd] = MOVES.get(mut_rnd);
			
			int tmp_evl = applySeq(cube, seq, evl_nb);
			paths.add(new SequencesEval(seq, tmp_evl));
			
			//sorting paths with eval
			paths.sort(new Comparator<SequencesEval>() {
				@Override
				public int compare(SequencesEval o1, SequencesEval o2) {
					if (o1.evl > o2.evl)
						return -1;
					else if (o1.evl < o2.evl)
						return 1;
					else
						return 0;
				}
			});
			
			//removing the least good
			paths.remove(paths.size()-1);
		}
		
		//best solution
		System.out.println(paths.get(0).evl);
		cube.executeSeq(paths.get(0).seq);
		return cube;
	}
	
	private static int applySeq(Cube44 cube, String[] seq, int evl_nb) {
		cube.executeSeq(seq);
		if (evl_nb > -1)
			return Annealing.eval(cube, evl_nb);
		return 0;
	}
}
