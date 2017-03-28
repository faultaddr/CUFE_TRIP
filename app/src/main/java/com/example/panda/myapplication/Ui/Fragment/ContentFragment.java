package com.example.panda.myapplication.Ui.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panda.myapplication.Data.ImageData;
import com.example.panda.myapplication.Data.JianDanHtmlParser;
import com.example.panda.myapplication.R;
import com.example.panda.myapplication.Tools.AsynImageLoader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import static java.lang.Integer.valueOf;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private WebView textView;
    private ImageView imageView;
    final String mimeType = "text/html";
    final String encoding = "utf-8";
    Charset charset = Charset.defaultCharset();
    private OnFragmentInteractionListener mListener;
    private static int position;

    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance(Bundle bundle) {
        ContentFragment fragment = new ContentFragment();
        //Bundle args = bundle;
        //position= valueOf(bundle.getString("contentPosition")) ;
        if (bundle.getString("position") != null) {
            Log.i(">>contentPosition>>", "" + bundle.getString("position"));
            position = Integer.parseInt(bundle.getString("position"));
            position--;
        }
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.view1, container, false);
        textView = (WebView) contentView.findViewById(R.id.textview_route);
        imageView = (ImageView) contentView.findViewById(R.id.imageview1);
        AsynImageLoader asynImageLoader = new AsynImageLoader();
        Log.i(">>position", position + "");
        asynImageLoader.showImageAsyn(imageView, ImageData.imageUrl.get(position), 0x7f0200e9);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        try {
            Log.i(">>charset", charset.displayName());

            textView.loadDataWithBaseURL(null, JianDanHtmlParser.getHtml(ImageData.getDataUrl(getContext()).get(position)), "text/html", "utf-8", null);
            Log.i(">>parse",ImageData.getDataUrl(getContext()).get(position)+"");
            //textView.loadData(new String(JianDanHtmlParser.getHtml(ImageData.getDataUrl(getContext()).get(position)).getBytes(charset),encoding), mimeType,
            //encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
