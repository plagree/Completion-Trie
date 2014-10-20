package org.completion.trie.structure;

public abstract class VisitorImpl<R> implements Visitor<R> {

    protected R result;
    
    
	public VisitorImpl() {
		this.result = null;
	}
	
	public VisitorImpl(R initialValue) {
		this.result = initialValue;
	}
	
	public R getResult() {
		return result;
	}

	abstract public void visit(String key, RadixTreeNode parent, RadixTreeNode node);

}
