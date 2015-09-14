package com.brandycamacho.treeforce;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Welcome_S1_Fragment extends Fragment implements View.OnClickListener {
    View view;
    TextView tv_message;
    Button btn_next;
    int count = 0;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity().getBaseContext();

        view = inflater.inflate(R.layout.fragment_welcome_s1, container, false);

        tv_message = (TextView) view.findViewById(R.id.tv_message);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        count =1;
        return view;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btn_next:
                if (count == 1){
                tv_message.setText("We Need You To Authenticate With Our Servers");
                btn_next.setText("Next");
                count = 2;

                } else
                if (count == 2) {

                    tv_message.setText("Perfect, Please Enter Your Username And Password");
                    btn_next.setText("Next");
                    RelativeLayout rl = new RelativeLayout(context);
                    RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    TextView tv = new TextView(context);
                    tv.setText("WooHOo it worked!");
                    tv.setLayoutParams(textViewParams);
                    rl.addView(tv);
//                    count = 1;

                }
                break;
        }
    }

}
