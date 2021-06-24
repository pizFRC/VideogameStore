package application.model;


import java.io.*;

import javafx.scene.image.Image;




public class imageReader {

	public imageReader() {
		
	}
	
	
	public byte[] read(String path) throws Exception {
		File image=new File(path);
		   FileInputStream input=new FileInputStream(image);
		   byte[] array =    input.readAllBytes();
	        // convert BufferedImage to byte[]
	       return array;
	}
	public Image byteToImg(byte[]array) throws Exception{
		InputStream in = new ByteArrayInputStream(array);
		Image img = new Image(in);
		
	       
		return img;
		
	}
	public static void main(String[] args) {
		
	}
}
