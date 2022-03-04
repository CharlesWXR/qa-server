package edu.njnu.qaserver.exception;

import edu.njnu.qaserver.enums.ResultCode;
import edu.njnu.qaserver.pojo.Result;

public class BizException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	protected Result result;

	private BizException(final String message) {
		super(message);
	}

	private BizException(final String message, final Throwable cause) {
		super(message, cause);
	}

	private BizException(ResultCode resultCode, final String message, final Throwable cause) {
		super(message, cause);
		result = new Result(resultCode, null);
	}

	public static BizException getInstance(final String message) {
		return new BizException(message);
	}

	public static BizException getInstance(final String message, final Throwable cause) {
		return new BizException(message, cause);
	}

	public static BizException getInstance(final ResultCode resultCode, final String message, final Throwable cause) {
		return new BizException(resultCode, message, cause);
	}

	public Result getResult() {
		return result;
	}
}
