import java.util.Arrays;
import java.util.Random;

public class Chromosome {
    public Integer[] val = new Integer[75];
    public Integer score = 0;


    // Remplire aleatoirement les valeurs d un chromosome
    public void RandFill() {
        Arrays.fill(this.val , 0);
        Random r = new Random();
        Random r2 = new Random() ;
        int n = r.nextInt(75);
        for (int i = 0; i < n; i++) {

            this.val[r2.nextInt(75)] = 1 ;
        }

    }

    // Afficher les valeurs d un chromosome
    public void print(){
        for (int i = 0; i < 75; i++) {
            System.out.println(this.val[i]);
        }
    }

    public void evaluation(Ensemble ensemble){

            int sc = 0;
            for (int j=0;j<325;j++){
                int b = 0;

                if(ensemble.ensemble[j].l1 > 0) b = val[ensemble.ensemble[j].l1-1];
                else{
                    int t = val[Math.abs(ensemble.ensemble[j].l1)-1];
                    if (t == 0) b = 1;
                    else if (t == 1) b = 0;
                }

                if (b == 1) sc++;
                else{
                    if(ensemble.ensemble[j].l2 > 0) b = val[ensemble.ensemble[j].l2-1];
                    else{
                        int t = val[Math.abs(ensemble.ensemble[j].l2)-1];

                        if (t == 0) b = 1;
                        else if (t == 1) b = 0;
                    }

                    if (b == 1) sc++;
                    else{
                        if(ensemble.ensemble[j].l3 > 0) b = val[ensemble.ensemble[j].l3-1];
                        else{
                            int t = val[Math.abs(ensemble.ensemble[j].l3)-1];

                            if (t == 0) sc++;
                        }
                    }
                }
            }
            this.score = sc;

    }


}
