package com.heqing.treeviewtest.bean.my_tree_view;

import com.heqing.treeviewtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HQOCSHheqing on 2016/8/2.
 *
 * @description 节点帮助类
 */
public class NodeHelper {

    /**
     * 传入所有的要展示的节点数据
     * @param nodeList  返回值是所有的根节点
     * @return
     */
    public static List<Node> sortNodes(List<Node> nodeList){

        List<Node> rootNodes = new ArrayList<>();
        int size = nodeList.size();
        Node m;
        Node n;
        //两个for循环整理出所有数据之间的斧子关系，最后会构造出一个森林（就是可能有多棵树）
        for (int i = 0;i < size;i++){
            m = nodeList.get(i);
            for (int j = i+1;j < size;j++){
                n = nodeList.get(j);
                if (m.parent(n)){
                    m.get_childrenList().add(n);
                    n.set_parent(m);
                }else if (m.child(n)){
                    n.get_childrenList().add(m);
                    m.set_parent(n);
                }
            }
        }
        //找出所有的树根，同事设置相应的图标（后面你会发现其实这里的主要
        // 作用是设置叶节点和非叶节点的图标）
        for (int i = 0;i < size;i++){
            m = nodeList.get(i);
            if (m.isRoot()){
                rootNodes.add(m);
            }
            setNodeIcon(m);
        }
        nodeList.clear();//此时所有的关系已经整理完成了
        // ，森林构造完成，可以清空之前的数据，释放内存，提高性能
        // ，如果之前的数据还有用的话就不清空
        nodeList = rootNodes;//返回所有的根节点
        rootNodes = null;
        return nodeList;
    }

    /**
     * 设置图标
     * @param node
     */
    private static void setNodeIcon(Node node){
        if (!node.isLeaf()){
            if (node.isExpand()){
                node.set_icon(R.mipmap.collapse);
            }else{
                node.set_icon(R.mipmap.expand);
            }
        }else{
            node.set_icon(-1);
        }
    }
}
