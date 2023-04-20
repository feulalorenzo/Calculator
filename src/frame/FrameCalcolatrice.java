package frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameCalcolatrice extends JFrame{
    /** Inizializzazione components e containers */
    private JTextArea risultato;
    private JFrame frame;
    private JButton del, add, sub, mul, div, equal, zero, one, two, three, four, five, six, seven, eight, nine;

    public FrameCalcolatrice(){
        
        //Estetica frame
        frame = new JFrame("Calcolatrice");
        frame.setLocation(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,600);
        frame.setLayout(new GridLayout());

        /** Inizializzazione pulsanti*/
        risultato = new JTextArea();
        zero = new JButton("0");
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        equal = new JButton("=");
        del = new JButton("del");

        /** Effetti pressione pulsanti */
        
        zero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("0");
            }
        });
        one.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("1");
            }
        }); 
        two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("2");
            }
        }); 
        three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("3");
            }
        }); 
        four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("4");
            }
        }); 
        five.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("5");
            }
        }); 
        six.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("6");
            }
        }); 
        seven.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("7");
            }
        }); 
        eight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("8");
            }
        }); 
        nine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append("9");
            }
        });
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append(" + ");
            }
        }); 
        sub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append(" - ");
            }
        });
        mul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append(" * ");
            }
        }); 
        div.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                risultato.append(" / ");
            }
        });
        equal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String[] valori = new String[3];
                boolean continua = true;
                //La variabile cont salva la posizione dell'operatore, in modo da sapere che operazione si sta svolgendo dopo il ciclo di lettura
                int cont = 0, posValori = 0;
                String tmp = risultato.getText();

                //Ciclo di lettura: Scorre la stringa inserita nella JText Area per individuare i valori
                for(int i = 0; i < tmp.length() && continua; i++){

                    //Se il carattere è un numero, concatena il numero e li inserisce nei valori da salvare
                    if( tmp.charAt(i) != '+' && tmp.charAt(i) != '-' && tmp.charAt(i) != '*' && tmp.charAt(i) != '/' && tmp.charAt(i) != '='){
                        valori[posValori] = valori[posValori] + tmp.charAt(i);
                    }

                    //Se il carattere è un operatore, salva la posizione dell'operatore in cont e aumenta la posizione nell'array valori
                    if( tmp.charAt(i) == '+' || tmp.charAt(i) == '-' || tmp.charAt(i) == '*' || tmp.charAt(i) == '/'){
                        posValori++;
                        cont = i;
                    }

                    //Se il carattere è = allora esce dal ciclo di lettura
                    if( tmp.charAt(i) == '=')
                        continua = false;
                }
                
            }
        });


        //Aggiunta pulsanti sul frame

        frame.add(risultato);
        frame.add(zero);
        frame.add(one);
        frame.add(two);
        frame.add(three);
        frame.add(four);
        frame.add(five);
        frame.add(six);
        frame.add(seven);
        frame.add(eight);
        frame.add(nine);
        frame.add(add);
        frame.add(sub);
        frame.add(mul);
        frame.add(div);
        frame.add(equal);
        frame.add(del);

        //Finestra visibile dopo la fine dell'inizializzazione
        frame.setVisible(true);
        
    }

}


