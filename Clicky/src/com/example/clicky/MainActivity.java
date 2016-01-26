package com.example.clicky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); //set view to activity_main.xml

	}

	public void SendToOptions(View view){
		Intent OptionsIntent = new Intent(this, OptionsScreen.class);
		startActivity(OptionsIntent); //Send intent from this class to the Options Screen 

	}
	public void SendToGame(View view){
		Intent intent = new Intent(this, GameScreen.class);
		startActivity(intent);		//Send intent from this class to the GameScreen
	}

	public void SendToInstructions(View view){
		Intent InstructionsIntent = new Intent(this, Instructions.class);
		startActivity(InstructionsIntent); //Send intent from this class to the Instruction Screen
	}
}