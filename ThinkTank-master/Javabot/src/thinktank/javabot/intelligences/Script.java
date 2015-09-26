package thinktank.javabot.intelligences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import thinktank.javabot.graphics.GraphicInterface;


/**
*
* @author Gabriel
*/


public class Script {

	private String instructions = "";
	private String filename;
	private int currentLine = 0;
	private String tmpFileName;
	private String  tmpFileContent = "";
	private String output; /* Sortie telle qu'elle est affichée dans l'inferface graphique */
	private Writer JVBLayerOutput = new StringWriter(); /*Redirection de la sortie standard, composée de la surcouche Javabot (numéros de lignes)*/
	public Script(String filename)
	{
		this.filename = filename;
		importInstructionsFromFile(this.filename);
		generateTmpFileName();
		//System.out.println("generated file: "+tmpFileName);
		//System.out.println("Content:  "+tmpFileContent);
		writeTmpFile();
	/*	watchOutput = new WatchOutput(this);
		watchOutput.start();*/
	}
	
	public Writer getJVBLayerOutput()
	{
		return JVBLayerOutput;
	}

	
	public String getInstructions()
	{
		return instructions;
	}
	
	public String getOutput()
	{
		return output;
	}
	
	public void setCurrentLine(int line)
	{
		currentLine = line;
	}
	
	public int getCurrentLine()
	{
		return currentLine;
	}
	
	public String getTmpFileName()
	{
		return tmpFileName;
	}
	
	/*public void convertJVBLayerOutPutToOutPut()
	{
		System.out.println("JVB ici");
		String buff = JVBLayerOutput.toString();
		
		String lines[] = buff.split("\\n");
		for (int i = 0; i < lines.length; i++)
		{
			System.out.println("JVB: "+lines[i]);
			if (lines[i].length() > 9 && lines[i].substring(0, 9).equals("<lineJVB>"))
			{
				String strCurrentLineTmp = lines[i].substring(9);
				currentLine = Integer.parseInt(strCurrentLineTmp) - (Integer.parseInt(strCurrentLineTmp) / 2);
				
			}
			else
			{
				output += lines[i]+'\n';
			}
			//System.out.println("l: "+lines[i]);
			//currentLine = Integer.parseInt(lines[i]) - (Integer.parseInt(lines[i]) / 2);
			
		}
	}*/
	
	/* Génère un nom aléatoire pour le fichier .py temporaire */
	private void generateTmpFileName()
	{
		String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		tmpFileName = "."; /* Fichier caché sur les sytèmes UNIX */
		for (int i = 0; i < 50; i++)
		{
			tmpFileName += charset.charAt((int) (Math.random() * charset.length()));
		}
	}
	/* Ecrit dans le fichier temporaire */
	private void writeTmpFile()
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("ressources/"+tmpFileName)));
		
			writer.write(tmpFileContent);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*try {
			PrintWriter writer =  new PrintWriter(new BufferedWriter
					   (new FileWriter("Ressources/"+tmpFileName)));
			writer.print(tmpFileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	private void importInstructionsFromFile(String path)
	{
		
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				instructions += sCurrentLine+'\n';
				//tmpFileContent += "print str(inspect.currentframe().f_back.f_lineno)";
				
						
				
			}
			tmpFileContent = instructions;
			tmpFileContent = tmpFileContent.replaceAll("tank.doNothing\\(\\)", "tank.doNothing(lineno())");
			tmpFileContent = tmpFileContent.replaceAll("tank.moveForward\\(\\)", "tank.moveForward(lineno())");
			tmpFileContent = tmpFileContent.replaceAll("tank.moveBackward\\(\\)", "tank.moveBackward(lineno())");
			tmpFileContent = tmpFileContent.replaceAll("tank.turnClockwise\\(\\)", "tank.turnClockwise(lineno())");
			tmpFileContent = tmpFileContent.replaceAll("tank.turnCounterClockwise\\(\\)", "tank.turnCounterClockwise(lineno())");
			tmpFileContent = tmpFileContent.replaceAll("tank.shoot\\(\\)", "tank.shoot(lineno())");
			tmpFileContent = tmpFileContent.replaceAll("tank.radar\\(\\)", "tank.radar(lineno())");
			tmpFileContent = tmpFileContent.replaceAll("tank.distanceOfForwardObstacle\\(\\)", "tank.distanceOfForwardObstacle(lineno())");
			tmpFileContent = tmpFileContent.replaceAll("tank.typeOfForwardObstacle\\(\\)", "tank.typeOfForwardObstacle(lineno())");
			
			GraphicInterface.textAreaCode.setText(instructions);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
}
