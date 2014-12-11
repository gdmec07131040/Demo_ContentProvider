package cn.edu.gdmec.s07131040.demo_contentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText etName,etPhone;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etName=(EditText) findViewById(R.id.editText1);
        etPhone=(EditText) findViewById(R.id.editText2);
    }
    public void doClick(View v){
    	//��ȡ���������
    	String name=etName.getText().toString();
    	
    	//��ȡ����ĵ绰
    	String number=etPhone.getText().toString();
    	
    	//����һ�������ݣ�����ȡ�����IDֵ
    	ContentValues values=new ContentValues();
    	Uri rawContactUri=getContentResolver().insert(RawContacts.CONTENT_URI, values);
    	long id=ContentUris.parseId(rawContactUri);
    	
    	//��������
    	values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
    	values.put(Data.RAW_CONTACT_ID, id);
    	values.put(StructuredName.GIVEN_NAME, name);
    	getContentResolver().insert(Data.CONTENT_URI, values);
    	values.clear();
    	//����绰����
    	values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
    	values.put(Data.RAW_CONTACT_ID, id);
    	values.put(Phone.NUMBER, number);
    	values.put(Phone.TYPE, Phone.TYPE_MOBILE);
    	getContentResolver().insert(Data.CONTENT_URI, values);
    	
    	
    	Log.i("info", "��ϵ����Ϣ�Ѳ���");
    	
    	
    	
    	
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
