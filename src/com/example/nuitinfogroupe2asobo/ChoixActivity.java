package com.example.nuitinfogroupe2asobo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ChoixActivity extends Activity 
{

	private String arg0;
	
	private static final String url = "www.spaume.org/?";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choix);
		Intent intent = getIntent();
		this.arg0 = intent.getStringExtra("param");
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

}
