package com.jr.uhf;

import com.jr.uhf.InventoryTagActivity.ButtonreadtagListner;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button buttonBluetooth ;
	private Button buttonSerial;
	private Button buttonExit ;
	private Button buttonUSB ;
	private Button buttonHelp;
	MyActivityManager manager ;
	private TextView textTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textTitle = (TextView) findViewById(R.id.textView_title);
		textTitle.setText("请选择操作入口");
		manager = (MyActivityManager)getApplication();
		manager.addActivity(this);
		//初始UI
		this.initView();
		//监听按钮
		this.listner();
	}
	/**
	 * 初始UI
	 * @author jimmy
	 * @Date 2015-1-13
	 */
	private void initView(){
		buttonBluetooth = (Button) findViewById(R.id.buttonBluetoothOption);
		buttonSerial = (Button) findViewById(R.id.buttonSerialOption);
		buttonExit = (Button) findViewById(R.id.button_exit);
		buttonUSB = (Button) findViewById(R.id.buttonUSBOption);
		buttonHelp = (Button) findViewById(R.id.button_help);
	}
	/**
	 * 监听按钮
	 * @author jimmy
	 * @Date 2015-1-13
	 */
	private void listner(){
		//蓝牙操作入口
		buttonBluetooth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
				startActivity(intent);

			}
		});
		//串口操作入口
		buttonSerial.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent serialportIntent = new Intent(MainActivity.this, SerialportSettingActivity.class);
				startActivity(serialportIntent);

			}
		});
		//help
		buttonHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent helpIntent = new Intent(MainActivity.this, HelpAcivity.class);
				startActivity(helpIntent);
			}
		});
		//USB
		buttonUSB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent UsbIntent = new Intent(MainActivity.this, UsbSettingActivity.class);
				startActivity(UsbIntent);
			}
		});
		//退出
		buttonExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MainActivity.this.exit();

			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			this.exit();
		}
		return super.onKeyDown(keyCode, event);
	}
	/**
	 * 退出
	 * @author jimmy
	 * @Date 2015-1-14
	 */
	public void exit(){
		new AlertDialog.Builder(this)
				.setTitle("退出程序")
				.setMessage("是否退出程序？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						manager.exit();
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		}).show();
	}
}
