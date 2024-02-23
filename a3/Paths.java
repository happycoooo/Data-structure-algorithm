package assignment3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Paths {
    public static class Edge{
        int u;
        int v;
        int w;
        public Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }

        //获取边上的一个点
        public int either(){
            return u;
        }

        //获取边上除了vertex以外的另一个顶点
        public int other(int vertex){
            if(vertex == u){
                return v;
            }else{
                return u;
            }
        }
    }

    public static class EdgeWeightedGraph{
        private int V;
        private int E;
        private Deque<Edge>[] adjList;

        public EdgeWeightedGraph(int V){
            this.V = V;
            this.E = 0;
            this.adjList = new Deque[V];
            for(int i = 0 ; i < adjList.length; i++){
                adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(Edge e){
            int u = e.either();
            int v = e.other(u);
            adjList[u-1].offer(e);
            adjList[v-1].offer(e);
        }
    }

    public static int findPaths(EdgeWeightedGraph g, int num) {
        int pathNumber = 0;
        Stack<Integer> path = new Stack<>();
        path.push(1);
        while(g.adjList[0].size() != 0 ){ //循环从1出发的所有路径
            int sum= 0;
            sum += g.adjList[path.peek()-1].peek().w;//sum加上第一个结点的权重
            int current = g.adjList[0].pop().other(1);
            path.push(current);
            int lastNumber = 1;//用于记录上一个结点的数字
            while(path.size() != 1 ){ //每条路上循环遍历找路径
                //去除与前节点连接的边
                if(g.adjList[current-1].size() == 0){
                    path.pop();
                    current = path.peek();
                } else if(g.adjList[current-1].peek().other(current) == lastNumber){
                    g.adjList[current-1].pop();
                }else{
                    lastNumber = current;
                    assert g.adjList[path.peek()-1].peek() != null;
                    int now = g.adjList[path.peek()-1].peek().other(current);
                    path.push(now);
                    int weight = g.adjList[current-1].pop().w;
                    sum += weight;
                    current = now;
                    while(g.adjList[now-1].size() == 1){
                        if(sum == num){//当前结点为尾节点
                            pathNumber++;
                            path.pop();
                            current = path.peek();
                            lastNumber = 1;
                            sum = sum - weight;
                            g.adjList[now-1].pop();
                        }else{
                            current = path.pop();//8
                            sum = sum - weight;
                            lastNumber = path.peek();//7
                            path.push(current);
                            g.adjList[now-1].pop();
                        }
                    }
                }
            }
        }
        return pathNumber;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改文件路径
        File input = new File("src/test_data/Q2/B2.in");
        if (!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int n = in.nextInt(); //the number of tree nodes
        int num = in.nextInt();// the target number
        EdgeWeightedGraph g = new EdgeWeightedGraph(n);
        g.V = n;
        for (int i = 0; i < n-1 ; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            Edge edge = new Edge(u,v,w);
            g.addEdge(edge);
        }
        int pathNmuber = findPaths(g,num);
        System.out.println(pathNmuber);
        File output = new File("src/test_data/Q2/B2.out");
        in = new Scanner(output);
        boolean flag = in.nextInt() == pathNmuber;
        System.out.println(flag);
    }

}
