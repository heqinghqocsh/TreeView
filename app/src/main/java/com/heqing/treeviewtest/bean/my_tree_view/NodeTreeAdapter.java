package com.heqing.treeviewtest.bean.my_tree_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heqing.treeviewtest.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HQOCSHheqing on 2016/8/3.
 *
 * @description 适配器类，就是listview最常见的适配器写法
 */
public class NodeTreeAdapter extends BaseAdapter{

    //大家经常用ArrayList，但是这里为什么要使用LinkedList
    // ，后面大家会发现因为这个list会随着用户展开、收缩某一项而频繁的进行增加、删除元素操作，
    // 因为ArrayList是数组实现的，频繁的增删性能低下，而LinkedList是链表实现的，对于频繁的增删
    //操作性能要比ArrayList好。
    private LinkedList<Node> nodeLinkedList;
    private LayoutInflater inflater;
    private int retract;//缩进值
    private Context context;

    public NodeTreeAdapter(Context context,ListView listView,LinkedList<Node> linkedList){
        inflater = LayoutInflater.from(context);
        this.context = context;
        nodeLinkedList = linkedList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);
            }
        });
        //缩进值，大家可以将它配置在资源文件中，从而实现适配
        retract = (int)(context.getResources().getDisplayMetrics().density*10+0.5f);
    }

    /**
     * 展开或收缩用户点击的条目
     * @param position
     */
    private void expandOrCollapse(int position){
        Node node = nodeLinkedList.get(position);
        if (node != null && !node.isLeaf()){
            boolean old = node.isExpand();
            if (old){
                List<Node> nodeList = node.get_childrenList();
                int size = nodeList.size();
                Node tmp = null;
                for (int i = 0;i < size;i++){
                    tmp = nodeList.get(i);
                    if (tmp.isExpand()){
                        collapse(tmp,position+1);
                    }
                    nodeLinkedList.remove(position+1);
                }
            }else{
                nodeLinkedList.addAll(position + 1, node.get_childrenList());
            }
            node.setIsExpand(!old);
            notifyDataSetChanged();
        }
    }
    /**
     * 递归收缩用户点击的条目
     * 因为此中实现思路是：当用户展开某一条时，就将该条对应的所有子节点加入到nodeLinkedList
     * ，同时控制缩进，当用户收缩某一条时，就将该条所对应的子节点全部删除，而当用户跨级缩进时
     * ，就需要递归缩进其所有的孩子节点，这样才能保持整个nodeLinkedList的正确性，同时这种实
     * 现方式避免了每次对所有数据进行处理然后插入到一个list，最后显示出来，当数据量一大，就会卡顿，
     * 所以这种只改变局部数据的方式性能大大提高。
     * @param position
     */
    private void collapse(Node node,int position){
        node.setIsExpand(false);
        List<Node> nodes = node.get_childrenList();
        int size = nodes.size();
        Node tmp = null;
        for (int i = 0;i < size;i++){
            tmp = nodes.get(i);
            if (tmp.isExpand()){
                collapse(tmp,position+1);
            }
            nodeLinkedList.remove(position+1);
        }
    }

    @Override
    public int getCount() {
        return nodeLinkedList.size();
    }

    @Override
    public Object getItem(int position) {
        return nodeLinkedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.tree_listview_item,null);
            holder = new ViewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.id_treenode_icon);
            holder.label = (TextView)convertView.findViewById(R.id.id_treenode_label);
            holder.confirm = (LinearLayout)convertView.findViewById(R.id.id_confirm);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Node node = nodeLinkedList.get(position);
        holder.label.setText(node.get_label());
        if(node.get_icon() == -1){
            holder.imageView.setVisibility(View.INVISIBLE);
        }else{
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setImageResource(node.get_icon());
        }
        holder.confirm.setTag(position);
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"选中:"+v.getTag(),Toast.LENGTH_SHORT).show();
            }
        });
        convertView.setPadding(node.get_level()*retract,5,5,5);
        return convertView;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView label;
        public LinearLayout confirm;
    }

}
