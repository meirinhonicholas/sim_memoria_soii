public class Frame {
	// region [Attributes]
	private boolean available;
	private String process;
	// endregion [Attributes]

	public Frame() {
		available = true;
	}

	// region [Getters and Setters]
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getProcess() {
		return this.process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	// endregion [Getters and Setters]
}
