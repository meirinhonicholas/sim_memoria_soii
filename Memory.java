// region [Imports]
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
// endregion [Imports]

public class Memory{
	// region [Attributes]
	private int rows;
	private int columns;
	private Frame[][] frames, lastView;
	private Logger logger;
	private Printers printer;
	private List<ExecuteProcess> executors = new ArrayList<ExecuteProcess>();
	private static ForkJoinPool execute = new ForkJoinPool();
	// endregion [Attributes]
	
	// region [Getters]
	public Frame[][] getLastView() {
		return this.lastView;
	}

	public Logger getLogger() {
		return this.logger;
	}
	
	public void waitToExecuteAllProcess() {
		executors.forEach(ForkJoinTask::join);
	}
	// endregion [Getters]

	public Memory(int rows, int columns, Logger logger, Printers printer) {
		this.rows = rows;
		this.columns = columns;
		this.logger = logger;
		this.printer = printer;

		frames = new Frame[rows][columns];
		lastView = new Frame[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				frames[i][j] = new Frame();
				lastView[i][j] = new Frame();
			}
		}
	}

	// region [Public Methods]
		// region [Print Memory]
	public void printMemory() {
		printer.table(rows, columns, frames);
	}
		// endregion [Print Memory]

	// region [Print Memory]
	public synchronized Dictionary<String, Boolean> allocFirstFit(Process process) {
		/* Try to alloc a process into the memory by First Fit algorithmw
		 */
		Dictionary<String, Boolean> response = new Hashtable<String, Boolean>();
		if (process.getSize() > rows * columns) {
			response.put("Fits", false);
			response.put("Allocated", false);
			return response;
		} else {
			int firstIndexToAlloc = firstBlockAvailable(process.getSize());
			boolean alocado = alloc(firstIndexToAlloc, process);
			response.put("Fits", true);
			response.put("Allocated", alocado);
			return response;
		}
	}

	public synchronized void dealloc(int firstBlockAvailable, Process process) {
		/* Dealloc the process from the memory
		 */
		int currentRow = firstBlockAvailable / columns;
		int currentColumn = firstBlockAvailable - (currentRow * columns);

		for (int i = 0; i < process.getSize(); i++) {
			frames[currentRow][currentColumn].setAvailable(true);
			frames[currentRow][currentColumn].setProcess(null);
			currentColumn++;
			if (currentColumn >= columns) {
				currentRow++;
				currentColumn = 0;
			}
		}
	}
	// endregion [Public Methods]

	// region [Private Methods]
	private int firstBlockAvailable(int processSize) {
		/* Find the first block available for the process
		 */
		int totalFrames = rows * columns;

		int blockSize = 0, blockStart = 0;

		for (int i = 0; i < totalFrames; i++) {
			int currentRow = i / columns;
			int currentColumn = i - (currentRow * columns);
			if (frames[currentRow][currentColumn].isAvailable()) {
				blockSize++;

				if (blockSize >= processSize) {
					return blockStart;
				}
			} else {
				blockStart = i + 1;
				blockSize = 0;
			}
		}
		return -1;
	}

	private synchronized boolean alloc(int firstBlockAvailable, Process process) {
		/* Alloc the process after find the first block avaiable for the process
		 */
		if (firstBlockAvailable == -1) {
			return false;
		} else {

			int currentRow = firstBlockAvailable / columns;
			int currentColumn = firstBlockAvailable - (currentRow * columns);

			for (int i = 0; i < process.getSize(); i++) {
				lastView[currentRow][currentColumn].setAvailable(false);
				lastView[currentRow][currentColumn].setProcess(process.getProcessId());
				frames[currentRow][currentColumn].setAvailable(false);
				frames[currentRow][currentColumn].setProcess(process.getProcessId());
				currentColumn++;
				if (currentColumn >= columns) {
					currentRow++;
					currentColumn = 0;
				}
			}

			ExecuteProcess executor = new ExecuteProcess(this, firstBlockAvailable, process);
			execute.execute(executor);
			executors.add(executor);

			this.getLogger().setRunnedProcess(this.getLogger().getRunnedProcess() + 1);
			this.getLogger().addTimesProcesses(process.getTime());
			
			return true;
		}
	}
	// endregion [Private Methods]
}
