import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Scanner;

public class Main {

    private JButton psoButton;
    private JButton bsoButton;
    public JPanel mainpanel;
    private JTextField TextFieldpath;

    public int choix = 0;
    public String file;
    private JButton algorithmeGenetiqueButton;
    private JPanel firstpanel;
    private JTextArea textArea1;
    private JPanel resultatspanel;
    private JTextPane textPane1;
    private JTextField textField1;
    private JPanel secondpanel;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton lancerButton;
    private JLabel cross;
    private JLabel mut;
    private JLabel rand;
    private JLabel c1;
    private JLabel c2;
    private JLabel split;
    private JLabel ind;
    private JLabel NBbee;
    private JLabel fli;
    private JLabel chance;

    public Main() {
        algorithmeGenetiqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TextFieldpath.getText().isEmpty() != true){
                    choix = 1;
                   /* psoButton.setEnabled(false);
                    bsoButton.setEnabled(false);*/


                    firstpanel.setVisible(false);

                    cross.setVisible(true);
                    mut.setVisible(true);
                    rand.setVisible(true);
                    secondpanel.setVisible(true);
/*                    mainpanel.repaint();
                    mainpanel.revalidate();*/


                }
                else{
                    JOptionPane.showMessageDialog(null,"benchmark");
                }
            }
        });
        psoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TextFieldpath.getText().isEmpty() != true){
                    choix = 2;
                    /*algorithmeGenetiqueButton.setEnabled(false);
                    bsoButton.setEnabled(false);*/
                    firstpanel.setVisible(false);

                    c1.setVisible(true);
                    c2.setVisible(true);
                    split.setVisible(true);
                    secondpanel.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"benchmark");
                }
            }
        });
        bsoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TextFieldpath.getText().isEmpty() != true){
                    choix = 3;
                    /*algorithmeGenetiqueButton.setEnabled(false);
                    psoButton.setEnabled(false);*/
                    file = TextFieldpath.getText();

                    firstpanel.setVisible(false);

                    ind.setVisible(false);
                    NBbee.setVisible(true);
                    fli.setVisible(true);
                    chance.setVisible(true);
                    textField5.setVisible(false);

                    secondpanel.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"benchmark");
                }
            }
        });

        lancerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                secondpanel.setVisible(false);
                resultatspanel.setVisible(true);
                switch (choix){


                    case 1:{
                        file = TextFieldpath.getText();

                        Ensemble x = new Ensemble(file);

                        Population population = new Population(x,Integer.parseInt(textField2.getText()),Double.parseDouble(textField3.getText()),Double.parseDouble(textField4.getText()),Double.parseDouble(textField5.getText()));
                        int nbGeneration = Integer.parseInt(textField1.getText());

                        secondpanel.setVisible(false);
                        resultatspanel.setVisible(true);
                        int i = 0;
                        while (population.chr[0].score < 325) {
                            population.createNew(x);
                            textPane1.setText("la generation nb :" + i + " : ");
                            System.out.print("la generation nb :" + i + " : ");
                            population.sort();
                            i++;
                            if (i == nbGeneration) {
                                break;
                            }

                        }

                        String s= "";
                        for (int j = 0 ;j<75;j++){
                            s = s + " " + population.chr[0].val[j].toString();
                        }

                        System.out.println(" le score final est : " + population.chr[0].score);
                        textPane1.setText(" le score final est : " + population.chr[0].score+"\n"+s);
                        break;
                    }

                    case 2:{
                        file = TextFieldpath.getText();

                        Ensemble x = new Ensemble(file);

                        long start = System.nanoTime();

                        Swarm swarm = new Swarm(Integer.parseInt(textField2.getText()),Integer.parseInt(textField1.getText()),Double.parseDouble(textField3.getText()),Double.parseDouble(textField4.getText()),Double.parseDouble(textField5.getText()));
                        swarm.CreateSwarm(x);
                        String s= "";
                        s = swarm.SwarmOptimisation(x);

                        long end = System.nanoTime();

                        System.out.println("temps d'exécution : " + (end - start) / 1000000000 + " s");

                        textPane1.setText(s);



                        break;
                    }

                    case 3:{
                        Ensemble x = new Ensemble(file);

                        Algo bso = new Algo(file,Integer.parseInt(textField1.getText()),Integer.parseInt(textField2.getText()),Integer.parseInt(textField3.getText()),Integer.parseInt(textField4.getText()));
                        String s = bso.bso();
                        textPane1.setText(s);

                        break;
                    }

                }


            }
        });
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("App");
        frame.setContentPane(new Main().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /*
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Max générations : ");
        int generation = Integer.parseInt(sc.nextLine());
        System.out.println("emplacement de benchmark : ");
        String file = sc.nextLine();

        System.out.println(x.file);
        */


    }
}
