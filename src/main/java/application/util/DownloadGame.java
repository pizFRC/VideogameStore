package application.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import application.model.Game;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class DownloadGame extends Service<Void> {
  private String urlText;

private Game g;
private String filePath;
private int fileSize;
	public String getUrlText() {
	return urlText;
}
public void setUrlText(String urlText) {
	this.urlText = urlText;
	 URL url;
	try {
		url = new URL(urlText);
		 HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		    fileSize =   httpConnection.getContentLength();
	} catch (Exception e) {
	System.err.println("errore link");
	}
	
}



	@Override
	protected Task<Void> createTask() {
        return new Task<Void>() {
			

			@Override
			protected Void call() throws Exception {
				
		    	
            	
			
				  URL url = new URL(urlText);
				    BufferedInputStream bis = new BufferedInputStream(url.openStream());
				    FileOutputStream fis = new FileOutputStream(new File(filePath +File.separator+g.getNome()+".jar"));
				    byte[] buffer = new byte[1024];
				    int count=0;
				   
					  
				 
				   updateMessage("Download in corso");
				    int i=0;
				    while((count = bis.read(buffer,0,1024)) != -1)
				    {   
				 
				
				    	
				    	i+=count;
				    		
				        fis.write(buffer, 0, count);
					    
			    	updateProgress(i,fileSize);
				      Thread.sleep(100);
				    }
				    
				    updateMessage("Download completato");
				    	
				  
				    fis.close();
				    bis.close();
	
				return null;
			}
		
		};
	}

	
	public String getSize() {
		
		return String.valueOf(fileSize);
	}
	public Game getG() {
		return g;
	}
	public void setGame(Game g) {
		this.g=g;
		
	}
	public void setFilePath(String path) {
		filePath=path;
		
	}

}
