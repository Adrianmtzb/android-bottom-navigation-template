package mx.adrianmb.bottom_navigation.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.adrianmb.bottom_navigation.R;

/**
 * Created by adrianmb on 19/02/18.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.tvTitle) TextView tvTitle;

    public static LoginFragment newInstance(String title) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);

        return fragment;
    }

    private String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = getLayoutInflater().inflate(R.layout.fragment_mock, container, false);
        ButterKnife.bind(this, v);

        title = getArguments().getString("title");
        tvTitle.setText(title);

        return v;
    }
}
