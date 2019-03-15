package prog_a1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pro1 {

	private  Dict [] diction=new Dict[2000000];

	private   int n=0;
	private  Scanner kb = new Scanner (System.in);
	private 	String wntofind;
	public static void main(String[] args) {
		Pro1 app = new Pro1();
		app.process_command();

	}

	private  void process_command() {


		while(true) {
			System.out.print("$ ");
			String command=kb.next();

			if(command.equals("read")) {
				read();
			}
			else if(command.equals("size")) {;
			System.out.println(n);
			}

			else if(command.equals("find")) {
				find();

			}
			else if(command.equals("exit"))
				break;
		}


		kb.close();
	}

	private  void find() {

		wntofind=kb.next();
		int index=0;

		index=binarySearch( 0, n-1);
		//System.out.println(index);


	}

	private int binarySearch(int begin,int end) {

		if(end<begin) {
	
			System.out.println("Not found.");
			System.out.println(diction[end].pos);
			System.out.println("------");
			System.out.println(diction[begin].pos);
			return end;

		}
		else {
		
			int middle=(begin+end)/2;
			if(diction[middle].word.compareToIgnoreCase(wntofind)==0 ) {
			
				countsame(middle);
				return middle;
			}
			else if(diction[middle].word.compareToIgnoreCase(wntofind)>0) {

				return binarySearch(begin, middle-1);
			}

			else 
				return	binarySearch(middle+1,end);

			}
		
		}

	private void countsame(int index) {
		int size=0,tmp=index;
		String str=diction[index].word;
		while(true) {
			tmp--;
		

			if(tmp<0 || str.compareToIgnoreCase(diction[tmp].word)!=0 ) {
				break;

			}

		}
		index=tmp+1;
		while(true) {
			tmp++;
			size++;

			if( str.compareToIgnoreCase(diction[tmp].word)!=0 ) break;


		}
		System.out.println("Found "+(size-1)+" items");

		for(int i=0;i<size-1;i++) {
			System.out.println(diction[index++].allstr);
		}


	}
	private  void read() {
		String myFile=kb.next();
		int i=0;
		Scanner in;
		try {
			in = new Scanner (new File(myFile) );
			while(in.hasNext()) {

				String tmp=in.nextLine();

				if(tmp.length()!=0 ) {
					i=tmp.indexOf('(');
					int j=tmp.indexOf(')');

					diction[n]=new Dict();
					if(i<0) continue;
					diction[n].allstr=tmp;//단어 한줄 모두
					diction[n].word=tmp.substring(0,i-1);//
					diction[n].pos=tmp.substring(0,j+1);
					tmp=diction[n].word.replaceFirst("-", "");
					tmp=diction[n].word.replaceFirst("'", "");
					tmp=diction[n].word.replaceAll(" ", "");
					diction[n].word=tmp;

					n++;
				}

			}in.close();


		} catch (FileNotFoundException e) {
			System.out.println("No data file");
			e.printStackTrace();
		}




	}




}
