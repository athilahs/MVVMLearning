package com.athila.mvvmlearning.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.athila.mvvmlearning.R;
import com.athila.mvvmlearning.databinding.FragmentHomeBinding;
import com.athila.mvvmlearning.viewmodel.UserViewModel;

public class HomeFragment extends Fragment {
    private View rootView;

    private Activity activity;
    private UserViewModel userViewModel;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initialize();
    }

    private void initialize() {
        userViewModel = new UserViewModel(activity);

        // MOCKUP
        userViewModel.setEmail("athila_test@gmail.com");
        userViewModel.setPassword("123456");
        userViewModel.login(null);

        // Another option is to pass the viewmodel within the intent
        userViewModel.initFromStorage();

        FragmentHomeBinding binding = FragmentHomeBinding.bind(rootView);
        binding.setUserViewModel(userViewModel);
    }
}
