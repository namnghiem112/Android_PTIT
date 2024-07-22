package com.example.ex2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex2.R;
import com.example.ex2.adapter.MessageAdapter;
import com.example.ex2.model.Message;

import java.util.ArrayList;
import java.util.List;


public class FragmentNotification extends Fragment {
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<Message> list;

    public FragmentNotification() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noti,container,false);
        list = new ArrayList<>();
        list.add(new Message(R.drawable.img_1,"Meo Con","Hello Meo Con","15:20"));
        list.add(new Message(R.drawable.icon_chub,"Khanh Con","Hello Meo Con","12:20"));
        list.add(new Message(R.drawable.img_1,"Hang Bi","Hello Meo Con","16:20"));
        list.add(new Message(R.drawable.img_1,"Thu Hang","Hello Meo Con","13:20"));
        list.add(new Message(R.drawable.img_1,"Hang 2211","Hello Meo Con","10:20"));
        list.add(new Message(R.drawable.img_1,"Hang 2004","Hello Meo Con","5:20"));
        recyclerView = view.findViewById(R.id.recylerview);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        messageAdapter = new MessageAdapter(view.getContext(),list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(messageAdapter);
        return view;
    }
}
