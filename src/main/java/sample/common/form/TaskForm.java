package sample.common.form;

public class TaskForm {
    private String title;
    private String contents;
    private String name;
    private String startDate;
    private String endDate;


    public String getTitle() { 
    	return title;
    	}
    
    public String getContents() {
    	return contents; 
    	}
    
    public String getName() {
    	return name; 
    	}
    
    public String getStartDate() {
    	return startDate;
    	}
    
    public String getEndDate() {
    	return endDate; 
    	}
    
    
    
    public void setTitle(String title) {
    	this.title = title; 
    	}
    
    public void setContents(String contents) {
    	this.contents = contents;
    	}
    
    public void setName(String name) {
    	this.name = name;
    	}

    public void setStartDate(String startDate) { 
    	this.startDate = startDate;
    	}
    
    public void setEndDate(String endDate) {
    	this.endDate = endDate;
    	}
}