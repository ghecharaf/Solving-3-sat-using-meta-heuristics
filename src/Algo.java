import java.util.ArrayList;

public class Algo {
    public ArrayList<Solution> tabooList = new ArrayList<Solution>();
    public Dance danceList;
    public ArrayList<Solution> bee = new ArrayList<Solution>();

    public static Integer flip = 5;
    public static Integer maxIteration = 100;
    public static Integer maxChance = 8;
    public static Integer nbBee = 15;
    public static Integer maxLocal = 3;

    public String path;

    Integer nbChance;
    Integer nbIteration;
    Integer nbLocal;

    public Solution sref;
    public Solution bestSolution ;

    public Algo(String path,int it,int nbb,int f,int ch){
        this.path = path;
        danceList = new Dance(path);
        sref = new Solution(path);
        bestSolution = new Solution(path);

        this.maxIteration = it;
        this.nbBee = nbb;
        this.flip = f;
        this.nbChance = ch;
    }

    public Algo(String path){
        this.path = path;
        danceList = new Dance(path);
        sref = new Solution(path);
        bestSolution = new Solution(path);
    }

    public void searchArea(){
        Solution s = new Solution(path);
        bee = new ArrayList<Solution>();
        int p=0;
        int ind;

        for (int i=0;i<nbBee;i++){
            p=0;

            s = new Solution(path);
            s.copy(sref);
            ind = flip*p+i+nbLocal;

            while (ind<75){

                if (s.solution[ind] == 0){
                    s.solution[ind] = 1;
                }
                else {
                    s.solution[ind] = 0;
                }
                p++;
                ind = flip*p+i+nbLocal;
            }
            bee.add(s);
        }

    }

    public void localSearch(){
        for (int i=0;i<nbBee;i++){
            bee.get(i).evaluation();
            //System.out.println(bee.get(i).score);
        }
    }

    public void addToDanceList(){
        for(int i=0;i<nbBee;i++){
            danceList.solution.add(bee.get(i));
        }
    }

    public String bso(){
        int it = 0;
        Solution pre = new Solution(path);

        String s="";

        sref.randFill();
        sref.evaluation();
        bestSolution = sref;
        nbLocal=0;
        nbChance = maxChance;
        pre = sref;
        while (it < maxIteration){

            Solution n = new Solution(path);
            n.copy(sref);
            n.evaluation();

            if (!contain(n)) tabooList.add(n);
            searchArea();
            localSearch();
            addToDanceList();

            Solution j  = danceList.getBestSolution();

            if (j.score > pre.score){
                pre = j;
                nbLocal = 0;
                sref = j;
                if (tabooList.contains(sref)){
                    System.out.println("rand fill");
                    sref = danceList.getBestDeversity();
                    if (tabooList.contains(sref)){
                        sref.randFill();
                    }
                }
                nbChance = maxChance;
            }
            else{
                pre = j;
                nbChance--;
                if (nbChance > 0){
                    nbLocal=nbLocal+nbBee;
                }
                else{
                    nbLocal = 0;
                    sref = danceList.getBestDeversity();
                    nbChance = maxChance;
                    if (tabooList.contains(sref)){
                        sref.randFill();
                        sref.evaluation();
                    }
                }
            }

            if (sref.score > bestSolution.score){
                bestSolution = new Solution(path);
                bestSolution.copy(sref);
                bestSolution.evaluation();

                s = "";
                for (int i=0;i<75;i++){
                    s = s +bestSolution.solution[i]+" ";
                }

            }

            System.out.println("score "+it+" : "+bestSolution.score+" "+nbChance+" "+tabooList.size()+" "+danceList.solution.size()+" "+nbLocal);
            for (int i=0;i<75;i++){
                System.out.print(sref.solution[i]+" ");
            }
            System.out.println();
            System.out.println("-----------------------------------------------------------");

            it++;
        }

        s = s + "\nbest score is " + bestSolution.score +" ---> "+ (bestSolution.score * 100 / 325);
        return s;
    }

    public Boolean contain(Solution s){
        boolean b=false;
        for (int i=0;i<tabooList.size();i++){
            Solution j = tabooList.get(i);

            int x=0;

            while(x<75 && s.solution[x] == j.solution[x]){
                x++;
            }

            if (x ==75){
                b = true;
                break;
            }
        }
        System.out.println(b);
        return b;
    }

}
