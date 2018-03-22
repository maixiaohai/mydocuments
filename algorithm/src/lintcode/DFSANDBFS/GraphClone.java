package lintcode.DFSANDBFS;

import java.util.*;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/19
 * @Description 深度克隆一张无向图，图中的每个节点包含一个 label 和一个列表 neighbors
 */
public class GraphClone {

     public class UndirectedGraphNode {
          int label;
          ArrayList<UndirectedGraphNode> neighbors;
          UndirectedGraphNode(int x) {
              label = x;
              neighbors = new ArrayList<UndirectedGraphNode>();
          }
     };

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        UndirectedGraphNode head = null;
        if (node == null) {
            return head;
        }
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hashMap =
                new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        head = new UndirectedGraphNode(node.label);
        hashMap.put(node, head);
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            for (UndirectedGraphNode neighbor : cur.neighbors) {
                if (!hashMap.containsKey(neighbor)) {
                    UndirectedGraphNode copyNode = new UndirectedGraphNode(neighbor.label);
                    queue.offer(neighbor);
                    hashMap.put(neighbor, copyNode);
                }
                hashMap.get(cur).neighbors.add(hashMap.get(neighbor));
            }
        }
        return head;
    }
}
