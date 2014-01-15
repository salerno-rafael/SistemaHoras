package com.example.sistemahoras;

import com.exe.sistemahoras.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements LoginFragment.LoginCallback {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		openLoginFragment();
	}

	private void openLoginFragment() {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, new LoginFragment(), "loginFragment");
		transaction.commit();
	}

	@Override
	public void onLogin() {
		Intent intent = new Intent(getApplicationContext(), StartActivity.class);
		startActivity(intent);
	}
		

}
