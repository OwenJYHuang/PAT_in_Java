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

	static class Task {

		class Node {
			Node(int val, String next) {
				this.val = val;
				this.next = next;
			}

			int val;
			String next;
		}

		class LL {
			LL(String address, int val) {
				this.val = val;
				this.address = address;
			}

			String address;
			int val;

		}

		public void solve(int testNumber, InputReader in, PrintWriter out) {
			LinkedList<LL> a = new LinkedList<LL>();
			LinkedList<LL> b = new LinkedList<LL>();
			String init = in.next();
			int n = in.nextInt();
			HashMap<String, Node> map = new HashMap<String, Node>();
			HashSet<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < n; i++) {
				map.put(in.next(), new Node(in.nextInt(), in.next()));
			}
			Node temp;
			while (!map.isEmpty()) {
				temp = map.remove(init);
				if (set.contains(Math.abs(temp.val))) {
					b.add(new LL(init, temp.val));
					init = temp.next;
				} else {
					set.add(Math.abs(temp.val));
					a.add(new LL(init, temp.val));
					init = temp.next;
				}
			}
			LL node;
			if(a.size()>0){
				Iterator<LL> it=a.iterator();
				node=  it.next();
				out.print(node.address+" "+node.val);
				while(it.hasNext()){
					node= it.next();
					out.println(" "+node.address);
					out.print(node.address+" "+node.val);
				}
				out.println(" -1");
			}
			
			if(b.size()>0){
				Iterator<LL> it=b.iterator();
				node=  it.next();
				out.print(node.address+" "+node.val);
				while(it.hasNext()){
					node=it.next();
					out.println(" "+node.address);
					out.print(node.address+" "+node.val);
				}
				out.println(" -1");
			}
			
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