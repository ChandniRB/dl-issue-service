package demo.model;

public class ApiResponseParams {
    private String resmsgid;
	private String msg;
	private String err;
	private String status;
	private String errmsg;
    
	public String getResmsgid() {
		return resmsgid;
	}
	public void setResmsgid(String resmsgid) {
		this.resmsgid = resmsgid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msgid) {
		this.msg = msgid;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
