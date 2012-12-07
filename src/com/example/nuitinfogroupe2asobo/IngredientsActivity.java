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

public class IngredientsActivity extends Activity 
{
	protected static final int RESULT_SPEECH = 1;

	private ImageButton btnSpeak;
	
	private String arg0;
	
	private String arg1;
	
	private String[] ing = new String[5];
	
	private static final String url = "http://nuitinfo2012.iut-valence.fr/eq2/asobo/script.php?";
	
	private int i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingredients_ativity);
		Intent intent = getIntent();
	    this.arg0 = intent.getStringExtra("param0");
	    this.arg1 = intent.getStringExtra("param1");
	    i = 0;
	    
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
		getMenuInflater().inflate(R.menu.activity_ingredients_ativity, menu);
		return true;
	}
	
	public void choixAdd(View view)
	{
		if(i < 5)
		{
			TextView text = (TextView) findViewById(R.id.TextIng);
			if(text==null)
				Toast.makeText(getApplicationContext(), "CA VAUT NULL", Toast.LENGTH_SHORT).show();
			else
			{
				ing[i] = text.getText().toString();
				text.setText(null);
				i++;
			}
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Nombre maximum d'ingredients atteinds", Toast.LENGTH_SHORT).show();
		}
	}

	public void choixVal(View view)
	{
		String res = "";
		int j;
		for(j = 0; j < this.i; j++)
			res = res + "&ingredients" + (j+1) + "=" + this.ing[j];
		Intent intent = new Intent(this, WebViewActivity.class);
		intent.putExtra("url", url + "type=" + this.arg0 + "&choix=" + this.arg1 + res);
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
				
				if(text.get(0).equals("ajouter"))
					this.choixAdd(null);
				else
				{
					if(text.get(0).equals("valider"))
						this.choixVal(null);
					TextView Ctext = (TextView) findViewById(R.id.TextIng);
					if(Ctext==null)
						Toast.makeText(getApplicationContext(), "CA VAUT NULL", Toast.LENGTH_SHORT).show();
					
					Ctext.setText(text.get(0));
				}
			}
			break;
		}

		}
	}
}
