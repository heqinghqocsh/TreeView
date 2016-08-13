package com.heqing.treeviewtest.bean.my_tree_view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.heqing.treeviewtest.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HQOCSHheqing on 2016/8/4.
 *
 * @description
 */
public class TreeTestActivity extends Activity{

    private ListView mListView;
    private NodeTreeAdapter mAdapter;
    private LinkedList<Node> mLinkedList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_dept_layout);
        mListView = (ListView)findViewById(R.id.id_tree);
        mAdapter = new NodeTreeAdapter(this,mListView,mLinkedList);
        mListView.setAdapter(mAdapter);
        initData();
    }

    private void initData(){
        List<Node> data = new ArrayList<>();
        addOne(data);
        mLinkedList.addAll(NodeHelper.sortNodes(data));
        mAdapter.notifyDataSetChanged();
    }

    private void addOne(List<Node> data){
        data.add(new Dept(1, 0, "总公司"));//可以直接注释掉此项，即可构造一个森林
        data.add(new Dept(2, 1, "一级部一级部门一级部门一级部门门级部门一级部门级部门一级部门一级部门门级部一级"));
        data.add(new Dept(3, 1, "一级部门"));
        data.add(new Dept(4, 1, "一级部门"));

        data.add(new Dept(222, 5, "二级部门--测试1"));
        data.add(new Dept(223, 5, "二级部门--测试2"));

        data.add(new Dept(5, 1, "一级部门"));

        data.add(new Dept(224, 5, "二级部门--测试3"));
        data.add(new Dept(225, 5, "二级部门--测试4"));

        data.add(new Dept(6, 1, "一级部门"));
        data.add(new Dept(7, 1, "一级部门"));
        data.add(new Dept(8, 1, "一级部门"));
        data.add(new Dept(9, 1, "一级部门"));
        data.add(new Dept(10, 1, "一级部门"));

        for (int i = 2;i <= 10;i++){
            for (int j = 0;j < 10;j++){
                data.add(new Dept(1+(i - 1)*10+j,i, "二级部门"+j));
            }
        }

        for (int i = 0;i < 5;i++){
            data.add(new Dept(101+i,11, "三级部门"+i));
        }

        for (int i = 0;i < 5;i++){
            data.add(new Dept(106+i,22, "三级部门"+i));
        }
        for (int i = 0;i < 5;i++){
            data.add(new Dept(111+i,33, "三级部门"+i));
        }
        for (int i = 0;i < 5;i++){
            data.add(new Dept(115+i,44, "三级部门"+i));
        }

        for (int i = 0;i < 5;i++){
            data.add(new Dept(401+i,101, "四级部门"+i));
        }
    }


}
