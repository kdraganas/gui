import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RPSLS extends Frame/* implements ActionListener*/ {
	String mem[] = {"ROCK","PAPER","SCISSORS","LIZARD","SPOCK"};
	private Label label;
	private Label label1;
	private Label pscore;
	private Label cscore;
	private CheckboxGroup cbg;
	private TextArea txt;
	private Button btn;
	private Button bttn;
	private TextField scorep;
	private TextField scorec;
	private Checkbox cb1;
	private Checkbox cb2;
	private Checkbox cb3;
	private Checkbox cb4;
	private Checkbox cb5;
	private Dialog dialog;
	int playerscore, comscore;
	int player, rand, win;

	public RPSLS(){
	setLayout(new FlowLayout());

	label = new Label("Your Choice.");

	pscore = new Label("Player's Score:");
	scorep = new TextField(5);
	scorep.setEditable(false);

	cscore = new Label("Computer's Score:");
	scorec = new TextField(5);
	scorec.setEditable(false);

	cbg = new CheckboxGroup();
	txt = new TextArea();
	txt.setText("Results: ");

	btn = new Button("RockPaperScissorsLizardSpock!");
	bttn = new Button("OK");
	bttn.setSize(100,100);
	bttn.setVisible(true);

	//if the btn rockpaperscissorlizardspock is clicked
	btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
        	int check = checkCBox();
        	if(check == 0){
        		txt.setText("Results: \nPlayer chose "+mem[player]+"\nComputer chose "+mem[rand]+"\n\nYOU LOSE THIS ROUND!");
        		comscore+=1;
        		scorec.setText(comscore+"");
        	} else if (check == 1){
        		txt.setText("Results: \nPlayer chose "+mem[player]+"\nComputer chose "+mem[rand]+"\n\nYOU WON THIS ROUND!");
        		playerscore+=1;
        		scorep.setText(playerscore+"");
        	} else{
        		txt.setText("Results: \nPlayer chose "+mem[player]+"\nComputer chose "+mem[rand]+"\n\nIT'S A TIE!");
        	}
        	//prompting of the winner
        	if(playerscore==5 || comscore==5){
        		if(playerscore==5){
        			label1 = new Label("PLAYER WINS");
        		}
        		else{
        			label1 = new Label("COMPUTER WINS");
        		}
        		dialog.setLayout(new FlowLayout());
        		dialog.add(bttn);
        		label1.setVisible(true);
        		dialog.add(label1);
        		dialog.setSize(150,100);
        		dialog.setVisible(true);
        	}
        }
	});

	//after prompting the winner, the game will restart
	bttn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
        	playerscore=0;
        	comscore=0;
        	label1.setText("");
        	scorec.setText(comscore+"");
        	scorep.setText(playerscore+"");
        	dialog.dispose();
        }
	});

	cb1 = new Checkbox("Rock",cbg,true);
    cb2 = new Checkbox("Paper",cbg,false);
    cb3 = new Checkbox("Scissors",cbg,false);
    cb4 = new Checkbox("Lizard",cbg,false);
	cb5 = new Checkbox("Spock",cbg,false); 

	add(label);
	add(cb1);
	add(cb2);
	add(cb3);
	add(cb4);
	add(cb5);
	add(txt);
	add(btn);
	add(pscore);
	add(scorep);
	add(cscore);
	add(scorec);

	dialog = new Dialog(this, "GAME OVER",Dialog.ModalityType.DOCUMENT_MODAL);

    setSize(600, 300);
	setVisible(true);

	//closing window
	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
	});

	//closing the dialog
	dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
	});
	}

	//checking the box
	public int checkCBox(){
		int res;
		rand = randomizer(0,5);
		if(cbg.getSelectedCheckbox().equals(cb1)){
			player = 0;
		} else if (cbg.getSelectedCheckbox().equals(cb2)){
			player = 1;
		} else if (cbg.getSelectedCheckbox().equals(cb3)){
			player = 2;
		} else if (cbg.getSelectedCheckbox().equals(cb4)){
			player = 3;
		} else if (cbg.getSelectedCheckbox().equals(cb5)){
			player = 4;
		}
		res = result(player,rand);
		return res;
	}

	//checking of winner
	public int result(int player, int rand){
		int resu;
		if(player==0 && rand == 2 || player==0 && rand==3){
			resu = 1;
		} else if(player==1 && rand == 0 || player==1 && rand==4){
			resu = 1;
		} else if(player==2 && rand == 3 || player==2 && rand==1){
			resu = 1;
		} else if(player==3 && rand == 1 || player==3 && rand==4){
			resu = 1;
		} else if(player==4 && rand == 0 || player==4 && rand==2){
			resu = 1;
		} else if (player==0 && rand==0){
			resu = 2;
		} else if (player==1 && rand==1){
			resu = 2;
		} else if (player==2 && rand==2){
			resu = 2;
		} else if (player==3 && rand==3){
			resu = 2;
		} else if (player==4 && rand==4){
			resu = 2;
		} else {
			resu = 0;
		}
		return resu;
	}

	//random number generator
	public int randomizer(int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt(max - min) + min;
		return randomNum;
	} 

	public static void main(String[] args) {
		new RPSLS();
	}
}