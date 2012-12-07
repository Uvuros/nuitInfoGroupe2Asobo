package com.example.nuitinfogroupe2asobo;

//import net.viralpatel.android.speechtotextdemo.R;
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

public class MenuAccueil extends Activity {
	
	protected static final int RESULT_SPEECH = 1;

	private ImageButton btnSpeak;
	
	private static final String url = "http://nuitinfo2012.iut-valence.fr/eq2/asobo/script.php?";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_accueil);

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
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void choixEntrees(View view)
	{
		Intent intent = new Intent(this, ChoixActivity.class);
		intent.putExtra("param", "entrée");
		startActivity(intent);
	}
	
	public void choixPlats(View view)
	{
		Intent intent = new Intent(this, ChoixActivity.class);
		intent.putExtra("param", "plat+principal");
		startActivity(intent);
	}
	
	public void choixDesserts(View view)
	{
		Intent intent = new Intent(this, ChoixActivity.class);
		intent.putExtra("param", "dessert");
		startActivity(intent);
	}
	
	public void choixMenu(View view)
	{
		Intent intent = new Intent(this, WebViewActivity.class);
		intent.putExtra("url" , url + "type=menus");
		startActivity(intent);
	}
	
	public void choixLog(View view)
	{
		Toast.makeText(getApplicationContext(), "À faire !", Toast.LENGTH_SHORT).show();
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
				if(text.get(0).equals("entrée"))
				{
					Toast.makeText(getApplicationContext(), text.get(0) +  "-" + text.get(1)+ "-" + text.get(2), Toast.LENGTH_SHORT).show();
					this.choixEntrees(null);
				}
				if(text.get(0).equals("menu"))
				{
					Toast.makeText(getApplicationContext(), text.get(0), Toast.LENGTH_SHORT).show();
					this.choixMenu(null);
				}
				if(text.get(0).equals("plat"))
				{
					Toast.makeText(getApplicationContext(), text.get(0), Toast.LENGTH_SHORT).show();
					this.choixPlats(null);
				}
				if(text.get(0).equals("dessert"))
				{
					Toast.makeText(getApplicationContext(), text.get(0), Toast.LENGTH_SHORT).show();
					this.choixDesserts(null);
				}
			}
			break;
		}

		}
	}

}
