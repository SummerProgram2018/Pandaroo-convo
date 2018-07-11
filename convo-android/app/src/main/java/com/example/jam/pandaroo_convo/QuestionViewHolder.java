package com.example.jam.pandaroo_convo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    private TextView simpleTextView;

    public QuestionViewHolder(final View itemView) {
        super(itemView);
        simpleTextView = (TextView) itemView.findViewById(R.id.lblListItem);
    }

    public void bindData(final Question viewModel) {
        simpleTextView.setText(viewModel.getQuestion());
    }
}
