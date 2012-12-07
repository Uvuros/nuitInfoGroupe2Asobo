package com.example.nuitinfogroupe2asobo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RegionActivity extends Activity 
{

	private String arg0;
	
	private String arg1;
	
	private static final String url = "http://nuitinfo2012.iut-valence.fr/eq2/asobo/script.php?";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_region);
		Intent intent = getIntent();
	    this.arg0 = intent.getStringExtra("param0");
	    this.arg1 = intent.getStringExtra("param1");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_region, menu);
		return true;
	}
	
	public void choixRegion(View view)
	{
		TextView text = (TextView) findViewById(R.id.textBox);
		if(text==null)
			Toast.makeText(getApplicationContext(), "CA VAUT NULL", Toast.LENGTH_SHORT).show();
		else
		{
			String res = text.getText().toString();
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra("url", this.url + "type=" + this.arg0 + "&choix=" + this.arg1 + "&region=" + res);
			startActivity(intent);
		}
	}

}
