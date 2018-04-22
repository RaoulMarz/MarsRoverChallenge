package roverframework;

public enum ReportCategory {
	REPORT_NONE, REPORT_INPUT, REPORT_OUTPUT, REPORT_ALL;

	public String toString() {
		switch (this) {
		case REPORT_NONE: {
			return "none";
		}
		case REPORT_INPUT: {
			return "input";
		}
		case REPORT_OUTPUT: {
			return "output";
		}
		case REPORT_ALL: {
			return "all";
		}
		default: {
			return "";
		}
		}
	}
};