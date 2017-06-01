package com.example.dangminhtien.lazembo.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dangminhtien.lazembo.R;

public class fragment_motasp extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public fragment_motasp() {
        // Required empty public constructor
    }

    public static fragment_motasp newInstance(String param1, String param2) {
        fragment_motasp fragment = new fragment_motasp();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_motasp, container, false);
    }

    private void xulyTxtGiasp () {

    }

    private void xulyTxtGiaspTruocKhiGiam () {

    }

    private void xylyTxtCamKet () {

    }

    private void xulySpMausac () {

    }

    private void xulySpKichco () {

    }

    private void xulyRbRating () {

    }

    private void xulyPagerHinhsp () {

    }

    private void xulyHienthiSpKichco () {

    }

    private void xylyHienthiSpMausac () {

    }

    private void xulyHienthinRbRating () {

    }

    private void xulyHienthiPagerHinhsp () {

    }

    private void setTextHinhthucgiaohang () {

        SpannableStringBuilder styledString
                = new SpannableStringBuilder(
                "Hình thức vận chuyển\n\n" // 22
                        + "COD: " // 5
                        + "Hình thức vận chuyển tận nhà. Do bưu điện Viettel vận chuyển, có tính phí vận chuyển đối với đơn hàng dưới 300 nghìn\n\n" // 118
                        + "VISA/MASTER CARD: " // 18
                        + "Miễn phí vận chuyển, Do bưu điện Viettel vận chuyển \n\n" // 53
                        + "Chuyển khoảng: " // 15
                        + "Miễn phí vận chuyển, Vietcombank: 19802948123, Ngân hàng quân đội: 12948192893, Ngân hàng Đông Á: 19382838192" // 109
        );

        styledString.setSpan(new RelativeSizeSpan(1.7f), 0, 22 - 1, 0);

        styledString.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, 22 - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledString.setSpan(new ForegroundColorSpan(Color.RED), 0, 22 - 1  ,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledString.setSpan(new ForegroundColorSpan(Color.BLUE), 22, 22 + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledString.setSpan(new ForegroundColorSpan(Color.BLUE), 27 + 118, 27 + 118 + 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledString.setSpan(new ForegroundColorSpan(Color.BLUE), 27 + 118 + 18 + 53, 27 + 118 + 18 + 53 + 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }
    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//         TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
