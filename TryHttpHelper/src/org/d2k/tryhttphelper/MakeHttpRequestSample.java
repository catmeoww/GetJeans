package org.d2k.tryhttphelper;
import java.util.HashMap;
import java.util.Map;
import org.d2k.helper.HttpHelper;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MakeHttpRequestSample extends Activity {
	private String CLASSTAG = MakeHttpRequestSample.class.getSimpleName();
    private EditText txtUrl;
    private Button btnFetch;
    private TextView txtResult;
    private EditText input ;
    private Button postBtn ;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(CLASSTAG,"onCreate(): enter into onCreate");
        setContentView(R.layout.main);
    	Log.d(CLASSTAG,"onCreate(): contentView set successfully!");
        txtUrl = (EditText)findViewById(R.id.url);
        btnFetch = (Button)findViewById(R.id.button);
        txtResult = (TextView)findViewById(R.id.content);
        input = (EditText)findViewById(R.id.input);
        postBtn = (Button)findViewById(R.id.postBtn);
    	
        btnFetch.setOnClickListener(get);
        Log.d(CLASSTAG,"onCreate(): getButton set successfully!");
        postBtn.setOnClickListener(post);
        Log.d(CLASSTAG,"onCreate(): postButton set successfully!");

    }
    private Button.OnClickListener get = new Button.OnClickListener(){
        public void onClick(View v){
        	getRequest(txtResult,txtUrl);
        }
    };
    private Button.OnClickListener post = new Button.OnClickListener(){
        public void onClick(View v){
        	postRequest(txtResult,txtUrl);
        }
    };
    
    public void getRequest(TextView txtResult, EditText txtUrl){
        String url = txtUrl.getText().toString();
        
        //httpHelper sample usage: make get request!
        HttpHelper helper = new HttpHelper();
        String answer = helper.performGetToString(url);
        //end of the sample
        txtResult.setText(answer);
    }
    
    public void postRequest(TextView txtResult, EditText txtUrl){
    	String url = txtUrl.getText().toString();
    	Log.d(CLASSTAG,"postRequest(): the url is "+url);
    	
        //httpHelper sample usage: make post request!
    	Map<String,String> param = new HashMap<String,String>();
    	param.put("title",input.getText().toString());
    	Log.d(CLASSTAG,"postRequest(): put param ok!");
    	
        HttpHelper helper = new HttpHelper();
        String answer = helper.performPostToString(url,param);
        //end of the sample
        txtResult.setText(answer);
    }
}