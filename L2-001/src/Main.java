import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Temp {
        int min, node;

        Temp(int a, int b) {
            min = a;
            node = b;
        }
    }
    static int[] p;
    static int n;
    static Node node[];
    static int[] ans;
    static LinkedList<Integer> list[];
    static class com implements Comparator<Temp> {

        public int compare(Temp a, Temp b) {
            // TODO Auto-generated method stub
            if (a.min < b.min)
                return -1;
            if (a.min > b.min)
                return 1;
            return 0;
        }
    }

    static class Node {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        public void add(int key, int val) {
            map.put(key, val);
        }
    }

    static class Task {
        int counter=0;
        int fs=0;
        String fp="";
        static final int MAX=999999999;
        void dfs(int start, int end, String path,int sum){
            if(end==start){
                counter++;
                if(sum>fs){
                    fs=sum;
                    fp=path;
                }

            }
            Iterator<Integer> it=list[end].iterator();
            while(it.hasNext()){
                int now=it.next();
                dfs(start,now,now+" "+path,sum+p[now]);

            }
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {

            n=in.nextInt();
            int m=in.nextInt();
            int s=in.nextInt();
            int d=in.nextInt();

            list=new LinkedList[n];
            for(int i=0;i<n;i++){
                list[i]=new LinkedList<Integer>();
            }

            node=new Node[n];
            ans=new int[n];

            p=new int[n];
            for(int i=0;i<n;i++){
                p[i]=in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                node[i] = new Node();
            }
            for(int i=0;i<m;i++){
                int a=in.nextInt();
                int b=in.nextInt();
                int c=in.nextInt();
                node[a].add(b,c);
                node[b].add(a,c);
            }
            PriorityQueue<Temp> queue = new PriorityQueue<Temp>(502,new com());

            queue.add(new Temp(0, s));
            Arrays.fill(ans, MAX);
            ans[s] = 0;

            while (!queue.isEmpty()) {
                Temp t = queue.poll();
                int v = t.node;
                if (ans[v] < t.min)
                    continue;
                for (Map.Entry<Integer, Integer> entry : node[v].map.entrySet()) {
                    int to = entry.getKey();
                    int cost = entry.getValue();

                    if (ans[to] > ans[v] + cost) {
                        ans[to] = ans[v] + cost;
                        queue.add(new Temp(ans[to], to));
                        list[to]=new LinkedList<Integer>();
                        list[to].add(v);

                    }else if(ans[to]==ans[v]+cost){
                        list[to].add(v);
                    }

                }
            }

//            for(int i=0;i<n;i++){
//                if(ans[i]==MAX)
//                    continue;
//                list[i].add(d);
//            }

//            out.println(ans[d]);
            dfs(s,d,""+d,p[d]);


//            for(int i=0;i<n;i++){
//                Iterator<Integer> it=list[i].iterator();
//                while(it.hasNext()){
//                    out.print(it.next()+" ");
//                }
//                out.println();
//            }
            out.println(counter+" "+fs);
            out.println(fp);
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

    }
}