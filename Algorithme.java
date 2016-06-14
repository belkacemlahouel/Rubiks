import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Algorithme {
	
	static private int NN = 1000; 
	static private int max_tabu_size = 800; 
	private HashSet<String> formules;
	public Algorithme(){
		formules = new HashSet<String>();
		readFile("ofapel");
	}
	
	
	private boolean isBetter1(Cube44 c1, Cube44 c2){
		return c1.valeur1() > c2.valeur1();
	}
	
	//permet de generer le voisinage de cube
	private ArrayList<Cube44> generateNeigh(Cube44 cube){
		
		ArrayList<Cube44> res = new ArrayList<Cube44>();
		for(String formule: formules){
			Cube44 tmp = new Cube44(cube);
			tmp.executeSeq(formule);
			res.add(tmp);

			Cube44 tmp0 = new Cube44(cube);
			tmp0.executeSeq("x"+formule);
			res.add(tmp);

			Cube44 tmp1 = new Cube44(cube);
			tmp1.executeSeq("x'"+formule);
			res.add(tmp);
			Cube44 tmp2 = new Cube44(cube);
			tmp2.executeSeq("y"+formule);
			res.add(tmp);
			Cube44 tmp3 = new Cube44(cube);
			tmp3.executeSeq("y'"+formule);
			res.add(tmp);
			Cube44 tmp4 = new Cube44(cube);
			tmp4.executeSeq("z"+formule);
			res.add(tmp);
			Cube44 tmp5 = new Cube44(cube);
			tmp5.executeSeq("z'"+formule);
			res.add(tmp);
		}
		return res;
	}
	
	//Recherche tabou
	public Cube44 tabuSearch(Cube44 c){
		Cube44 res = new Cube44(c), tmpSolu = new Cube44(c), bestNeigh = new Cube44(c);
		ArrayList<Cube44> tabu_list = new ArrayList<Cube44>();
		int stop = 0;
		System.err.println("Valeur: " + res.valeur1());
		while(stop < NN){
			boolean first_iteration = true;
			//generation du voisinage
			for(Cube44 cube : generateNeigh(tmpSolu)){
				if(first_iteration){
					bestNeigh.set(cube);
					first_iteration = false;
				}
				else if (!tabu_list.contains(cube) && (isBetter1(cube, bestNeigh))) {
					bestNeigh.set(cube);
				}
			}
			tmpSolu.set(bestNeigh);
			if(isBetter1(tmpSolu, res)){
				System.err.println("Valeur: " + res.valeur1() + " < " + tmpSolu.valeur1());
				res.set(tmpSolu);
			}
			//Mise a jour de la liste tabou
			tabu_list.add(bestNeigh);
			if(tabu_list.size() > max_tabu_size){
				tabu_list.remove(0);
			}
			if(stop%19==0) System.err.println(stop*100.0/NN);
			stop++;
		}
		System.err.println("Valeur: " + res.valeur1());
		return res;
	}
	
	public void readFile(String name){
		
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
	
}
