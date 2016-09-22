package cn.edu.hebut.iscs.kwsms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.hebut.iscs.kwsms.R;
import cn.edu.hebut.iscs.kwsms.entity.ExpertInfo;

/**
 * Created by lixueyang on 16-8-29.
 */
public class SendExpertAdapter extends ArrayAdapter<ExpertInfo> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ExpertInfo> list = new ArrayList<ExpertInfo>();
    public List<Boolean> stateList = new ArrayList<Boolean>();

    public SendExpertAdapter(Context context) {
        super(context, R.layout.item_for_send_exper);
        this.mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_for_send_exper, parent,
                    false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ExpertInfo expertInfo = getItem(position);
        viewHolder.textViewSmsId.setText(expertInfo.getId() + "");
        viewHolder.textViewNameCode.setText(expertInfo.getName() + "(" + expertInfo.getExpertCode() + ")");
        viewHolder.textViewPhone.setText(expertInfo.getTel());
        viewHolder.textViewSmsMansege.setText(expertInfo.getMsgContent());
        viewHolder.checkBoxDelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    stateList.set(position, true);
                    list.add(expertInfo);
                } else {
                    stateList.set(position, false);
                    list.remove(expertInfo);
                }
            }
        });
        if (position >= stateList.size()) {
            stateList.add(false);
        }
        if (stateList.get(position)) {
            viewHolder.checkBoxDelect.setChecked(true);
        } else {
            viewHolder.checkBoxDelect.setChecked(false);
        }
        return convertView;
    }

    public List<ExpertInfo> getList() {
        return list;
    }

    public void setList(List<ExpertInfo> list) {
        this.list = list;
    }

    public List<Boolean> getStateList() {
        return stateList;
    }

    public void setStateList(List<Boolean> stateList) {
        this.stateList = stateList;
    }

    static class ViewHolder {
        @BindView(R.id.textView_sms_id)
        TextView textViewSmsId;
        @BindView(R.id.textView_name_code)
        TextView textViewNameCode;
        @BindView(R.id.textView_phone)
        TextView textViewPhone;
        @BindView(R.id.checkBox_delect)
        CheckBox checkBoxDelect;
        @BindView(R.id.textView_sms_mansege)
        TextView textViewSmsMansege;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
