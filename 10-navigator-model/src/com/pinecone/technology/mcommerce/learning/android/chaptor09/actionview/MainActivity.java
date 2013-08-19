package com.pinecone.technology.mcommerce.learning.android.chaptor09.actionview;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.pinecone.technology.mcommerce.learning.android.chaptor09.R;

public class MainActivity extends Activity {

	private MenuItem menuItem;
	private ShareActionProvider provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		provider = (ShareActionProvider) menu.findItem(R.id.menu_share)
				.getActionProvider();
		// Initialize the share intent
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		provider.setShareIntent(intent);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_load:
			menuItem = item;
			menuItem.setActionView(R.layout.progressbar);
			menuItem.expandActionView();
			TestTask task = new TestTask();
			task.execute("test");
			break;
		case R.id.menu_share:
			doShare();
			break;
		default:
			break;
		}
		return true;
	}

	private class TestTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// Simulate something long running
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			menuItem.collapseActionView();
			menuItem.setActionView(null);
		}
	};

	public void doShare() {
		// Populare the share intent with data
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");
		provider.setShareIntent(intent);
	}
}