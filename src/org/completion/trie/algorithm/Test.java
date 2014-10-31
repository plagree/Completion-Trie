package org.completion.trie.algorithm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.completion.trie.structure.*;

public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RadixTreeImpl patricia = new RadixTreeImpl();
		/**
		 * SMALL TESTS
		 */ 
		/*patricia.insert("ab", (float)4.0);
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
		
		System.out.println(patricia.searchPrefix("TFBJP").getWord());
		
		System.out.println(patricia.find("cacab"));
		System.out.println(patricia.find("d"));
		System.out.println(patricia.find("cb"));
		
		System.out.println(patricia.searchPrefix("cacab").getValue());
		System.out.println(patricia.searchPrefix("d").getValue());
		System.out.println(patricia.searchPrefix("cb").getValue());*/
		
		patricia.insert("TFBj", (float)7.0);
		patricia.insert("TFBJ", (float)8.0);
		patricia.insert("TFBUSA", (float)3.0);
		patricia.insert("TFB", (float)152.0);
		patricia.insert("TFB_VIP", (float)1.0);
		patricia.insert("TFBPH", (float)4.0);
		patricia.insert("TFB_TeamFollow", (float)2.0);
		patricia.insert("TFBINA", (float)4.0);
		patricia.insert("TFBFI", (float)1.0);
		patricia.insert("TFBjp", (float)2.0);
		patricia.insert("TFBJp", (float)4.0);
		patricia.insert("TFBJP", (float)103.0);
		
		patricia.display();

		RadixTreeNode pf = patricia.searchPrefix("TFBJ");
		System.out.println(pf.getKey()+" : key,\t"+pf.getValue()+" : value,\t"+pf.getChildren().size()+" : size,\t"+pf.getBestDescendant().getKey()+" : best descendant");
		
		pf = patricia.searchPrefix("TFBJP");
		System.out.println(pf.getKey()+" : key,\t"+pf.getValue()+" : value,\t"+pf.getChildren().size()+" : size,\t"+pf.getBestDescendant().getKey()+" : best descendant");
		
		pf = patricia.searchPrefix("TFBjp");
		System.out.println(pf.getKey()+" : key,\t"+pf.getValue()+" : value,\t"+pf.getChildren().size()+" : size,\t"+pf.getBestDescendant().getKey()+" : best descendant");
		
		pf = patricia.searchPrefix("TFBJp");
		System.out.println(pf.getKey()+" : key,\t"+pf.getValue()+" : value,\t"+pf.getChildren().size()+" : size,\t"+pf.getBestDescendant().getKey()+" : best descendant");
		
		pf = patricia.searchPrefix("TFBj");
		System.out.println(pf.getKey()+" : key,\t"+pf.getValue()+" : value,\t"+pf.getChildren().size()+" : size,\t"+pf.getBestDescendant().getKey()+" : best descendant");
		
		//pf.getBestDescendant().setValue((float)0.5);
		//patricia.display();
		//pf.getBestDescendant().updatePreviousBestValue();
		//pf = patricia.searchPrefix("dd");
		//System.out.println(pf);
		//System.out.println(pf.getKey()+" : key\n"+pf.getValue()+" : value\n"+pf.getChildren().size()+" : size\n"+pf.getBestDescendant().getKey()+" : best descendant");
		
		/**
		 * BIG TEST WITH TWITTER DUMP
		 *
		
		Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
		String url = "jdbc:postgresql://localhost/twitter";
        String user = "paul";
        String password = "Paul";
        
        long time = System.nanoTime();
        
        try {
            con = DriverManager.getConnection(url, user, password);
            
            String stm = "SELECT tag, max(docs.num) AS num FROM docs GROUP BY tag";
            pst = con.prepareStatement(stm);
            rs = pst.executeQuery();
            while(rs.next()) {
            	patricia.insert(rs.getString(1), rs.getFloat(2));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Test.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Test.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        long endTime = System.nanoTime();
        long trieInit = (endTime - time)/1000000000;
        
        System.out.println("Completion trie construction: "+trieInit+" s.");
        
        if (patricia != null) {
        	RadixTreeNode pf = patricia.searchPrefix("");
        	System.out.println(pf.getKey()+" : key\n"+pf.getValue()+" : value\n"+pf.getChildren().size()+" : size\n"+pf.getBestDescendant().getKey()+" : best descendant");
        }*/
    }
}
