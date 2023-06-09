package com.csit28f3.wildassist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowBookingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowBookingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShowBookingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowBookingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowBookingsFragment newInstance(String param1, String param2) {
        ShowBookingsFragment fragment = new ShowBookingsFragment();
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
        View v = inflater.inflate(R.layout.fragment_show_bookings, container, false);

        Booking b = Session.getRecentBooking();

        ((TextView) v.findViewById(R.id.txtBookingDate)).setText(b.getDate());
        ((TextView) v.findViewById(R.id.txtBookingStartTime)).setText(b.getStartTime());
        ((TextView) v.findViewById(R.id.txtBookingEndTime)).setText(b.getEndTime());
        ((TextView) v.findViewById(R.id.txtBookingDestination)).setText(b.getDestination());
        ((TextView) v.findViewById(R.id.txtBookingPurpose)).setText(b.getPurpose());

        return v;
    }
}