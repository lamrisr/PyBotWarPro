package thinktank.javabot.intelligences;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.python.apache.commons.compress.utils.IOUtils;




public class Script {

	private String instructions;
	private String filename;
	private int currentLine = 0;
	private String tmpFilename;
	private String output; /* Sortie telle qu'elle est affichée dans l'inferface graphique */
	private OutputStream JVBLayerOutput; /*Redirection de la sortie standard, composée de la surcouche Javabot (numéros de lignes)*/
	
	public Script(String filename)
	{
		this.filename = filename;
	}
	
	public String getInstructions()
	{
		return instructions;
	}
	
	public String getOutput()
	{
		return output;
	}
	private void convertJVBLayerOutPutToOutPut()
	{
		Writer writer = new StringWriter();
		//IOUtils.copy(JVBLayerOutput, writer, "UTF-8");
		String buff = writer.toString();
		//String buff = IOUtils.toString(JVBLayerOutput, "UTF-8");
		String lines[] = buff.split("\\n");
		for (int i = 0; i < lines.length - 1; i+= 2)
		{
			output += lines[i + 1]+'\n';
			currentLine = Integer.parseInt(lines[i]) - (Integer.parseInt(lines[i]) / 2);
			
		}
	}
	private void importInstructionsFromFile(String path)
	{
		String tmpFileContent = "";
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				instructions += sCurrentLine+'\n';
				tmpFileContent += "print str(inspect.currentframe().f_back.f_lineno)+\"\n\"";
				tmpFileContent += sCurrentLine;
						
				
			}

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
