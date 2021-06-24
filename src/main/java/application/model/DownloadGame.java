package application.model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

public class DownloadGame extends Service<Void> {
  private String urlText;
private String produttore;
private String nome;
private Game g;
	public String getUrlText() {
	return urlText;
}
public void setUrlText(String urlText) {
	this.urlText = urlText;
}


private static void downloadUsingStream(String urlStr, String file) throws IOException{
    URL url = new URL(urlStr);
    BufferedInputStream bis = new BufferedInputStream(url.openStream());
    FileOutputStream fis = new FileOutputStream(new File(file));
    byte[] buffer = new byte[1024];
    int count=0;
    while((count = bis.read(buffer,0,1024)) != -1)
    {
    	
        fis.write(buffer, 0, count);
    }
    fis.close();
    bis.close();
}
	@Override
	protected Task<Void> createTask() {
        return new Task<Void>() {
			

			@Override
			protected Void call() throws Exception {
				System.out.println( System.getProperty("user.home") +'/'+nome+".jar");
				//downloadUsingStream("https://drive.google.com/uc?export=download&id=1xBENrc3UCEbLuCAumz0i6yDwufjISBqT", System.getProperty("user.home") +'/'+nome+".jar");
				  URL url = new URL(urlText);
				    BufferedInputStream bis = new BufferedInputStream(url.openStream());
				    FileOutputStream fis = new FileOutputStream(new File(System.getProperty("user.home") +'/'+nome+".jar"));
				    byte[] buffer = new byte[1024];
				    int count=0;
				   int size=0;
				   HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
				  
				   long removeFileSize = httpConnection.getContentLengthLong();
				    System.out.println("size+"+size);
				    long	size1=removeFileSize;
				    int i=0;
				    while((count = bis.read(buffer,0,1024)) != -1)
				    {   
				 
				    	System.out.println(size1);
				    	System.out.println("nel while size"+size);
				   
				    	updateProgress(i,size1);
				    	i++;
				        fis.write(buffer, 0, count);
				    }
				    
				    	 
				    
				    fis.close();
				    bis.close();
				
				/*
						String file=System.getProperty("user.home")+"/provadownload.jar";
						System.out.println(" qui -> "+file);
					System.out.println("sito:"+urlText);
					System.out.println("sito:"+g.getUrlDownload());
			//	downloadUsingStream(urlText,System.getProperty("user.home")+"/prova3anima.jar");
				 URL url = new URL(urlText);
				  BufferedInputStream bis = new BufferedInputStream(url.openStream());
				    FileOutputStream fis = new FileOutputStream(new File(System.getProperty("user.home")+"/provadownload.jar"));
				    byte[] buffer = new byte[1024];
				    int count=0;
				    int i=0;
				    int size=bis.available();
				    while((count = bis.read(buffer,0,1024)) != -1)
				    {
				    	updateProgress(i,size);
				    	i+=1;
				        fis.write(buffer, 0, count);
				    }
				    fis.close();
				    bis.close();*/
				    
				    	
		/*		URL url=new URL(urlText);
				BufferedReader reader=new BufferedReader(new InputStreamReader( url.openStream()));
				StringBuilder builder=new StringBuilder();
				while(reader.ready()) {
					String line=reader.readLine();
					builder.append(line);
				}
				reader.close();
                 BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(System.getProperty("user.home")+"/prova3.txt")));
				 
				 //write contents of StringBuffer to a file
				 bwr.write(builder.toString());
				 
				 //flush the stream
				 bwr.flush();
				 
				 //close the stream
				 bwr.close();
				return null ;*/
				return null;
			}
		
		};
	}
	public void setProduttore(String produttore) {
		this.produttore=produttore;
		
	}
	public void setNomeGame(String nome) {
		this.nome=nome;
		
	}

}
