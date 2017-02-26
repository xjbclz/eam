package com.ruwant.eam.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


//import com.ruwant.aspectj.annotation.DebugTrace;
//import com.example.aoplib.DebugLog;
import com.bumptech.glide.Glide;
import com.ruwant.aspectj.annotation.DebugTrace;
import com.ruwant.eam.EamApplication;
import com.ruwant.eam.R;
import com.ruwant.eam.model.entity.CommentDetail;
import com.ruwant.eam.presenter.EamNetworkPresenter;
import com.ruwant.eam.presenter.EamNetworkPresenterImpl;
import com.ruwant.eam.presenter.EamPresenter;
import com.ruwant.eam.presenter.EamPresenterImpl;

import com.ruwant.eam.service.ANRService;
import com.ruwant.eam.service.DataIntentService;
import com.ruwant.eam.util.EamLog;
import com.ruwant.eam.view.MainView;

//import com.jr.uhf.BluetoothActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends BaseActivity implements MainView {

    private String TAG = "MainActivity";

    private int colNum;

    private EamNetworkPresenter eamNetworkPresenter;

    private EamPresenter eamPresenter;

    private Dialog loadingDialog;

    private TableLayout table;

    private Button importButton;
    private EditText fileName;

    private Spinner colesNameSpinner;
    private ArrayAdapter<String> spinnerAdapter;
    private EditText queryContent;
    private Button queryButton;

    private EditText exportFileName;
    private Button exportButton;

    private Button scanButton;

    private Button testButton;

    private Button test2Button;

    private Intent serviceIntent;

//    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initVariables();
        initViews();
        loadData();

        //Log.e(TAG,"onCreate");
    }

//    @DebugLog

    @DebugTrace
    public void initVariables() {

        colNum = 0;

        eamPresenter = new EamPresenterImpl(MainActivity.this, MainActivity.this);

        eamNetworkPresenter = new EamNetworkPresenterImpl(MainActivity.this, MainActivity.this);

        loadingDialog = new ProgressDialog(MainActivity.this);

        serviceIntent = new Intent(MainActivity.this, ANRService.class);
        startService(serviceIntent);

        Log.e(TAG,"exit initVariables");
    }

    public void initViews() {

        Log.e(TAG,"entry initViews");

        table = (TableLayout) findViewById(R.id.tablelayout);
        table.setStretchAllColumns(true);

        fileName =  (EditText) findViewById(R.id.filename_editText);

        importButton = (Button) findViewById(R.id.import_button);
        importButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                importData();
            }
        });

        colesNameSpinner =  (Spinner) findViewById(R.id.colesname_spinner);
        colesNameSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                colNum = arg2;
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }

        });

        queryContent =  (EditText) findViewById(R.id.query_editText);

        queryButton = (Button) findViewById(R.id.query_button);
        queryButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                queryData();
            }
        });

        exportFileName =  (EditText) findViewById(R.id.export_filename_editText);

        exportButton = (Button) findViewById(R.id.export_button);
        exportButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                exportData();
            }
        });

        scanButton = (Button) findViewById(R.id.scan_button);
        scanButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                scanData();
            }
        });

        testButton = (Button) findViewById(R.id.test_button);
        testButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                testFunction();
            }
        });

        test2Button = (Button) findViewById(R.id.test2_button);
        test2Button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                test2Function();
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.image);

        Glide.with(this).load("http://b2bdemo01.hollywant.com/web/binary/image?model=mobile.banner&id=136&field=image&updatetime=1476344273.0").into(imageView);

    }

    public void loadData() {
        eamPresenter.setExcelColesName();
    }

    private void importData() {

        String content = fileName.getText().toString();

        EamLog.v("", "fileName=" + content);

        if (content != "" && content != " ") {
            eamPresenter.importData(content);
        }
    }

    private void queryData() {

        String content = queryContent.getText().toString();

        if (content != "" && content != " ") {
            eamPresenter.queryData(colNum, content);
        }
    }

    private void exportData() {

        String content = exportFileName.getText().toString();

        EamLog.v("", "exportFileName=" + content);

        if (content != "" && content != " ") {
            eamPresenter.exportData(content);
        }
    }

    private void scanData() {

        eamNetworkPresenter.login("admin", "123456", null);
//        try {
//            Log.e(TAG, "scanData");
//
//            Thread.sleep(6*1000);
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
//        startActivity(intent);
//
//        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }


    @Override
    public void setExcelContentToView(int rows, int cols,  Sheet sheet){

        for (int i = 0; i < rows; i++) {

            List<String> contentList = new ArrayList();
            String content;

            TableRow tableRow = new TableRow(MainActivity.this);
            tableRow.setBackgroundColor(Color.rgb(0, 255, 255));

            for (int j = 0; j < cols; j++) {
                content = sheet.getCell(j,i).getContents();
                contentList.add(content);

                EamLog.v("", "content=" + content);

                EditText cellView = new EditText(MainActivity.this);
                cellView.setBackgroundResource(R.drawable.table_frame_gray);
                cellView.setText(content);
                tableRow.addView(cellView);
            }

            table.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            if (i > 0) {
                eamPresenter.insertDataToDataBase(cols, contentList);
            }
        }
    }

    @Override
    public void setExcelColesNameToView(String[] colesName) {

        //将可选内容与ArrayAdapter连接
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colesName);

        //设置下拉列表的风格
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter添加到m_Spinner中
        colesNameSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void setDataBaseContentToView(int colCount, String[] rowContent){

            TableRow tableRow = new TableRow(MainActivity.this);
            tableRow.setBackgroundColor(Color.rgb(222, 220, 210));

            for (int j = 0; j < colCount; j++) {

                EamLog.v("", "rowContent[j]=" + rowContent[j]);

                EditText cellView = new EditText(MainActivity.this);
                cellView.setBackgroundResource(R.drawable.table_frame_gray);
                cellView.setText(rowContent[j]);
                tableRow.addView(cellView);
            }

            table.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        eamPresenter.onDestroy();

        stopService(serviceIntent);
    }

    private void testFunction(){

//        Toast.makeText(this, makeMD5Hash("123abc456"), Toast.LENGTH_LONG).show();
//
//        EamLog.v("", "md5string=" + makeMD5Hash("123abc456"));

//        CommentDetail commentDetailA = new CommentDetail();
//        commentDetailA.commentContent ="bad";
//
//        CommentDetail commentDetailB = commentDetailA.clone();
//        commentDetailB.commentContent = commentDetailA.commentContent;
//
//        EamLog.v("", "commentDetailB=" + commentDetailB.commentContent);
//
//        commentDetailB.commentContent = "good";
//
//        EamLog.v("", "commentDetailA=" + commentDetailA.commentContent);
//
//
//        EamLog.v("", "commentDetailB=" + commentDetailB.commentContent);

//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        EamApplication.getContext().startActivity(intent);

//        copy();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    private void test2Function(){

//        paste();

        Intent intent = new Intent(this, DataIntentService.class);
        startService(intent);

    }

    private String makeMD5Hash(String key) {
        String md5String;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.reset();
            mDigest.update(key.getBytes());
            md5String = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            md5String = String.valueOf(key.hashCode());
        }
        return md5String;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte value : bytes) {
            String hex = Integer.toHexString(0xFF & value);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private void copy(){
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);

        ClipData clip = ClipData.newPlainText("text","Hello, World!");

        clipboard.setPrimaryClip(clip);
    }

    private void paste(){

        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);

        //判断剪切板里是否有数据
        if ((clipboard.hasPrimaryClip())) {

            //判断数据类型是否是Text类型
            if ((clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))) {

                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

                CharSequence pasteData = item.getText();

                EamLog.v("", "pasteData=" + pasteData);
            }
        }

    }

    public void saveData(){};

    public void restoreData(){};

    public void releaseMemory(){};
}


