import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Collections;
public class Population {
    // -------------------------les parametres --------------------------
    public int numIndiv = 200 ;
    public Chromosome[] chr;
    public double tCross ;
    public double tMut ;
    public double tRand ;
    // ------------------------------------------------------------------

    static public Comparator<Chromosome> scr = scr = new Comparator<Chromosome>(){
        public int compare(Chromosome c1 , Chromosome c2){

            return Integer.compare(c1.score , c2.score);

        }
    };

    public Population(Ensemble ensemble,int n,double cross , double mut , double rand){
        this.numIndiv = n;
        this.tCross = cross;
        this.tMut = mut;
        this.tRand = rand;
        this.chr = new Chromosome[numIndiv*2];
        this.create(ensemble);
    }

    // Cree une population de n chromosomes
    public void create(Ensemble ensemble){
        for (int i=0 ;i<numIndiv;i++){
            chr[i] = new Chromosome();
            chr[i].RandFill();
            chr[i].evaluation(ensemble);
        }
    }



    public void createNew(Ensemble ensemble){
        // cross over
        Random cross = new Random() ;
        Random gene = new Random() ;
        int number = (int) (numIndiv * tCross);
        int size ;
        int index1 ;
        int index2 ;
        Chromosome c1 ;
        Chromosome c2 ;
        int location ;
        for(int i = numIndiv ; i < numIndiv+number ; i ++){
            index1 = cross.nextInt(20) ;
            index2 = cross.nextInt(20) ;
            c1 = chr[index1] ;
            c2 = chr[index2] ;
            size = gene.nextInt(75 ) ;
            location = gene.nextInt(75 - size) ;
            chr[i] = c2 ;
            for(int j = location ; j < location+size ; j++){
                chr[i].val[j] = c1.val[j] ;
            }
            // fin de cross over

        }
        // mutation
        int nbmut = (int) (numIndiv * tMut);
        int mutsize ;
        Random mut = new Random() ;
        Random mut2 = new Random() ;
        Random mut3 = new Random() ;
        for (int k = numIndiv+number ; k < numIndiv+number+nbmut ; k++ ){
            mutsize = mut3.nextInt(75) ;
            chr[k] = chr[mut.nextInt(numIndiv)] ;
            
            int loc =mut2.nextInt(75 - mutsize) ;
            for(int t = loc ; t< loc + mutsize ; t++ ){
                chr[k].val[t] = mut2.nextInt(2) ;
            }
        }
        // fin mutation
        // random
        int nbrand = (int) (numIndiv * tRand);
        for (int z = numIndiv + nbmut + number ; z < numIndiv + number + nbmut + nbrand ; z++){
            chr[z] = new Chromosome();
            chr[z].RandFill();
            chr[z].evaluation(ensemble);
        }

    }
    public void sort(){
        Arrays.sort(chr , scr);
        Collections.reverse(Arrays.asList(chr));
        System.out.println("meilleur score est  :"+chr[0].score);
    }
}
