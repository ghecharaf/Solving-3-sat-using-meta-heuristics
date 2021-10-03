import java.util.ArrayList;

public class Dance {
    ArrayList<Solution> solution = new ArrayList<Solution>();

    public String path;

    public Dance(String path){
        this.path = path;
    }

    public Solution getBestSolution(){
        int best=0;
        for (int i=0;i<solution.size();i++){
            if (solution.get(i).score > solution.get(best).score){
                best = i;
            }
        }
        return solution.get(best);
    }

    public Solution getBestDeversity(){
        int best=0;
        Algo a = new Algo(path);
        boolean b = false;
        for (int i=0;i<solution.size();i++){

            if (getDeversity(solution.get(i)) > getDeversity(solution.get(best)) && !a.tabooList.contains(solution.get(i))){
                best = i;
                b = true;
            }
        }
        if (b == false){
            Solution n = new Solution(path);
            n.randFill();
            n.evaluation();
            return n;
        }
        return solution.get(best);
    }

    public Integer getDistance(Solution x,Solution y){
        int d=0;
        for (int i=0;i<75;i++){
            if (x.solution[i] != y.solution[i]){
                d++;
            }
        }
        return d;
    }

    public Integer getDeversity(Solution s){
        int d=75,x;
        int ind ;

        for (int i=0;i<solution.size();i++){
            x=getDistance(s,solution.get(i));
            if (x<d && x != 0){
                d=x;
            }
        }
        return d;
    }

    public void print(){
        for (int i=0;i<solution.size();i++){
            for (int j=0;j<75;j++){
                System.out.print(solution.get(i).solution[j]+"  ");
            }
            System.out.println();
        }

    }
}
