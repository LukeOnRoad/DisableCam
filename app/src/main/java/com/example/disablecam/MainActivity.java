package com.example.disablecam;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


@SuppressLint("NewApi") public class MainActivity extends ActionBarActivity {
private DevicePolicyManager policyManager;
private ComponentName mDeviceAdminSample;
private Button bt3;
private Button bt2;
private Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // 获取设备管理服务
         policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        // AdminReceiver 继承自 DeviceAdminReceiver
          mDeviceAdminSample = new ComponentName(getApplicationContext(), MyAdmin.class); 
          bt1=(Button)findViewById(R.id.button1);
          bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
				Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

	            // 权限列表
	            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);

	            // 描述(additional explanation)
	            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"------ 其他描述 ------");

	            startActivityForResult(intent, 0);	
			}
		});
          bt2=(Button)findViewById(R.id.button2);
          bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				policyManager.setCameraDisabled(mDeviceAdminSample, true);
                Toast.makeText(MainActivity.this, "禁用相机使用", Toast.LENGTH_SHORT).show();
			}
		});
          bt3=(Button)findViewById(R.id.button3);
          bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				policyManager.setCameraDisabled(mDeviceAdminSample, false);
                Toast.makeText(MainActivity.this, "启用相机使用", Toast.LENGTH_SHORT).show();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 激活设备管理权限 成功执行激活时，DeviceAdminReceiver中的 onEnabled 会响应
     */
    private void activeManage() {
            // 启动设备管理(隐式Intent) - 在AndroidManifest.xml中设定相应过滤器
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

            // 权限列表
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);

            // 描述(additional explanation)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"------ 其他描述 ------");

            startActivityForResult(intent, 0);
    }
}
