package menu.utama;

import menu.utama.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new CountDownTimer(2000, 100) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				login();
				finish();
			}
		}.start();
	}
	
	void login(){
		Intent i = new Intent(this, login.class);
		startActivity(i);
	}
	
	
	
	

}
