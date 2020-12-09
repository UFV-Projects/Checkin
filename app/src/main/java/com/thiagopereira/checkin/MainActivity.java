package com.thiagopereira.checkin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
	private List<String> categories = new ArrayList<String>();
	private String category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Spinner spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
		spinnerCategory.setOnItemSelectedListener(this);
		Cursor cats = BancoDadosSingleton.getInstance().buscar("Categoria", new String[]{"nome"}, "", "idCategoria ASC");

		while(cats.moveToNext()) {
			int name = cats.getColumnIndex("nome");

			categories.add(cats.getString(name));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerCategory.setAdapter(adapter);
	}

	@Override
	public void onItemSelected(AdapterView parent, View view, int position, long id) {
		category = parent.getAdapter().getItem(position).toString();
		Log.d("Ciclo", category);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}