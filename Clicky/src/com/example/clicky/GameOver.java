package com.example.clicky;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class GameOver extends Activity {

	Button scoreDisplay,highScoreDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);

		Intent mIntent = getIntent();
		int score = mIntent.getIntExtra("score", 0);
		int highScore =mIntent.getIntExtra("highScore", 0);

		//displaying your score and high score
		scoreDisplay = (Button) findViewById(R.id.Score);
		highScoreDisplay = (Button) findViewById(R.id.highScore);

		scoreDisplay.setText("Score = "+score);
		highScoreDisplay.setText("High Score = "+highScore);
	}
	//if you click the try again button you are taken back to the main game

	public void onClick(View v)
	{
		startActivity( new Intent(this, GameScreen.class) );
	}

}
