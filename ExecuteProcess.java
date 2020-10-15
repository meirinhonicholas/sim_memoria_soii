// region [Imports]
import java.util.concurrent.RecursiveAction;
// endregion [Imports]

public class ExecuteProcess extends RecursiveAction {
	// region [Attributes]
	private static final long serialVersionUID = 1L;	
	private Memory memory;
	private int firstIndexToAlloc;
	private Process process;
	// endregion [Attributes]

	public ExecuteProcess(Memory memory, int firstIndexToAlloc, Process process) {
		this.memory = memory;
		this.firstIndexToAlloc = firstIndexToAlloc;
		this.process = process;
		
	}

	@Override
	protected void compute() {
		try {
			Thread.sleep(process.getTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		memory.dealloc(firstIndexToAlloc, process);
	}

}
