package nwf.myapplication;
import android.content.ContentValues;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;


import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDa, btnNz,btnNet;
    EditText editZapros;
    TextView textView6,textView7;
    ListView listView7;
    SimpleCursorAdapter userAdapter;
    int D = 0;
    int Q = 1;
    Cursor c = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDa=(Button) findViewById(R.id.btnDa);
        btnDa.setOnClickListener(this);

        btnNz=(Button) findViewById(R.id.btnNz);
        btnNz.setOnClickListener(this);

        btnNet=(Button) findViewById(R.id.btnNet);
        btnNet.setOnClickListener(this);

        editZapros=(EditText) findViewById(R.id.editZapros);
        textView6 = (TextView)findViewById(R.id.textView6);
        textView7 = (TextView)findViewById(R.id.textView7);
        //
        //SQLiteDatabase database = EDOH.getReadableDatabase();
        //c=db.query("Yvlechenie",new String[]{"tablica"},"_id = ?",new String[] {"1"},null,null,null);
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("MyBD.bd", MODE_PRIVATE, null);
        c = db.rawQuery("SELECT * FROM Yvlechenie;", null);
        if (c.getColumnCount() == 1)
        {
            editZapros.append("1");
        }
        if (c.getColumnCount() > 1 )
        {
            editZapros.append("Много");
        }
        String[] headers = new String[]{"_id"};
        int[] to = new int[] { R.id.textView7,};
        userAdapter = new SimpleCursorAdapter(this, R.layout.activity_main, c, headers, to , 0);
        listView7.setAdapter(userAdapter);
        //textView7.setText("Найдено элементов: " + String.valueOf(c.getCount()));
        //Cursor cursor2 = db.rawQuery("select * from Yvlechenie;", new String[]{null});
        //Cursor cursor = db.rawQuery("SELECT * FROM Yvlechenie;", null);
        //textView7.setText(query.toString());
        //listView7.setAdapter(userAdapter);
        //textView7.setText(c.toString());
        c.close();
    }
    @Override
    public void onClick (View v)
    {

        switch (v.getId()){
            case R.id.btnDa:

                if (D == 1)
                {
                    editZapros.append("1");//(номер столбца) = {1} and
                    Q++;
                    break;
                }
                D++;
            case R.id.btnNet:
                if (D == 1)
                {
                    editZapros.append("0");
                    Q++;
                }
                break;
            case R.id.btnNz:
                if (D == 1)
                {
                    editZapros.append("2");
                    Q++;
                }
                break;
        }
        //SQLiteDatabase db = new DBHelper(this).openDataBase();
        //c =  db.rawQuery("select * from Yvlechenie", null);
        //c = db.query("Yvlechenie",null,null,null,null,null,null);
        //c =db.rawQuery("select * from ", new String[]{"Yvlechenie"});
        //String[] headers = new String[]{"tablica"};
        // userAdapter = new SimpleCursorAdapter(this, R.layout.activity_main, c, headers, new int[]{android.R.id.text1}, 0);
        //textView7.setText("Найдено элементов: " + String.valueOf(c.getCount()));
        //listView7.setAdapter(userAdapter);

    }



}
