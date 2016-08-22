package com.szl.fundlistdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FundListActivity extends AppCompatActivity {
    MyHorizontalScrollView mHorizontalScrollView, mPinnedHorizontalScrollView;
    ObservableScrollView main_scrollview, root_scrollview;
    WrapContentListView mLeftListView;
    WrapContentListView mRightListView;
    LeftAdapter mLeftAdapter;
    RightAdapter mRightAdapter;
    SwipeRefreshLayout swiperefresh;
    LinearLayout ll_pinned_otherfund;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_list);
        ll_pinned_otherfund = (LinearLayout) findViewById(R.id.ll_pinned_otherfund);


        mHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.hscrollview);

        mPinnedHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.pinnedhscrollview);


        mLeftListView = (WrapContentListView) findViewById(R.id.left_lv);

        mRightListView = (WrapContentListView) findViewById(R.id.right_lv);

        mLeftAdapter = new LeftAdapter(this);
        mRightAdapter = new RightAdapter(this);
        mLeftListView.setAdapter(mLeftAdapter);
        mRightListView.setAdapter(mRightAdapter);

        main_scrollview = (ObservableScrollView) findViewById(R.id.main_scrollview);


        mLeftAdapter.notifyDataSetChanged();
        mRightAdapter.notifyDataSetChanged();

        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        root_scrollview = (ObservableScrollView) findViewById(R.id.root_scrollview);


        mRightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });



        mHorizontalScrollView.setOnScrollChangedCallback(new MyHorizontalScrollView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                allScroll(l, t);
            }
        });



        mPinnedHorizontalScrollView.setOnScrollChangedCallback(new MyHorizontalScrollView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                allScroll(l, t);
            }
        });





        main_scrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                if (y == 0) {
                    swiperefresh.setEnabled(true);
                } else {
                    swiperefresh.setEnabled(false);
                }

            }
        });


        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        swiperefresh.post(new Runnable() {
                            @Override
                            public void run() {
                                swiperefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });


    }

    private synchronized void allScroll(int l, int t) {
        mPinnedHorizontalScrollView.scrollTo(l, t);
        mHorizontalScrollView.scrollTo(l, t);
    }


    public class LeftAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mLayoutInflater;

        public LeftAdapter(Context context) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(mContext);
        }


        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public String getItem(int position) {
            return "left:" + position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LeftHolder holder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.item_left, null);
                holder = new LeftHolder();
                holder.tv_fundname = (TextView) convertView.findViewById(R.id.tv_fundname);
                convertView.setTag(holder);

            } else {
                holder = (LeftHolder) convertView.getTag();

            }

            holder.tv_fundname.setText(getItem(position));


            return convertView;
        }


    }

    private class LeftHolder {
        TextView tv_fundname;
    }


    public class RightAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mLayoutInflater;

        public RightAdapter(Context context) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public String getItem(int position) {
            return "right:" + position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            RightHolder holder = null;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.item_right, null);
                holder = new RightHolder();
                holder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
                holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
                holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
                holder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
                holder.tv_5 = (TextView) convertView.findViewById(R.id.tv_5);
                holder.tv_6 = (TextView) convertView.findViewById(R.id.tv_6);
                holder.tv_7 = (TextView) convertView.findViewById(R.id.tv_7);
                holder.btn_8 = (TextView) convertView.findViewById(R.id.tv_8);
                convertView.setTag(holder);

            } else {
                holder = (RightHolder) convertView.getTag();

            }

            holder.tv_1.setText(position + "ofofof" + 1);
            holder.tv_2.setText(position + "ofofof" + 2);
            holder.tv_3.setText(position + "ofofof" + 3);
            holder.tv_4.setText(position + "ofofof" + 4);
            holder.tv_5.setText(position + "ofofof" + 5);
            holder.tv_6.setText(position + "ofofof" + 6);
            holder.tv_7.setText(position + "ofofof" + 7);
            holder.btn_8.setText(position + "ofofof" + 8);
            holder.btn_8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), position + " " + 8, Toast.LENGTH_SHORT).show();
                }
            });


            return convertView;
        }


    }

    private class RightHolder {
        TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, btn_8;
    }


}
