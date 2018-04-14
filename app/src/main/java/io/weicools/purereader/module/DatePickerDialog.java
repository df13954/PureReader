package io.weicools.purereader.module;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.weicools.purereader.R;

/**
 * Create by weicools on 2018/4/12.
 * <p>
 * desc: 日期选择Dialog
 */
public class DatePickerDialog extends DialogFragment {
    @BindView(R.id.date_picker)
    DatePicker mDatePicker;
    Unbinder unbinder;

    private int currYear, currMonth, currDay;
    private OnDateSetListener mOnDateSetListener;

    public DatePickerDialog() {
        // Required empty public constructor
    }

    public static DatePickerDialog newInstance(OnDateSetListener listener, int year,
                                               int monthOfYear, int dayOfMonth) {
        DatePickerDialog ret = new DatePickerDialog();
        ret.initialize(listener, year, monthOfYear, dayOfMonth);
        return ret;
    }

    private void initialize(OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        mOnDateSetListener = listener;

        currYear = year;
        currMonth = monthOfYear;
        currDay = dayOfMonth;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
        unbinder = ButterKnife.bind(this, view);

        mDatePicker.init(currYear, currMonth, currDay, null);
        return view;
    }

    @OnClick(R.id.btn_ok)
    void onSelectDate() {
        mOnDateSetListener.onDateSet(DatePickerDialog.this,
                mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    void onCancel() {
//        if (getDialog() != null) {
//            getDialog().cancel();
//        }
        dismiss();
    }

    public void setMaxDate(Calendar maxDate) {
        mDatePicker.setMaxDate(maxDate.getTimeInMillis());
    }

    public void setMinDate(Calendar minDate) {
        mDatePicker.setMinDate(minDate.getTimeInMillis());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnDateSetListener {
        void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth);
    }
}
