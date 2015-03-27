package org.lri.oak.structure;

import org.junit.Assert;
import org.junit.Test;

public class RadixTreeImplTest {
	
	private static final double DELTA = 1e-15;
	
	@Test
	public void testInsert() {
		RadixTreeImpl patricia = new RadixTreeImpl();
		patricia.insert("a", (float)4.0);
		Assert.assertEquals(patricia.size, 1);
	}
	
	@Test
	public void testInsertExisting() {
		RadixTreeImpl patricia = new RadixTreeImpl();
		patricia.insert("a", (float)4.0);
		Assert.assertEquals(patricia.size, 1);
		try {
		patricia.insert("a", (float)5.0);
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "Duplicate key: 'a'");
		}
		Assert.assertEquals(patricia.size, 1);
	}

	/*
	 * Tests of the method find(key)
	 * Works only for exact matches, use searchPrefix otherwise 
	 */
	@Test
	public void testFind() {
		RadixTreeImpl patricia = new RadixTreeImpl();
		patricia.insert("a", (float)4.0);
		Assert.assertEquals(patricia.find("a"), 4, DELTA);
		patricia.insert("ab", (float)5.0);
		Assert.assertEquals(patricia.find("a"), 4, DELTA);
		Assert.assertEquals(patricia.searchPrefix("a", false).getValue(), 5, DELTA);
		Assert.assertEquals(patricia.searchPrefix("a", true).getValue(), 4, DELTA);
	}
	
	@Test
	public void testValues() {
		RadixTreeImpl patricia = new RadixTreeImpl();
		patricia.insert("a", (float)4.0);
		Assert.assertEquals(patricia.size, 1);
		try {
		patricia.insert("a", (float)5.0);
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "Duplicate key: 'a'");
		}
		Assert.assertEquals(patricia.size, 1);
	}
	
	@Test
	public void testDelete() {
		RadixTreeImpl patricia = new RadixTreeImpl();
		patricia.insert("a", (float)4.0);
		patricia.delete("a");
		Assert.assertEquals(patricia.size, 0);
	}
	
	@Test
	public void testSearchPrefix() {
		RadixTreeImpl patricia = new RadixTreeImpl();
		patricia.insert("caca", (float)3.0);
		patricia.insert("caccc", (float)1.0);
		patricia.insert("cbac", (float)2.0);
		patricia.insert("cbba", (float)1.0);
		patricia.insert("cac", (float)1.0);
		patricia.insert("c", (float)10.0);
		
		Assert.assertEquals(patricia.searchPrefix("cac", false).getWord(), "cac");
		Assert.assertEquals(patricia.searchPrefix("c", false).getWord(), "c");
		Assert.assertEquals(patricia.searchPrefix("ca", false).getWord(), "cac");
		Assert.assertEquals(patricia.searchPrefix("cac", false).getValue(), 3, DELTA); // search for prefix
		Assert.assertEquals(patricia.searchPrefix("cac", true).getValue(), 1, DELTA);  // search for existing word
	}
	
	@Test
	public void testSerieInsertsDeletes() {
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
		patricia.insert("TFBJp", (float)2.0);
		patricia.insert("TFBJP", (float)4.0);
		patricia.insert("d", (float)4.0);
		patricia.insert("cacab", (float)15.0);
		
		Assert.assertEquals(patricia.size, 16);
		Assert.assertEquals(patricia.searchPrefix("", false).getValue(), 15, DELTA);
		Assert.assertEquals(patricia.searchPrefix("", false).getBestDescendant().getWord(), "cacab");
		
		patricia.delete("cacab");
		Assert.assertEquals(patricia.searchPrefix("", false).getValue(), 10, DELTA);
		Assert.assertEquals(patricia.getSize(), 15);
		
		patricia.insert("ca", 20);
		Assert.assertEquals(patricia.searchPrefix("ca", false).getWord(), "ca");
		Assert.assertEquals(patricia.searchPrefix("", false).getValue(), 20, DELTA);
		Assert.assertEquals(patricia.searchPrefix("TFBJ", false).getValue(), 4, DELTA);
		Assert.assertEquals(patricia.searchPrefix("ddddd", false).getValue(), 3, DELTA);
		Assert.assertEquals(patricia.getSize(), 16);
		
		patricia.delete("ddaaa");
		patricia.delete("ddddddde");
		patricia.delete("TFBJp");
		Assert.assertEquals(patricia.size, 13);
		Assert.assertEquals(patricia.searchPrefix("ddddd", false).getValue(), 1, DELTA);
	}

	@Test
	public void testUpdateValues() {
 
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
		patricia.insert("TFBJp", (float)2.0);
		patricia.insert("TFBJP", (float)4.0);
		patricia.insert("d", (float)4.0);
		patricia.insert("cacab", (float)15.0);
		
		Assert.assertEquals(patricia.searchPrefix("TFBJP", false).getWord(), "TFBJP");

		RadixTreeNode node = patricia.searchPrefix("TF", true);
		Assert.assertEquals(node.getWord(), "TFBJ");
		Assert.assertEquals(node.getValue(), 4, DELTA);
		Assert.assertEquals(node.getBestDescendant().getWord(), "TFBJP");
		
		node.getBestDescendant().updatePreviousBestValue((float)0.5);
		Assert.assertEquals(patricia.searchPrefix("TF", false).getValue(), 2, DELTA);
		Assert.assertEquals(patricia.searchPrefix("TF", false).getBestDescendant().getWord(), "TFBJp");
	}
	
	@Test
	public void testContains() {
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
		patricia.insert("cbbb", (float)0.5);
		
		Assert.assertEquals(patricia.contains("ab"),true);
		Assert.assertEquals(patricia.contains("b"),true);
		Assert.assertEquals(patricia.contains("bba"),true);
		Assert.assertEquals(patricia.contains("caca"),true);
		Assert.assertEquals(patricia.contains("caccc"),true);
		Assert.assertEquals(patricia.contains("cbba"),true);
		Assert.assertEquals(patricia.contains("c"),true);
		Assert.assertEquals(patricia.contains("cb"),true);
		Assert.assertEquals(patricia.contains("cbbb"),true);
		
		Assert.assertEquals(patricia.contains("cac"),false);
		Assert.assertEquals(patricia.contains("cbb"),false);
	}

}
