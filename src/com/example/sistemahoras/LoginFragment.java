package com.example.sistemahoras;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exe.sistemahoras.R;

public class LoginFragment extends Fragment {

	private String user = "Rafael";
	private String password = "123456";
	private String message = "";

	private LoginCallback mCallbacks;

	public interface LoginCallback {
		public void onLogin();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
	}

	@Override
	public void onStart() {
		SharedPreferences settings = getActivity().getPreferences(Context.MODE_PRIVATE);

		EditText edit1 = (EditText) getView().findViewById(R.id.editText1);
		edit1.setText(settings.getString("user_name", null));

		EditText edit2 = (EditText) getView().findViewById(R.id.editText2);
		edit2.setText(settings.getString("pass", null));

		super.onStart();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getView().findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean test = false;
				Context context = getActivity().getApplicationContext();

				EditText edit1 = (EditText) getView().findViewById(R.id.editText1);
				EditText edit2 = (EditText) getView().findViewById(R.id.editText2);

				SharedPreferences settings = getActivity().getPreferences(context.MODE_PRIVATE);
				settings.edit().putString("user_name", edit1.getText().toString()).putString("pass", edit2.getText().toString()).commit();

				if (!edit1.getText().toString().toLowerCase().equals(user.toLowerCase())
						|| !edit2.getText().toString().toLowerCase().equals(password.toLowerCase())) {
					message = "Login ou Senha inv??lida";
					test = true;
				} else
					message = "Salvo";

				if (test) {
					writeFile(edit1.getText().toString(), edit2.getText().toString());
					readFile();
				}
				
				Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
				toast.show();
				
				if (!test) {
					mCallbacks.onLogin();
			
				}

				

			}
		});

		final Button button2 = (Button) getView().findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				getActivity().finish();
			}
		});
	}

	private void writeFile(String user, String pass) {
		try {
			String logss = "Erro Login -" + user + "/" + pass;
			FileOutputStream fos = getActivity().openFileOutput("storageFile", Context.MODE_PRIVATE);
			fos.write(logss.getBytes());
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readFile() {
		try {
			Context context = getActivity().getApplicationContext();
			FileInputStream fis = context.openFileInput("storageFile");
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			Toast toast = Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT);
			toast.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (!(activity instanceof LoginCallback)) {
			throw new IllegalStateException("");
		}
		mCallbacks = (LoginCallback) activity;
	}

}
