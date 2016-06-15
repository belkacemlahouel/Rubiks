import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Algorithme {
	
	static private int NN = 6000; 
	static private int max_tabu_size = 800; 
	private ArrayList<String> formules;
	private ArrayList<String> formule_rand;
	public Algorithme(){
		formules = new ArrayList<String>();
		formule_rand = new ArrayList<String>();
		readFile("ofapel", formules);
		readFile("basic_move", formule_rand);
	}
	
	
	private boolean isBetter1(Cube44 c1, Cube44 c2){
		return c1.valeur1() >= c2.valeur1();
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
			tmp1.executeSeq("xx"+formule);
			res.add(tmp);
			Cube44 tmp2 = new Cube44(cube);
			tmp2.executeSeq("y"+formule);
			res.add(tmp);
			Cube44 tmp3 = new Cube44(cube);
			tmp3.executeSeq("yy"+formule);
			res.add(tmp);
			Cube44 tmp4 = new Cube44(cube);
			tmp4.executeSeq("z"+formule);
			res.add(tmp);
			Cube44 tmp5 = new Cube44(cube);
			tmp5.executeSeq("zz"+formule);
			res.add(tmp);
		}
		return res;
	}
	
	//Recherche tabou
	public Cube44 tabuSearch(Cube44 c){
		Cube44 res = new Cube44(c), tmpSolu = new Cube44(c), bestNeigh = new Cube44(c);
		ArrayList<Cube44> tabu_list = new ArrayList<Cube44>();
		int stop = 0, shuffle_count = 0, current_value = 0;
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
			if(!isBetter1(res, tmpSolu)){
				System.err.println("Valeur: " + res.valeur1() + " < " + tmpSolu.valeur1());
				res.set(tmpSolu);
			}
			if(current_value == tmpSolu.valeur1()){
				//si on reste bloquer à une certaine valeur trop longtemps
				//on shuffle de 5 mouvements
				shuffle_count++;
				if(shuffle_count > 10){
					tmpSolu.shuffle(5);
					shuffle_count=0;
				}
			}
			else{
				shuffle_count = 0;
			}
			current_value = tmpSolu.valeur1();
			//Mise a jour de la liste tabou
			tabu_list.add(bestNeigh);
			if(tabu_list.size() > max_tabu_size){
				tabu_list.remove(0);
			}
			if(stop%30==0) System.err.println(stop*100.0/NN);
			stop++;
		}
		System.err.println("Valeur: " + res.valeur1());
		return res;
	}
	
	public void readFile(String name, ArrayList<String> formules){
		
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
