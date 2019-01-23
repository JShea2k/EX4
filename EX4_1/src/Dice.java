import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Dice implements Runnable{
    private JLabel _label;
    Random rn = new Random();
    ImageIcon face1;
    ImageIcon face2;
    ImageIcon face3;
    ImageIcon face4;
    ImageIcon face5;
    ImageIcon face6;

    public Dice(JLabel label){
        this._label = label;
        diceFaces();
        _label.setIcon(randomSide());
    }

    @Override
    public void run() {
        for(int i = 0; i < 35; i++){
            _label.setIcon(randomSide());
            try{
                Thread.sleep(75);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private ImageIcon randomSide(){
        int randomInt = rn.nextInt(6) + 1;
        //System.out.println(randomInt);
        if(randomInt == 1){
            _label.setName("1");
            return face1;
        }else if(randomInt == 2){
            _label.setName("2");
            return face2;
        }else if(randomInt == 3){
            _label.setName("3");
            return face3;
        }else if(randomInt == 4){
            _label.setName("4");
            return face4;
        }else if(randomInt == 5){
            _label.setName("5");
            return face5;
        }else if(randomInt == 6){
            _label.setName("6");
            return face6;
        }else{
            return null;
        }
    }

    public int getFaceValue(){
        return Integer.parseInt(_label.getName());
    }

    // Gets the images for the dice
    // Currently not working, not sure why
    public void diceFaces(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int ratio = 100;

        // Gets the dice 1 image
        URL dice1URL = getClass().getResource("/resources/Dice1.png");
        Image dice1img = toolkit.getImage(dice1URL);
        dice1img = dice1img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        // Gets the dice 2 image
        URL dice2URL = getClass().getResource("/resources/Dice2.png");
        Image dice2img = toolkit.getImage(dice2URL);
        dice2img = dice2img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        // Gets the dice 3 image
        URL dice3URL = getClass().getResource("/resources/Dice3.png");
        Image dice3img = toolkit.getImage(dice3URL);
        dice3img = dice3img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        // Gets the dice 4 image
        URL dice4URL = getClass().getResource("/resources/Dice4.png");
        Image dice4img = toolkit.getImage(dice4URL);
        dice4img = dice4img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        // Gets the dice 5 image
        URL dice5URL = getClass().getResource("/resources/Dice5.png");
        Image dice5img = toolkit.getImage(dice5URL);
        dice5img = dice5img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        // Gets the dice 6 image
        URL dice6URL = getClass().getResource("/resources/Dice6.png");
        Image dice6img = toolkit.getImage(dice6URL);
        dice6img = dice6img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        //Sets all of the faces with their respective image
        face1 = new ImageIcon(dice1img);
        face2 = new ImageIcon(dice2img);
        face3 = new ImageIcon(dice3img);
        face4 = new ImageIcon(dice4img);
        face5 = new ImageIcon(dice5img);
        face6 = new ImageIcon(dice6img);
    }
}
