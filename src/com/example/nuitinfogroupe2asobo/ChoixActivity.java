package com.example.nuitinfogroupe2asobo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChoixActivity extends Activity 
{
	protected static final int RESULT_SPEECH = 1;

	private ImageButton btnSpeak;

	private String arg0;
	
	private static final String url = "http://nuitinfo2012.iut-valence.fr/eq2/asobo/script.php?";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choix);
		Intent intent = getIntent();
		this.arg0 = intent.getStringExtra("param");
		
		btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

		btnSpeak.setOnClickListener(new View.OnClickListener() 
		{

			@Override
			public void onClick(View v)
			{

				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "fr-FR");

				try 
				{
					startActivityForResult(intent, RESULT_SPEECH);
				} catch (ActivityNotFoundException a) 
				{
					Toast t = Toast.makeText(getApplicationContext(),
							"Ops! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choix, menu);
		return true;
	}

	public void choixSpecialitees(View view)
	{
		Intent intent = new Intent(this, RegionActivity.class);
		intent.putExtra("param0", this.arg0);
		intent.putExtra("param1", "specialitees");
		startActivity(intent);
	}

	public void choixIngredients(View view)
	{
		Intent intent = new Intent(this, IngredientsActivity.class);
		intent.putExtra("param0", this.arg0);
		intent.putExtra("param1", "ingredients");
		startActivity(intent);
	}

	public void choixRandom(View view)
	{
		Intent intent = new Intent(this, WebViewActivity.class);
		intent.putExtra("url", this.url + "type=" + this.arg0 + "&choix=random");
		startActivity(intent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode)
		{
		case RESULT_SPEECH: 
		{
			if (resultCode == RESULT_OK && null != data)
			{

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				Toast.makeText(getApplicationContext(), text.get(0), Toast.LENGTH_SHORT).show();
				if(text.get(0).equals("ingredient"))
				{
					Toast.makeText(getApplicationContext(), text.get(0) +  "-" + text.get(1)+ "-" + text.get(2), Toast.LENGTH_SHORT).show();
					this.choixIngredients(null);
				}
				if(text.get(0).equals("specialité"))
				{
					Toast.makeText(getApplicationContext(), text.get(0), Toast.LENGTH_SHORT).show();
					this.choixSpecialitees(null);
				}
				if(text.get(0).equals("random") || text.get(0).equals("aléatoire") ||text.get(0).equals("hasard") ||text.get(0).equals("hazard"))
				{
					Toast.makeText(getApplicationContext(), text.get(0), Toast.LENGTH_SHORT).show();
					this.choixRandom(null);
				}
			}
			break;
		}

		}
	}

}
