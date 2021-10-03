import java.util.Arrays;
import java.util.Random;

public class Solution {
    public Integer[] solution = new Integer[75];
    public Integer score = 0;
    public String path;
    private Ensemble ensemble;
    public Solution(String path){
        this.path = path;
        this.ensemble = new Ensemble(path) ;
    }



    public void randFill() {
        Arrays.fill(this.solution , 0);
        Random r = new Random();
        Random r2 = new Random() ;
        int n = r.nextInt(75);
        for (int i = 0; i < n; i++) {

            this.solution[r2.nextInt(75)] = 1 ;
        }

    }

    public void evaluation(){


        int sc = 0;
        for (int j=0;j<325;j++){
            int b = 0;
            if(ensemble.ensemble[j].l1 > 0) b = this.solution[ensemble.ensemble[j].l1-1];
            else{
                int t = this.solution[Math.abs(ensemble.ensemble[j].l1)-1];
                if (t == 0) b = 1;
                else if (t == 1) b = 0;
            }

            if (b == 1) sc++;
            else{
                if(ensemble.ensemble[j].l2 > 0) b = this.solution[ensemble.ensemble[j].l2-1];
                else{
                    int t = this.solution[Math.abs(ensemble.ensemble[j].l2)-1];

                    if (t == 0) b = 1;
                    else if (t == 1) b = 0;
                }

                if (b == 1) sc++;
                else{
                    if(ensemble.ensemble[j].l3 > 0) b = this.solution[ensemble.ensemble[j].l3-1];
                    else{
                        int t = this.solution[Math.abs(ensemble.ensemble[j].l3)-1];

                        if (t == 0) sc++;
                    }
                }
            }
        }
        this.score = sc;

    }

    public void copy(Solution s){
        for (int i=0;i<75;i++){
            solution[i] = s.solution[i];
        }
        score = s.score;
    }

    public void print(){
        for (int j=0;j<75;j++){
            System.out.print(solution[j]+" ");
        }
        System.out.println();
    }

}
