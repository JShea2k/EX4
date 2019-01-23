import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread;

public class Yahtzee implements ActionListener {
    private JPanel mainPanel;
    private JLabel dice1;
    private JLabel dice2;
    private JLabel dice3;
    private JLabel dice4;
    private JLabel dice5;
    private JButton rollBtn;
    private JTextArea scoreText;
    JFrame frame;
    int diceAmount = 5;
    Thread[] diceThreads;
    Dice[] diceArray;
    Thread testThread;
    boolean run = true;

    public Yahtzee() {
        // Creates the frame and the basic contents
        frame = new JFrame("Yahtzee");
        frame.setSize(new Dimension(600, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        scoreText.setPreferredSize(new Dimension(100, 100));
        scoreText.setText("Score: ");
        scoreText.setEditable(false);
        frame.add(mainPanel);

        // Calls the function to set up the dice
        diceSetup();

        // Gives the roll button a listener
        rollBtn.addActionListener(this);
    }

    private void diceSetup(){
        testThread = new Thread(this::outputScore);

        // Puts the dice in their own array
        diceArray = new Dice[diceAmount];
        diceArray[0] = new Dice(dice1);
        diceArray[1] = new Dice(dice2);
        diceArray[2] = new Dice(dice3);
        diceArray[3] = new Dice(dice4);
        diceArray[4] = new Dice(dice5);

        // Creates threads for all of the dice
        diceThreads = new Thread[diceAmount];
        for(int i = 0; i < diceAmount; i++){
            diceThreads[i] = new Thread(diceArray[i]);
        }
    }

    public void outputScore(){
        try {
            Thread.sleep(20);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        boolean allThreadsDone;
        do{
            try {
                Thread.sleep(50);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            allThreadsDone = true;
            for(int i = 0; i < diceAmount; i++){
                if(diceThreads[i].getState() != Thread.State.NEW && diceThreads[i].getState() != Thread.State.TERMINATED){
                    allThreadsDone = false;
                }

                // Calculates the score of the final dice roll
                int score = 0;
                for(int j = 0; j < diceAmount; j++){
                    score += diceArray[j].getFaceValue();
                }
                scoreText.setText("Score: " + score);
            }
        }while(!allThreadsDone);
        run = true;
    }

    // Makes the separate dice threads start
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == rollBtn && run == true){
            run = false;
            scoreText.setText("Score: ");
            for(int i = 0; i < diceAmount; i++){
                try {
                    diceSetup();
                    diceThreads[i].start();
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            testThread.start();
        }
    }

    public static void main(String[] args) {
        Yahtzee y = new Yahtzee();
    }
}
