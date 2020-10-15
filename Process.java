public class Process {
	// region [Attributes]
	private String processId;
	private int size;
	private long time;
	// endregion [Attributes]

	public Process (String processId, int size, long time){
		this.processId = processId;
		this.size = size;
		this.time = time;
	}

	// region [Getters and Setters]
	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTime() {
		return this.time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	// endregion [Getters and Setters]

}
