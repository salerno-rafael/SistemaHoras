package com.example.sistemahoras;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sistemahoras.dao.Atividades;
import com.example.sistemahoras.dao.UserDao;
import com.exe.sistemahoras.R;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		UserDao dao = new UserDao();
		ListView mDrawerList = (ListView) findViewById(R.id.listview);
		mDrawerList.setAdapter(new Adapter(getBaseContext(), R.layout.activity_add, dao.listData(getApplicationContext())));

		adicionar();
		remover();
		close();
	}

	private void clearFields() {
		EditText edit1 = (EditText) findViewById(R.id.editTextDesc);
		edit1.setText("");
		EditText edit2 = (EditText) findViewById(R.id.editTextDate);
		edit2.setText("");
	}

	private void close() {
		final Button buttonDel = (Button) findViewById(R.id.buttonClose);
		buttonDel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();

			}
		});
	}

	private void remover() {
		final Button buttonDel = (Button) findViewById(R.id.buttonDelete);
		buttonDel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Context context = getApplicationContext();
				UserDao dao = new UserDao();
				dao.deleteData(context);
				
				ListView mDrawerList = (ListView) findViewById(R.id.listview);
				Adapter ad = (Adapter) mDrawerList.getAdapter();
				ad.clear();
				ad.notifyDataSetChanged();

			}
		});
	}

	private void adicionar() {
		final Button button = (Button) findViewById(R.id.buttonAdd);

		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Context context = getApplicationContext();

				EditText edit1 = (EditText) findViewById(R.id.editTextDesc);
				EditText edit2 = (EditText) findViewById(R.id.editTextDate);

				UserDao dao = new UserDao();
				dao.saveData(context, edit1.getText().toString(), edit2.getText().toString());
				dao.listData(context);
				refreshList(edit1, edit2);
				clearFields();

			}

			private void refreshList(EditText edit1, EditText edit2) {
				ListView mDrawerList = (ListView) findViewById(R.id.listview);
				Adapter ad = (Adapter) mDrawerList.getAdapter();
				ad.add(new Atividades(edit1.getText().toString(), edit2.getText().toString()));
				ad.notifyDataSetChanged();
			}
		});
	}
}
