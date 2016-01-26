package com.example.clicky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class OptionsScreen extends Activity {

	int hardModey;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options_screen);
		
	}
	
	public void hardModeOn(View view){
		
		hardModey=1;
		Intent hardIntentOn = new Intent(this,GameScreen.class);
		hardIntentOn.putExtra("hardModey", 1);
		startActivity(hardIntentOn); //when expert is clicked this turns expert mode on	
	}
	
	public void hardModeOff(View view){
		hardModey = 2;
		Intent hardIntentOff = new Intent(this,GameScreen.class);
		hardIntentOff.putExtra("hardModey",2);
		startActivity(hardIntentOff); //when normal is clicked this turns normal mode on
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_screen, menu);
		return true;
	}

}
