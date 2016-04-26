package bgl.common.com.sqliteexmp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.util.Log;

public class MainActivity extends ActionBarActivity {
    EditText buckyInput;
    TextView buckyText;
    MyDbHandler myDbHandler;
    final static  private String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buckyInput=(EditText)findViewById(R.id.buckyInput);
        buckyText=(TextView)findViewById(R.id.buckyText);
        myDbHandler= new MyDbHandler(this,null,null,1);
        Log.i(TAG," onCreate method myDbHandler ::::::::::: "+myDbHandler);

        printDatabase();
    }

    public void printDatabase(){
        Log.i(TAG," printDatabase method dbString  1 ::::::::::: ");
        String dbString= myDbHandler.databaseToString();
        Log.i(TAG," printDatabase method dbString 2  ::::::::::: "+dbString);
        buckyText.setText(""+dbString);
        buckyInput.setText("");

    }
    public void addButtonClicked(View v) {
        Products products=new Products(buckyInput.getText().toString());
        myDbHandler.addProduct(products);
        buckyInput.setText("");
         printDatabase();
    }
    public void deleteOnClicked(View v) {
        String t=buckyInput.getText().toString();
        myDbHandler.deleteProduct(t);
         printDatabase();

    }




}
