// region [Imports]
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
// endregion [Imports]

public class Allocator implements Runnable {
	// region [Attributes]
	private Memory memory;
	private List<Process> processes;
	// endregion [Attributes]

	public Allocator(Memory memory, List<Process> processes){
		this.memory = memory;
		this.processes = processes;
	}

	// region [Getters]
	public Memory getMemory() {
		return this.memory;
	}
	// endregion [Getters]

	// region [Thread Runner]
	@Override
	public void run(){
		Iterator<Process> iter = processes.iterator();

		while(iter.hasNext()) {
			Process process = iter.next();
			Dictionary<String, Boolean> response = memory.allocFirstFit(process);
			if (response.get("Fits")) {
				if (response.get("Allocated")) {
					memory.getLogger().setMultiprogramLevel(Thread.activeCount());
					iter.remove();
				} else {
					memory.getLogger().setMultiprogramLevel(Thread.activeCount());
				}
			} else {
				iter.remove();
			}
		}
	}
	// endregion [Thread Runner]
}
