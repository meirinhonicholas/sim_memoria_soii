
// region [Imports]
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
// endregion [Imports]

public class Logger {
	// region [Attributes]
	private int totalProcess;
	private int runnedProcess;
	private long executedTime;
	private List<Long> timesProcesses;
	private int multiprogramLevel;
	// endregion [Attributes]

	public Logger(int totalProcess) {
		this.totalProcess = totalProcess;
		this.runnedProcess = 0;
		this.executedTime = 0;
		this.timesProcesses = new ArrayList<Long>();
		this.multiprogramLevel = 0;
	}

	// region [Getters and Setters]
	public static List<String> getUnique(Frame[][] frames) {
		Map<String, Integer> map = new LinkedHashMap<>();
	
		for (Frame[] row : frames)
			for (Frame col : row)
				map.put(col.getProcess(), map.getOrDefault(col, 0) + 1);
	
		List<String> unique = new ArrayList<>();
	
		for (Map.Entry<String, Integer> entry : map.entrySet())
			unique.add(entry.getKey());
	
		return unique;
	}

	public int getPartitions(Frame[][] frames) {
		List<String> partitions = getUnique(frames);
		return (int) partitions.size();
	}

	public int getUnrunnedProcess() {
		return this.totalProcess - this.runnedProcess;
	}

	public int getTotalProcess() {
		return this.totalProcess;
	}

	public void setTotalProcess(int totalProcess) {
		this.totalProcess = totalProcess;
	}

	public List<Long> getTimesProcesses() {
		return this.timesProcesses;
	}

	public void addTimesProcesses(long time) {
		this.timesProcesses.add(time);
	}

	public int getRunnedProcess() {
		return this.runnedProcess;
	}

	public void setRunnedProcess(int runnedProcess) {
		this.runnedProcess = runnedProcess;
	}

	public long getExecutedTime() {
		return this.executedTime;
	}

	public void setExecutedTime(long executedTime) {
		this.executedTime = executedTime;
	}

	public long getMeanTimePerProcess() {
		return this.executedTime / this.timesProcesses.size();
	}

	public int getMultiprogramLevel() {
		return this.multiprogramLevel;
	}

	public void setMultiprogramLevel(int multiprogramLevel) {
		if (multiprogramLevel > this.multiprogramLevel) {
			this.multiprogramLevel = multiprogramLevel;
		}
	}
	// endregion [Getters and Setters]

}