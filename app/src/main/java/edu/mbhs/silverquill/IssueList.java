package edu.mbhs.silverquill;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Ali on 11/2/2015.
 */
public class IssueList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] issueNames;
    private final String[] issueDates;
    private final Integer[] imageIds;

    public IssueList(Activity context,
                     String[] issueNames, String[] issueDates, Integer[] imageIds) {
        super(context, R.layout.issue_list_item, issueNames);
        this.context = context;
        this.issueNames = issueNames;
        this.issueDates = issueDates;
        this.imageIds = imageIds;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.issue_list_item, null, true);

        TextView issueName = (TextView) rowView.findViewById(R.id.issueName);
        TextView issueDate = (TextView) rowView.findViewById(R.id.issueDate);
        MLRoundedImageView imageView = (MLRoundedImageView) rowView.findViewById(R.id.thumbnail);

        issueName.setText(issueNames[position]);
        issueDate.setText(issueDates[position]);
        imageView.setImageResource(imageIds[position]);

        return rowView;
    }
}