package pokemon;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.io.BufferedReader;

class rand{

	String method() {
		String path = new File(".").getAbsoluteFile().getParent();
		String _path = new File(path).getAbsoluteFile().getParent();
		File picturesdir = new File(_path);
		File[] picture = picturesdir.listFiles();
		boolean loop = true, x = false;
		String tmp = "", line = "", name = "";

		while (loop) {
			Random rnd = new Random();
            int number = rnd.nextInt(802) + 1; 	//図鑑番号
            String numbers;		//図鑑番号(String)

            /*図鑑番号をStringに変換*/
            if (number < 10) {
            	numbers = "00" + new Integer(number).toString();
            } else if (number < 100) {
            	numbers = "0" + new Integer(number).toString();
            } else {
            	numbers = new Integer(number).toString();
            }

            for (int i = 0; i < picture.length; i++) {
            	File file = picture[i];
            	tmp = "" + file;
            	if (tmp.length() > 3) {
            		tmp = tmp.substring(_path.length() + 1, _path.length() + 4);
            		if (tmp.startsWith(numbers)) {
            			x = true;
            			break;
            		}
            	}
            }

            try {
            	FileReader fr = new FileReader("UTF8.txt");
            	String osname = System.getProperty("os.name");
            	if (osname.indexOf("Windows") >= 0) {
            		fr = new FileReader("ShiftJIS.txt");
            	}
            	BufferedReader br = new BufferedReader(fr);
            	for (int i = 0; i < (number - 1); i++) {
            		br.readLine();
            	}
            	line = br.readLine();
            	br.close();
            } catch (Exception e) {
            	line = "Error! The file \"UTF8.txt\" or \"ShiftJIS.txt\" couldn't find.";
            }

            if (x) {
            	x = !x;
            	name = name + line + "      Painted\n";
            } else {
            	loop = false;
            	name = name + line + "\n";
            }

            //System.out.println(line);
        }
        return (name);
    }

    int count() {

    	String str = new File(".").getAbsoluteFile().getParent();
    	String path = new File(str).getAbsoluteFile().getParent();
    	File picturesdir = new File(path);
    	File[] picture = picturesdir.listFiles();

    	ArrayList<Integer> result = new ArrayList<Integer>();

    	for(int i = 0; i < picture.length; i++){

    		str = picture[i] + "";
    		String file_name = str.substring(path.length()+1,str.length());
    		String extension = "null";

    		if(file_name.length() > 2)
    			extension = file_name.substring(file_name.length()-3,file_name.length());

    		if(extension.equals("png")){
    			int number = Integer.parseInt(file_name.substring(0,3));
    			if(result.indexOf(number) < 0)
    				result.add(number);
    		}

    	}

    	return result.size();

    }

}