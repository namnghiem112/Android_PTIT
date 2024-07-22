package com.example.btlandroid;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btlandroid.db.SQLiteHelper;
import com.example.btlandroid.model.SinhVien;
import com.example.btlandroid.model.User;

import java.util.List;


public class FragmentInfo extends Fragment {
    private TextView infoUserName, infoEmail, infoPassWord;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoEmail = view.findViewById(R.id.infoEmail);
        infoUserName = view.findViewById(R.id.infoUserName);
        infoPassWord = view.findViewById(R.id.infoPassWord);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        String name = sqLiteHelper.getAllUser().get(sqLiteHelper.getAllUser().size()-1).getUsername();
        User user = sqLiteHelper.getUserByUserName(name);
        infoEmail.setText(user.getEmail());
        infoUserName.setText(user.getUsername());
        infoPassWord.setText(user.getPassword());
    }
}
