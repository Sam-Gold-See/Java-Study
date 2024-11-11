package software.note;

public class Note {
	private String title;
	private String content;

	public Note() {
	}

	public Note(String line) {
		String[] data = line.split("&");
		this.title = data[0].split("=")[1];
		this.content = data[1].split("=")[1];
	}

	public Note(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "title=" + title + "&content=" + content;
	}
}
