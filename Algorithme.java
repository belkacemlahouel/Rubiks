import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Algorithme {
	
	static private int NN = 700;  //nombre d'iteration pour searchTabu
	static private int max_tabu_size = 400; 
	private ArrayList<String> formules;
	private ArrayList<String> formule_rand;
	
	public static final Cube44 PERFECT = new Cube44();
	public static final String[] axis_set = 
		{
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
	
	public Algorithme(){
		formules = new ArrayList<String>();
		formule_rand = new ArrayList<String>();
		readFile("ofapel", formules);
		readFile("basic_move", formule_rand);
		addAxisToFormulas();
		
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
	
	private boolean isBetter(Cube44 c1, Cube44 c2){
		return eval(c1) >= eval(c2);
	}
	
	//fonction utilitaire pour traiter les differentes formules issus de la rotation du cube
	void addAxisToFormulas(){
		ArrayList<String> tmp = new ArrayList<String>();
		int i;
		for(String formule : formules){
			i = 0;
			while(i < axis_set.length){
				StringBuilder mystr = new StringBuilder();
				mystr.append(axis_set[i]);
				mystr.append(formule);
				mystr.append(axis_set[i+1]);
				tmp.add(mystr.toString());
				i = i+2;
			}
		}
		for(String str:tmp){
			formules.add(str);
		}
	}
	
	//permet de generer le voisinage de cube
	private ArrayList<Cube44> generateNeigh(Cube44 cube){
		ArrayList<Cube44> res = new ArrayList<Cube44>();
		for(String formule: formules){
			Cube44 tmp = new Cube44(cube);
			tmp.executeSeq(formule);
			res.add(tmp);
		}
		return res;
	}
	
	//Recherche tabou revisee
	public Cube44 tabuSearch(Cube44 c){
		Cube44 res = new Cube44(c), tmpSolu = new Cube44(c), bestNeigh = new Cube44(c);
		ArrayList<Cube44> tabu_list = new ArrayList<Cube44>();
		int stop = 0, shuffle_count = 0, current_value = 0;
		StringBuilder complete_moves = new StringBuilder(); //mouvements totaux effectuee
		while(stop < NN && !res.isSolved()){
			String tmpstr = "";
			int iteration = 0;
			for(Cube44 cube : generateNeigh(tmpSolu)){ //generation du voisinage
				if(iteration == 0){
					//on choisit tout d'abord le premier voisin
					bestNeigh.set(cube);
				}
				else if (!tabu_list.contains(cube) && (isBetter(cube, bestNeigh))) {
					tmpstr = formules.get(iteration);
					bestNeigh.set(cube);
				}
				iteration++;
			}
			complete_moves.append(tmpstr); 
			tmpSolu.set(bestNeigh);
			if(!isBetter(res, tmpSolu)){
				System.err.println("Valeur: " + eval(res) + " < " + eval(tmpSolu));
				res.set(tmpSolu);
			}

			if(current_value == eval(tmpSolu)){
				//si on reste bloquer sur une certaine valeur trop longtemps
				//on shuffle de 5 mouvements
				shuffle_count++;
				if(shuffle_count > 20){
					complete_moves.append(tmpSolu.shuffle1(5));
					shuffle_count=0;
				}
			}
			else{
				shuffle_count = 0;
			}
			current_value = eval(tmpSolu);
			
			//Mise a jour de la liste tabou
			tabu_list.add(bestNeigh);
			if(tabu_list.size() > max_tabu_size){
				tabu_list.remove(0);
			}
			if(stop%30==0) 
				System.err.println(stop*100.0/NN);
			stop++;
		}
		System.err.println("Valeur: " + res.valeur1());
		System.err.println("Mouvements : "+complete_moves.toString());
		return res;
	}
	
	//fonction utilitaire pour la lecture de fichier
	public static void readFile(String name, ArrayList<String> formules){
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("./src/" + name + ".txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				formules.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	//algorithme purement aleatoire
	public Cube44 randomAlgo(Cube44 c){
		Random randomGenerator = new Random();
		int len = formule_rand.size();
		int iter = 0;
		int max = 0;
		while(!c.isSolved()){
			c.executeSeq(formule_rand.get(randomGenerator.nextInt(len)));
			iter++;
			if(max < c.valeur1()) max = c.valeur1();
			if(iter%1000000 == 0){
				System.err.println(c);
				System.err.println(max);
			}
		}
		return c;
	}
	
}
