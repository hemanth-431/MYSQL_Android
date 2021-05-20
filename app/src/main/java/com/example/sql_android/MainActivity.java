package com.example.sql_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText name,contact,dof;
Button insert,delete,view,update;
DB_Helper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
name=findViewById(R.id.name);
contact=findViewById(R.id.contact);
dof=findViewById(R.id.dob);
insert=findViewById(R.id.btninsert);
delete=findViewById(R.id.btndelete);
view=findViewById(R.id.btnview);
update=findViewById(R.id.btnupdate);
DB=new DB_Helper(this);
insert.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String nametxt=name.getText().toString();
        String contacttxt=contact.getText().toString();
        String dobtxt=dof.getText().toString();
        Boolean check=DB.insertuserdata(nametxt,contacttxt,dobtxt);
        if(check){
            Toast.makeText(MainActivity.this,"Success inserted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
        }
    }
});

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt=name.getText().toString();
                String contacttxt=contact.getText().toString();
                String dobtxt=dof.getText().toString();
                Boolean check=DB.updateuserdata(nametxt,contacttxt,dobtxt);
                if(check){
                    Toast.makeText(MainActivity.this,"Success updated",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt=name.getText().toString();
                String contacttxt=contact.getText().toString();
                String dobtxt=dof.getText().toString();
                Boolean check=DB.deletedata(nametxt);
                if(check){
                    Toast.makeText(MainActivity.this,"Success deleted",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor check=DB.getdata();
                if(check.getCount()==0){
                    Toast.makeText(MainActivity.this,"No data selected",Toast.LENGTH_LONG).show();
                    return;
                }
StringBuffer s=new StringBuffer();
while (check.moveToNext()){
    s.append("Name : "+check.getString(0)+"\n");
    s.append("Contact : "+check.getString(1)+"\n");
    s.append("Date of Birth : "+check.getString(2)+"\n\n");
}
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
builder.setCancelable(true);
builder.setTitle("Users Entries");
builder.setMessage(s.toString());
builder.show();
                    Toast.makeText(MainActivity.this,"View",Toast.LENGTH_LONG).show();
                }

        });
    }
}
