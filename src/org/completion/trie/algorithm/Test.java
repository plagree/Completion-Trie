package org.completion.trie.algorithm;

import org.completion.trie.structure.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RadixTreeImpl patricia = new RadixTreeImpl();
		patricia.insert("ab", (float)4.0);
		patricia.insert("b", (float)2.0);
		patricia.insert("bba", (float)1.0);
		patricia.insert("caca", (float)3.0);
		patricia.insert("caccc", (float)1.0);
		patricia.insert("cbac", (float)2.0);
		patricia.insert("cbba", (float)1.0);
		patricia.insert("c", (float)10.0);
		patricia.insert("cb", (float)0.5);
		patricia.insert("ddddddd", (float)1.0);
		patricia.insert("ddaaa", (float)2.0);
		patricia.insert("ddddddde", (float)3.0);
		patricia.insert("d", (float)4.0);
		patricia.insert("cacab", (float)15.0);

		patricia.display();

		RadixTreeNode pf = patricia.searchPrefix("c");
		System.out.println(pf.getKey()+" : key\n"+pf.getValue()+" : value\n"+pf.getChildren().size()+" : size");

	}

}
