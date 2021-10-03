import java.util.Arrays;
import java.util.Random;
public class Swarm {
    public int NbParticle  = 200;
    public Particle[] population = new Particle[NbParticle] ;
    public int NbIterations = 1000;
    public double w = 2*1/Math.abs(2-4.1-Math.sqrt(Math.pow(4.1,2)-4*4.1));
    public double c1 = w * 2.05;
    public double c2 = w * 2.05 ;
    public Best GlobalBest = new Best();
    public double split = 0.99 ;

    public Swarm(int np,int ni,double c1,double c2,double split){
        this.NbParticle = np;
        this.NbIterations = ni;
        this.c1 = c1 * w;
        this.c2 = c2 * w;
        this.split = split;
    }

    public void  CreateSwarm(Ensemble ensemble){
        for(int i = 0 ; i < NbParticle ; i++){
            population[i] = new Particle(ensemble) ;
            if (population[i].score > GlobalBest.BScore){
                GlobalBest.BPosition = population[i].position ;
                GlobalBest.BScore = population[i].score ;
            }

        }
    }
    public String SwarmOptimisation(Ensemble ensemble){
        String s= "";
        for(int i = 0 ; i < NbIterations ; i ++ ){
            for(int j = 0 ; j < NbParticle ; j ++ ){

                //   System.out.print("old is :"+population[j].score);

                population[j].UpdateVelocity(w ,c1 ,c2 ,GlobalBest);

                population[j].UpdatePosition(ensemble);

                if (population[j].score > GlobalBest.BScore){

                    s="";
                    for (int x = 0 ;x<75;x++){
                        s = s + " " +population[j].position[x].toString();
                    }
                    System.out.println(s);
                    GlobalBest.BPosition = population[j].position ;
                    GlobalBest.BScore = population[j].score ;
                }

                // System.out.println(" new is :"+population[j].score);

            }
            System.out.println("iteration numero  : " +i) ;
        }
        System.out.println();
        System.out.println("best score is " + GlobalBest.BScore +" ---> "+ (GlobalBest.BScore * 100 / 325) + "%") ;




        return "best score is " + GlobalBest.BScore +" ---> "+ (GlobalBest.BScore * 100 / 325) + "%\n"+s;
    }

}


