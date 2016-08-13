package com.heqing.treeviewtest;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.heqing.treeviewtest.bean.bean.FileBean;
import com.heqing.treeviewtest.bean.my_tree_view.Dept;
import com.heqing.treeviewtest.bean.my_tree_view.NodeHelper;
import com.heqing.treeviewtest.bean.my_tree_view.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test(){
        List<Node> deptList = new ArrayList<>();
        Dept dept;
        for (int i = 0;i<10;i++){
            for (int j = 0;j < 5;j++){
                dept = new Dept();
                dept.setId(i);
                dept.setParentId(i);
                dept.setName("");
                deptList.add(dept);
            }
        }
        for (int j = 0;j < 10;j++){
            dept = new Dept();
            dept.setId(j);
            dept.setParentId(j);
            dept.setName("");
            deptList.add(dept);
        }
        NodeHelper.sortNodes(deptList, 2);


    }

    public void testSplit(){
        String tmp = "1111111111111111111111";
        tmp.split("A");
    }

    public void testDept(){
        List<Node> mDatas2 = new ArrayList<>();
//        mDatas2.add(new Dept(1, 0, "总公司"));
        mDatas2.add(new Dept(2, 1, "一级部门"));
        mDatas2.add(new Dept(3, 1, "一级部门"));
        mDatas2.add(new Dept(4, 1, "一级部门"));

        mDatas2.add(new Dept(222, 5, "二级部门--测试1"));
        mDatas2.add(new Dept(223, 5, "二级部门--测试2"));

        mDatas2.add(new Dept(5, 1, "一级部门"));

        mDatas2.add(new Dept(224, 5, "二级部门--测试3"));
        mDatas2.add(new Dept(225, 5, "二级部门--测试4"));

        mDatas2.add(new Dept(6, 1, "一级部门"));
        mDatas2.add(new Dept(7, 1, "一级部门"));
        mDatas2.add(new Dept(8, 1, "一级部门"));
        mDatas2.add(new Dept(9, 1, "一级部门"));
        mDatas2.add(new Dept(10, 1, "一级部门"));

        for (int i = 2;i <= 10;i++){
            for (int j = 0;j < 10;j++){
                mDatas2.add(new Dept(1+(i - 1)*10+j,i, "二级部门"));
            }
        }

        for (int i = 0;i < 5;i++){
            mDatas2.add(new Dept(101+i,11, "三级部门"));
        }
        for (int i = 0;i < 5;i++){
            mDatas2.add(new Dept(106+i,22, "三级部门"));
        }
        for (int i = 0;i < 5;i++){
            mDatas2.add(new Dept(111+i,33, "三级部门"));
        }
        for (int i = 0;i < 5;i++){
            mDatas2.add(new Dept(115+i,44, "三级部门"));
        }

        List<Node> nodeList = NodeHelper.sortNodes(mDatas2, 2);
        nodeList.clear();




    }

}