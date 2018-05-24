package com.example.dell.wy_one.view.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.wy_one.R;
import com.example.dell.wy_one.view.custom.FlowLayout;
import com.example.dell.wy_one.view.custom.RecordSQLiteOpenHelper;
import com.example.dell.wy_one.view.custom.SearchListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    @BindView(R.id.lv_search)
    SearchListView lvSearch;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.search_textview)
    ImageView searchTextview;
    private RecordSQLiteOpenHelper helper;
    private SimpleCursorAdapter adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(SearchActivity.this, i, Toast.LENGTH_LONG).show();
            }
        });
        //初始化监听
        initListener();
        //初始化数据
        initData();
        //流式布局
        initChildViews();

    }

    //流式布局
    private void initChildViews() {
        flowLayout = (FlowLayout) findViewById(R.id.flow_layout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < datas.length; i++) {
            TextView view = new TextView(this);
            view.setText(datas[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            flowLayout.addView(view, lp);
        }
    }

    private void initListener() {
        //触摸框的监听事件
        edSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //点击输入框显示搜索并获取焦点
                edSearch.setFocusable(true);
                edSearch.setFocusableInTouchMode(true);
                tvSearch.setVisibility(View.VISIBLE);
                return false;
            }
        });
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                boolean hasData = hasData(edSearch.getText().toString().trim());
                if (!hasData) {
                    insertData(edSearch.getText().toString().trim());

                    queryData("");
                }
                edSearch.setFocusable(false);
                edSearch.setFocusableInTouchMode(false);
                edSearch.clearFocus();
                tvSearch.setVisibility(View.GONE);

                //跳转到H5界面
                String searchContent = edSearch.getText().toString().trim();
                //Toast.makeText(MainActivity.this, "搜索内容为："+searchContent, Toast.LENGTH_SHORT).show();
                edSearch.setText("");
            }
        });
        searchTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSearch.setText("");
            }
        });
        //"清空搜索历史"按钮
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //清空数据库
                deleteData();
                queryData("");
            }
        });
    }

    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

    /*检查数据库中是否已经有该条记录*/
    private boolean hasData(String tempName) {
        //从Record这个表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    private String datas[] = {
            "复仇者联盟3：无限战争", "钢铁侠2", "雷神2",
            "碟中谍5", "猩球崛起", "狄仁杰之四大天王",

    };

    private void initData() {
        //插入数据
        initSearch();
        //实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(this);
        // 第一次进入时查询所有的历史记录
        queryData("");
    }

    private void initSearch() {
        for (int i = 0; i < datas.length; i++) {
            final TextView textView = new TextView(this);
            textView.setText(datas[i]);


//           //保存按下能显示selector的效果，需要设置一个如下的属性
            // textView.setBackground(DrawUtils.getSelector(DrawUtils.getDrawable(MainActivity.this, Color.rgb(210, 210, 210), DensityUtil.dip2px(this, 4)), DrawUtils.getDrawable(MainActivity.this, Color.WHITE, DensityUtil.dip2px(this, 4))));

            //添加点击事件，也是实现显示selector的效果的一种方式
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到H5界面
                    String searchContent = textView.getText().toString();
                    Toast.makeText(SearchActivity.this, "" + searchContent, Toast.LENGTH_SHORT).show();
                    edSearch.setText("");
                }
            });
        }
    }

    /*模糊查询数据 并显示在ListView列表上*/
    private void queryData(String tempName) {
        //模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象,装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        lvSearch.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /*插入数据*/
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }
}
