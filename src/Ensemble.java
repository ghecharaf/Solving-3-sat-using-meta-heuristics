import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ensemble {
    public  Clause[] ensemble = new Clause[325];

    String file ;

    Ensemble(String file){
        this.file = file;
        CreateSet();
    }
    // Lire le fichier
    public void CreateSet(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            for(int i = 0 ; i < 8 ; i ++){
                line =  reader.readLine() ;
            }
            for(int i = 0 ; i < 325 ; i ++){
                line =  reader.readLine() ;
                String[] literals   = line.split(" ");

                ensemble[i] =  new Clause(Integer.parseInt(literals[0]) , Integer.parseInt(literals[1]) , Integer.parseInt(literals[2])) ;

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Afficher le fichier
    public void print(){
        for (int i = 0; i < 325; i++) {
            System.out.println(this.ensemble[i].l1 +" "+ this.ensemble[i].l2 +" "+ this.ensemble[i].l3);
        }
    }

}
