
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class wordCount extends JFrame implements ActionListener {

	// frames hold all your components
	JPanel mypanel;
	JButton mybutton;
	JLabel mylabel;
	JTextArea showtext;
	JScrollPane Scrool;

	int count = 0;

	public wordCount() {
		// constructor reates panel and GUI components and adds them to the frame and
		// panel.
		mypanel = new JPanel();
		mybutton = new JButton("Click");
		mylabel = new JLabel("I am just displaying stuff");
		showtext = new JTextArea(30, 40);
		/*
		 * Scrool= new JScrollPane(
		 * 
		 * ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		 * ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		 */
		// myframe.add(Scrool);

		add(mypanel);
		mypanel.add(mybutton);
		mypanel.add(mylabel);
		mypanel.add(showtext);

		// also initialises the event handling for the button
		mybutton.addActionListener(this);

	}

	public static void main(String[] args) throws IOException {
		// creates Jframe object, sets title, size and location and makes it appear
		// onscreen. It also closes the frame when you press X
		// String mytext="";
		JFrame myframe = new wordCount();
		myframe.setTitle("Word Counter");
		myframe.setSize(500, 600);
		myframe.setLocation(10, 200);
		myframe.setVisible(true);

		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		String mytext = "";
		Map<String, Integer> frequency = new HashMap<>();
		ArrayList<String> wordF = new ArrayList<>();

		String[] Punctions = { "!", "#", "$", "&", "%", "'", "(", ")", "*", "+", ",", ".", "/", ":", ";", "<", ">", "=",
				"?", "@", "[", "]", "^", "_", "`", "{", "}", "|", "~", "-", "\\" };
		List<String> PunctionList = Arrays.asList(Punctions);
		String[] stopwords = { "i", "me", "my", "myself", " we", "our", "ours", "ourselves", "you", "your", "yourself",
				"yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself",
				"they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
				"these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had",
				"having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as",
				"until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through",
				"during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off",
				"over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how",
				"all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not",
				"only", "own", "same", "so", "than", "too", "very", "can", "will", "just", "don", "should", "now" };

		// making the English stop word array to an arraylist
		List<String> stopwordslist = Arrays.asList(stopwords);
		try {
			FileReader readFile = new FileReader("Senteces.txt");
			BufferedReader BR = new BufferedReader(readFile);
			String line = null;
			while ((line = BR.readLine()) != null) {
				mytext = line;

				// String mytext="";
				// String x =showtext.getText();
				mytext = mytext.toLowerCase();
				mytext = mytext.replace(",", "");
				mytext = mytext.replace(".", "");

				String derivedwords[] = mytext.split(" ");
				List<String> words = Arrays.asList(derivedwords);
				System.out.println(words);
				wordF.addAll(words);
				wordF.removeAll(PunctionList);
				wordF.removeAll(stopwordslist);
				System.out.println(wordF);
				for (String FinalWords : wordF) {
					if (FinalWords == null) {
						continue;
					}

					String ActionWords = FinalWords;
					if (frequency.containsKey(ActionWords)) {
						frequency.put(ActionWords, frequency.get(ActionWords) + 1);
					} else {
						frequency.put(ActionWords, 1);
					}
				}

				List<Entry<String, Integer>> desending = new ArrayList<>(frequency.entrySet());

				// here we are sorting by the word frequency which is the value
				Collections.sort(desending, (f1, f2) -> f2.getValue().compareTo(f1.getValue())); // for descending we
																									// are comparing m2
																									// to m1.

				for (Entry<String, Integer> entry : desending) // iterating the entries to get a sorted hashmap
				{
					System.out.println("(" + entry.getValue() + ", " + entry.getKey() + ")");

				}
				System.out.println(frequency);
				// System.out.println(mytext);

				if (e.getSource() == mybutton) {
					count = wordF.size();
					showtext.getText();
					// showtext.setText("frequency "+ showtext.getText() );
					mylabel.setText("Count" + count);

					for (int i = 0; i < frequency.size(); i++) {

						showtext.setText(showtext.getText() + "(" + desending.get(i) + ")" + "\n");
						// myarea.setText(myarea.getText() + entry.getValue() + ", " + entry.getKey() +
						// ")" ) ;

						// myarea.append(myarea.getText());

					}

					// x=x.toLowerCase();
					// line=line.replace(",", "");
					// line=line.replace(".", "");
					// System.out.println(line);
					// System.out.println (mytext);

					// showtext.setText(mytext);

					// mylabel.setText("Frequency"+frequency);

					// System.out.println("you typed " + x);
					// count++;
					// System.out.println("you pressed the button " + count + " times");

				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
