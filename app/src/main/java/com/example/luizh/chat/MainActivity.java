package com.example.luizh.chat;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Command for move layouts up when soft keyboard is shown
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ListView lv = (ListView)findViewById(R.id.listview);

        //final AdapterCustom arrayChats = new AdapterCustom<Chat>(new Arraylist<Chat>(), this);
        final ArrayAdapter<Chat> arrayChats = new ArrayAdapter<Chat>(getApplication(),android.R.layout.simple_list_item_1);

        lv.setAdapter(arrayChats);

        // Timer to refresh the screen every 1s
        final Activity ac = this;
        Timer repeat = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                AsyncTaskList a = new AsyncTaskList();
                a.setActivity(ac);
                a.setArrayAdapterChat(arrayChats);
                a.execute();
            }
        };
        repeat.scheduleAtFixedRate(task, 1, 1000);
    }

    public void sendMessage(View view){

        Chat chat = new Chat();
        chat.setName("Luiz Correia");

        EditText txt_message = (EditText)findViewById(R.id.txt_message);
        chat.setMessage(txt_message.getText().toString());
        txt_message.setText(null);

        Toast.makeText(this, chat.getName()+", Message: '"+chat.getMessage()+"'. Send Successful", Toast.LENGTH_LONG).show();

        new AsyncTaskSendMessage(chat).execute();
    }
}