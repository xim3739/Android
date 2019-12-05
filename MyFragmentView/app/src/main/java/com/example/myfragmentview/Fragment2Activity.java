package com.example.myfragmentview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2Activity extends Fragment implements View.OnClickListener {

    public View view;
    public Button f2BtClick;
    public EditText editText;

    public OnFragmentInteractionListener mListener;

    public Fragment2Activity() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + "OnFragmentInteractionListener Error");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment2, container, false);
        f2BtClick = view.findViewById(R.id.f2BtClick);
        editText = view.findViewById(R.id.editText);

        f2BtClick.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f2BtClick :


                String name = editText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                mListener.onFragmentInteration(bundle);

                break;
        }
    }

    public interface OnFragmentInteractionListener {
        public abstract void onFragmentInteration(Bundle bundle);
    }
}
