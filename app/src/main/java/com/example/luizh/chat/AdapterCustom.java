package com.example.luizh.chat;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luiz.h on 02/06/2017.
 */

public class AdapterCustom extends BaseAdapter {

    private final List<Chat> chats;
    private final Activity act;

    public AdapterCustom(List<Chat> chats, Activity act) {
        this.chats = chats;
        this.act = act;
    }

    @Override
    public int getCount() {

        return getChats().size();
    }

    @Override
    public Object getItem(int position) {
        return getChats().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater().inflate(R.layout.list_custom, parent, false);
        Chat chat = getChats().get(position);

        //Pegando as Referências das Views
        TextView name = (TextView)view.findViewById(R.id.list_custom_name);
        TextView message = (TextView)view.findViewById(R.id.list_custom_message);
        ImageView image = (ImageView)view.findViewById(R.id.list_custom_image);

        //Populando Views
        name.setText(chat.getName());
        message.setText(chat.getMessage());
        image.setImageResource(R.drawable.luiz);

        return view;
    }

    public List<Chat> getChats() {
        return chats;
    }
}


