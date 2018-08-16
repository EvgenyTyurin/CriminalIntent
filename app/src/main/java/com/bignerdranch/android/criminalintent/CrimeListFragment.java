package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.util.List;

/**
 *  Crime List Fragment class
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private ImageView mSolvedImageView;
    private int posClicked;

    // Run point
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Connect class and layout
        View view = inflater.inflate(R.layout.fragment_crime_list,
                container,
                false);
        // Create recycler and assign then a layout manager
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    // Repaint list after return from Crime activity
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    // Paint/repaint crime list
    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
            // Update only clicked item - not suitable for pager since chapter 11
            // mAdapter.notifyItemChanged(posClicked);
        }
    }

    // Holder to fill item fragment
    private class CrimeHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;
        private int posInList;

        // Prepare item layout
        public CrimeHolder (LayoutInflater inflater, ViewGroup parent, int viewType) {
            super(inflater.inflate(viewType, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mSolvedImageView = itemView.findViewById(R.id.crime_solved);
        }

        // Fill crime data to presentation widgets
        public void bind(Crime crime, int position) {
            mCrime = crime;
            posInList = position;
            mTitleTextView.setText(mCrime.getTitle());
            Date dt = mCrime.getDate();
            mDateTextView.setText(new SimpleDateFormat("EEEE").format(dt) + " " +
                    DateFormat.getDateInstance(DateFormat.LONG).format(dt));
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        // On click on crime - show crime details
        @Override
        public void onClick(View view) {
            // Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getID());
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getID());
            posClicked = posInList;
            startActivity(intent);
        }
    }

    // Adapter to get crime and bind it to holder
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public  CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime, position);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        // Choosing layout to show crime
        @Override
        public int getItemViewType(int idx) {
            if (mCrimes.get(idx).isRequiresPolice())
                return R.layout.list_item_crime_police;
            else
                return R.layout.list_item_crime;
        }

    } // private class CrimeAdapter

} // public class CrimeListFragment
