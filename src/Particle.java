import java.util.Arrays;
import java.util.Random;
public class Particle {
    public Integer[] position = new Integer[75];
    public double [] velocity  = new double[75];
    public Best Pbest   = new Best();
    public Integer score = 0;

    public void RandFill() {
        Arrays.fill(this.position , 0);
        Random r = new Random();
        Random r2 = new Random() ;
        int n = r.nextInt(75);
        for (int i = 0; i < n; i++) {

            this.position[r2.nextInt(75)] = 1 ;
        }

    }
    public void evaluation(Ensemble ensemble){

        int sc = 0;
        for (int j=0;j<325;j++){
            int b = 0;

            if(ensemble.ensemble[j].l1 > 0) b = this.position[ensemble.ensemble[j].l1-1];
            else{
                int t = this.position[Math.abs(ensemble.ensemble[j].l1)-1];
                if (t == 0) b = 1;
                else if (t == 1) b = 0;
            }

            if (b == 1) sc++;
            else{
                if(ensemble.ensemble[j].l2 > 0) b = this.position[ensemble.ensemble[j].l2-1];
                else{
                    int t = this.position[Math.abs(ensemble.ensemble[j].l2)-1];

                    if (t == 0) b = 1;
                    else if (t == 1) b = 0;
                }

                if (b == 1) sc++;
                else{
                    if(ensemble.ensemble[j].l3 > 0) b = this.position[ensemble.ensemble[j].l3-1];
                    else{
                        int t = this.position[Math.abs(ensemble.ensemble[j].l3)-1];

                        if (t == 0) sc++;
                    }
                }
            }
        }
        this.score = sc;

    }
    public Particle(Ensemble ensemble){
        RandFill();
        this.Pbest.BPosition = this.position ;
        // init velocity
        for(int i = 0 ; i <75 ; i ++) {
            Random r = new Random();
            this.velocity[i] = r.nextFloat();

        }
        // calcule score
        evaluation(ensemble);
        this.Pbest.BScore = this.score ;
    }
    public void UpdateVelocity(double w , double c1 , double c2 , Best GlobalBest ){
        Random rand  = new Random();
        double r1 = rand.nextFloat();
        double r2 = rand.nextFloat();

        for(int i = 0 ; i < 75 ; i++){
            this.velocity[i] = w*this.velocity[i]+c1*r1*(this.Pbest.BPosition[i]-this.position[i] )+c2*r2*(GlobalBest.BPosition[i]- this.position[i]) ;
            //this.velocity[i] = (1/( 1 + Math.pow(Math.E,(-1*this.velocity[i]))));

        }
    }
    public void UpdatePosition( Ensemble ensemble ){
        Random r = new Random();
        double split = r.nextDouble();
        for(int i = 0 ; i < 75 ; i ++){

            if(this.velocity[i] < split) this.position[i] = 0 ;
            else this.position[i] = 1 ;
        }
        evaluation(ensemble);
        if(this.Pbest.BScore < this.score ){
            this.Pbest.BPosition = this.position ;
            this.Pbest.BScore = this.score ;
        }
    }

}
