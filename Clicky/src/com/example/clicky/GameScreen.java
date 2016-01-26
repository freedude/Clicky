package com.example.clicky;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameScreen extends Activity implements OnClickListener {

	public static final String PREFERENCES_NAME = "DataStorage"; //for use in saving data
	public static final String HIGH_SCORE_KEY = "highScore"; //the key you will us for retrieving your high score

	SharedPreferences difficulty;
	SharedPreferences data;//declaring shared preferences
	public static String filename = "info";//naming the file in which your data will be stored

	//declare buttons
	Button label,label2,label3;
	Button a,b,c,d,e;
	//declare ints
	int score; //holds your score
	int highScore;
	int previous; //holds an int which represents the last color that was displayed
	int chances=3; //the amount of mistakes you can make before getting a game over
	int l; //decides which colour that label one tells you to pick
	int a1,b1,c1,d1,e1; //ints assigned random numbers to decide what colour the buttons will be

	public boolean hardMode ; //boolean determining difficulty level

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen); //set view to activity_game_screen
		
		Intent l1Intent = getIntent();
		int level = l1Intent.getIntExtra("hardModey", 1);

		if (level==1){
			hardMode=true;
		}
		
		Intent l2Intent = getIntent();
		int level2 =l2Intent.getIntExtra("hardModey", 2);

		if (level2==2){
			hardMode=false;
		}

		data = getSharedPreferences(filename, MODE_PRIVATE); //retrieving data from your saved files

		//labels to display info such as scores
		label = (Button) findViewById(R.id.buttonOne);
		label2 = (Button) findViewById(R.id.buttonTwo);
		label3 = (Button) findViewById(R.id.buttonThree);

		//clickable buttons whose colors change
		a = (Button) findViewById(R.id.button1);
		b = (Button) findViewById(R.id.button2);
		c = (Button) findViewById(R.id.button3);
		d = (Button) findViewById(R.id.button4);
		e = (Button) findViewById(R.id.button5);

		a.setBackgroundColor(Color.RED);
		b.setBackgroundColor(Color.BLUE); 
		c.setBackgroundColor(Color.GREEN);
		d.setBackgroundColor(Color.MAGENTA);
		e.setBackgroundColor(Color.CYAN);

		label.setText("Red");
		l=1;//defaulting the label one display to red

		a.setOnClickListener(this);
		b.setOnClickListener(this);
		c.setOnClickListener(this);
		d.setOnClickListener(this);
		e.setOnClickListener(this);

		highScore = data.getInt(HIGH_SCORE_KEY, 5);//retrieving the high score from shared preferences

		new CountDownTimer(60000, 1000) //starting a timer that ensures that every game lasts at most 90 seconds
		{

			public void onTick(long millisUntilFinished) 
			{
				label3.setText("" + (millisUntilFinished / 1000));//displays the remaining time
			}

			public void onFinish() 
			{
				gameOver();//opens the game over screen when time runs out
			}
		}.start();

	}

	public void gameOver() //Opens a new screen when the game is finished
	{
		Intent myIntent = new Intent(GameScreen.this, GameOver.class);
		myIntent.putExtra("score", score);//sends the players score and high score to the new screen to be displayed.
		myIntent.putExtra("highScore", highScore);
		startActivity(myIntent);
	}

	//Method that the buttons run when clicked
	//Changes the colors of the Buttons
	public void onClick(View v)  {


		if(v.getId()==R.id.button1) //calls the isCorrect method to see if the button clicked is the right button
		{
			isCorrect(a1,previous);
		}
		else if(v.getId()==R.id.button2)
		{
			isCorrect(b1,previous);
		}
		else if(v.getId()==R.id.button3)
		{
			isCorrect(c1,previous);
		}
		else if(v.getId()==R.id.button4)
		{
			isCorrect(d1,previous);
		}
		else if(v.getId()==R.id.button5)
		{
			isCorrect(e1,previous);
		}

		previous = l; //sets the current color displayed on the label to be the previous color
		l = (int) Math.floor(Math.random()*5)+1; //randomly picks a new color for the label

		//Pick five unique numbers
		int [] colours = {1,2,3,4,5};
		int [] hasBeenPicked = new int[colours.length];

		for(int i=0; i<5;i++)
		{
			int x = (int) Math.floor(Math.random()*5);
			while(colours[x] == 0)
			{
				x = (int) Math.floor(Math.random()*5);
			}
			hasBeenPicked[i]=colours[x];
			colours[x]=0;
		}

		//assign these numbers to the buttons, this ensures that each button
		//will have a unique color, i.e no two have the same color
		a1 = hasBeenPicked[0];
		b1 = hasBeenPicked[1];
		c1 = hasBeenPicked[2];
		d1 = hasBeenPicked[3];
		e1 = hasBeenPicked[4];


		if(l==1) //converts the numbers l is set to to be text.
		{
			label.setText("RED ");
		}
		else if(l==2)
		{
			label.setText("BLUE ");
		}
		else if(l==3)
		{
			label.setText("GREEN ");
		}
		else if(l==4)
		{
			label.setText("MAGENTA ");
		}
		else if(l==5)
		{
			label.setText("CYAN ");
		}

		//set the colors of the buttons 
		if(a1==1)
		{
			a.setBackgroundColor(Color.RED);
			if(hardMode==true)
			{
				label.setTextColor(Color.RED);
			}
		}
		else if(a1==2)
		{
			a.setBackgroundColor(Color.BLUE);
			if(hardMode==true)
			{
				label.setTextColor(Color.BLUE);
			}
		}
		else if(a1==3)
		{
			a.setBackgroundColor(Color.GREEN);
			if(hardMode==true)
			{
				label.setTextColor(Color.GREEN);
			}
		}
		else if(a1==4)
		{
			a.setBackgroundColor(Color.MAGENTA);
			if(hardMode==true)
			{
				label.setTextColor(Color.MAGENTA);
			}
		}
		else if(a1==5)
		{
			a.setBackgroundColor(Color.CYAN);
			if(hardMode==true)
			{
				label.setTextColor(Color.CYAN);
			}
		}


		if(b1==1)
		{
			b.setBackgroundColor(Color.RED);
		}
		else if(b1==2)
		{
			b.setBackgroundColor(Color.BLUE);
		}
		else if(b1==3)
		{
			b.setBackgroundColor(Color.GREEN);
		}
		else if(b1==4)
		{
			b.setBackgroundColor(Color.MAGENTA);
		}
		else if(b1==5)
		{
			b.setBackgroundColor(Color.CYAN);
		}	


		if(c1==1)
		{
			c.setBackgroundColor(Color.RED);
		}
		else if(c1==2)
		{
			c.setBackgroundColor(Color.BLUE);
		}
		else if(c1==3)
		{
			c.setBackgroundColor(Color.GREEN);
		}
		else if(c1==4)
		{
			c.setBackgroundColor(Color.MAGENTA);
		}
		else if(c1==5)
		{
			c.setBackgroundColor(Color.CYAN);
		}	


		if(d1==1)
		{
			d.setBackgroundColor(Color.RED);
		}
		else if(d1==2)
		{
			d.setBackgroundColor(Color.BLUE);
		}
		else if(d1==3)
		{
			d.setBackgroundColor(Color.GREEN);
		}
		else if(d1==4)
		{
			d.setBackgroundColor(Color.MAGENTA);
		}
		else if(d1==5)
		{
			d.setBackgroundColor(Color.CYAN);
		}	



		if(e1==1)
		{
			e.setBackgroundColor(Color.RED);

		}
		else if(e1==2)
		{
			e.setBackgroundColor(Color.BLUE);

		}
		else if(e1==3)
		{
			e.setBackgroundColor(Color.GREEN);

		}
		else if(e1==4)
		{
			e.setBackgroundColor(Color.MAGENTA);

		}
		else if(e1==5)
		{
			e.setBackgroundColor(Color.CYAN);

		}


		if(chances<0) //if you lose all your chances
		{
			chances = 3; //reset your chances for the next game
			SharedPreferences.Editor editor = data.edit();//commit your high score to shared preferences
			editor.putInt(HIGH_SCORE_KEY, highScore);
			editor.commit();

			gameOver();//open the game over activity
		}


	}


	//Methods to see which button is correct
	public void isCorrect(int x1, int previous)

	{//make it only change when you're correct

		if(previous == x1) //x1 is the button you clicked, if it holds the same value as previous you are right
		{
			//if you are right your score increases and label2 is updated
			score++;
			label2.setText("Score = " +score);

			if(score>highScore)//changes the high score if your score exceeds it
			{
				highScore = score;
			}
		}
		else //otherwise your chances decrease
		{

			chances --;

		}
	}

}
