package org.lri.oak.structure;

public class DuplicateKeyException extends RuntimeException
{
	private static final long serialVersionUID = 3141795907493885706L;

	public DuplicateKeyException(String msg)
	{
		super(msg);
	}
}
