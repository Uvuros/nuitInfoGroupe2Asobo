package com.example.nuitinfogroupe2asobo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class IngredientsActivity extends Activity 
{

	private String arg0;
	
	private String arg1;
	
	private String[] ing = new String[5];
	
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
		
	}
}
